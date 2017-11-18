package com.hackatum2017.mqtt;

import com.hackatum2017.mqtt.messages.Message;
import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;

/**
 * @author apodznoev
 * @since 18/11/17
 */
public interface MessageSender {

    CompletableFuture<Void> sendMessage(Message message);
}
