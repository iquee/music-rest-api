package com.luiztaira.music.utils;

public interface PopFromQueue<T> {

    void receiveMessageFromQueue(T clazz);

    //void sendToQueue(T object);
}
