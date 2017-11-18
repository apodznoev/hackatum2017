package com.example.android.saufdeckel.service.impl;

import android.util.Log;
import com.example.android.saufdeckel.models.Coaster;
import com.example.android.saufdeckel.models.Drink;
import com.example.android.saufdeckel.service.CoasterListener;
import com.example.android.saufdeckel.service.CoastersService;
import com.example.android.saufdeckel.service.mqtt.MqttClientFactory;
import com.example.android.saufdeckel.service.mqtt.messages.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Andrei.Podznoev
 * Date    18.11.2017.
 */
public class CoasterServiceImpl implements CoastersService {
    private MqttClient client;
    private ObjectMapper mapper = new ObjectMapper();
    private final CoastersStorage coastersStorage = new CoastersStorage();
    private CoasterListener clientListener;

    @Override
    public List<Coaster> getCoasters() {
        return coastersStorage.getCoasters();
    }

    @Override
    public Coaster getCoasterById(int coasterId) {
        for (Coaster coaster : coastersStorage.getCoasters()) {
            if (coaster.getId() == coasterId) {
                return coaster;
            }
        }
        return null;
    }

    @Override
    public double checkoutCoaster(int coasterId) {
        double sum = 0.0;

        Coaster coaster = getCoasterById(coasterId);
        if (coaster == null)
            return sum;

        coastersStorage.removeCoaster(coaster);
        for (Drink drink : coaster.getAllDrinks()) {
            sum += drink.getPrice();
        }
        sendCheckoutMessage(coaster.getId());

        return sum;
    }


    @Override
    public void setListener(CoasterListener listener) {
        clientListener = listener;
        clientListener.coastersLoaded(coastersStorage.getCoasters());
    }

    @Override
    public void start() {
        MqttClientFactory mqttF = new MqttClientFactory();
        try {
            client = mqttF.createClient();
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.e("MqTT", "Connection broken", cause);
                }

                @Override
                public void messageArrived(String rawTtopic, MqttMessage message) throws Exception {
                    Topic topic = Topic.create(rawTtopic);
                    if (topic == null) {
                        Log.w("MqTT", "Got unknonw topic:" + rawTtopic);
                        return;
                    }
                    String msgString = new String(message.getPayload());
                    switch (topic) {
                        case TEST_TOPIC:
                            TestMessage testMsg = mapper.readValue(msgString, TestMessage.class);
                            Log.i("MqTT","Test message received:" + testMsg.toString());
                            break;
                        case WEIGHT_CHANGE_EVENT:
                            WeightChangeMessage weightChangeMessage = mapper.readValue(msgString, WeightChangeMessage.class);
                            Log.i("MqTT","Weight change message received:" + weightChangeMessage.toString());
                            Coaster coaster = getCoasterById(weightChangeMessage.getCoasterId());
                            if (coaster == null) {
                                Log.e("MqTT", "Cannot find coaster for id:" + weightChangeMessage.getCoasterId());
                                return;
                            }
                            double previousWeight = coaster.getCurrentDrink().getStatus();
                            if (drinkIsFinished(coaster.getCurrentDrink(), previousWeight, weightChangeMessage)) {
                                coaster.getCurrentDrink().setWeight(weightChangeMessage.getNewWeight());
                                if (clientListener != null)
                                    clientListener.drinkFinished(coaster);
                                sendDrinkFinishedMessage(coaster.getId(), coaster.getCurrentDrink());
                            } else if (isNewDrinkStarted(coaster.getCurrentDrink(), previousWeight, weightChangeMessage)) {
                                Drink drink = predictNewDrink(weightChangeMessage);
                                if (drink == null) {
                                    Log.e("Logic", "cannot determine type of new drink from event:" + weightChangeMessage);
                                    return;
                                }
                                coaster.addDrink(drink);
                                if (clientListener != null)
                                    clientListener.newDrinkStarted(coaster);
                                sendNewDrinkStartedMessage(coaster.getId(), coaster.getCurrentDrink());
                            } else {
                                coaster.getCurrentDrink().setWeight(weightChangeMessage.getNewWeight());
                                if (clientListener != null)
                                    clientListener.drinkLevelChanged(coaster);
                                sendDrinkWeightChangedMessage(coaster.getId(), coaster.getCurrentDrink());
                            }
                            break;
                        case CHANGE_LIGHT_COMMAND:
                            Log.w("MqTT", "Not expecting to listen for Light change events");
                            break;
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.i("MqTT", "Message delivered" + token);
                }
            });

