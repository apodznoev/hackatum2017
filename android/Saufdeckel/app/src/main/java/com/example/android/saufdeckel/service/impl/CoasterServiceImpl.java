package com.example.android.saufdeckel.service.impl;

import android.util.Log;
import com.example.android.saufdeckel.models.Coaster;
import com.example.android.saufdeckel.models.Drink;
import com.example.android.saufdeckel.service.CoasterListener;
import com.example.android.saufdeckel.service.CoastersService;
import com.example.android.saufdeckel.service.mqtt.MqttClientFactory;
import com.example.android.saufdeckel.service.mqtt.messages.Topic;
import org.eclipse.paho.client.mqttv3.*;

import java.util.List;

/**
 * @author Andrei.Podznoev
 * Date    18.11.2017.
 */
public class CoasterServiceImpl implements CoastersService {
    private MqttClient client;
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
        for (Drink drink : coaster.getmAllDrinks()) {
            sum += drink.getPrice();
        }

        return sum;
    }

    @Override
    public void setListener(CoasterListener listener) {
        clientListener = listener;
    }

    @Override
    public void start() {
        MqttClientFactory mqttF = new MqttClientFactory();
        try {
            client = mqttF.createClent();
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    Log.e("MqTT", "Connection broken", cause);
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Topic.create(topic);
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
}
