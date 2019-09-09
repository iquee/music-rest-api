package com.github.iquee.music.utils;

public interface PopFromQueue<T> {

    void receiveMessageFromQueue(T clazz);

    //void sendToQueue(T object);
}