            for (Topic topic : Topic.values()) {
                client.subscribe(topic.getTopic(), 1);
            }
        } catch (MqttException e) {
            Log.e("Start", "Cannot start mqtt service", e);
        }
    }

    private void sendCheckoutMessage(int coasterId) {
        try {
            client.publish(
                    Topic.CHANGE_LIGHT_COMMAND.getTopic(),
                    mapper.writeValueAsBytes(new ChangeLightMessage(coasterId, 255)),
                    1,
                    false);
        } catch (Exception e) {
            Log.e("MqTT", "Cannot send message due to error", e);
        }
    }

    private void sendDrinkWeightChangedMessage(int coasterId, Drink currentDrink) {
        try {
            double fullness = currentDrink.getStatus() / currentDrink.getType().getFullWeight();
            client.publish(
                    Topic.CHANGE_LIGHT_COMMAND.getTopic(),
                    mapper.writeValueAsBytes(new ChangeLightMessage(coasterId, (int) Math.round(255 * fullness))),
                    1,
                    false);
        } catch (Exception e) {
            Log.e("MqTT", "Cannot send message due to error", e);
        }
    }

    private void sendNewDrinkStartedMessage(int coasterId, Drink currentDrink) {
        try {
            client.publish(
                    Topic.CHANGE_LIGHT_COMMAND.getTopic(),
                    mapper.writeValueAsBytes(new ChangeLightMessage(coasterId, 150)),
                    1,
                    false);
        } catch (Exception e) {
            Log.e("MqTT", "Cannot send message due to error", e);
        }
    }

    private void sendDrinkFinishedMessage(int coasterId, Drink currentDrink) {
        try {
            client.publish(
                    Topic.CHANGE_LIGHT_COMMAND.getTopic(),
                    mapper.writeValueAsBytes(new ChangeLightMessage(coasterId, 0)),
                    1,
                    false);
        } catch (Exception e) {
            Log.e("MqTT", "Cannot send message due to error", e);
        }
    }

    private Drink predictNewDrink(WeightChangeMessage weightChangeMessage) {
        for (Drink.DrinkType drinkType : Drink.DrinkType.values()) {
            if (Math.abs(weightChangeMessage.getNewWeight() - drinkType.getFullWeight()) < drinkType.getFullWeight() / 5) {
                double randomPrice = (Math.round(1 + Math.random()) * 10) / 10 + drinkType.getDefaultPrice();
                return new Drink(drinkType.getRandomName(), randomPrice, drinkType);
            }
        }
        return null;
    }

    private boolean isNewDrinkStarted(Drink currentDrink, double previousWeight, WeightChangeMessage weightChangeMessage) {
        if (previousWeight > weightChangeMessage.getNewWeight()) {
            return false;
        }
        double fullWeight = currentDrink.getType().getFullWeight();
        if (Math.abs(fullWeight - weightChangeMessage.getNewWeight()) > fullWeight / 2) {
            return true;
        }
        return false;
    }

    private boolean drinkIsFinished(Drink currentDrink, double previousWeight, WeightChangeMessage weightChangeMessage) {
        if (weightChangeMessage.getNewWeight() > previousWeight) {
            return false;
        }
        if (weightChangeMessage.getNewWeight() <= 0) {
            return true;
        }
        return false;
    }
}
