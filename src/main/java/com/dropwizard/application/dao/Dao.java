package com.dropwizard.application.dao;

import com.dropwizard.application.models.Subscriber;

import java.util.List;

public interface Dao {

    List<Subscriber>getSubscribers();

    Subscriber checkAndGetSubscriber(String subscriberName);

    Subscriber checkAndGetDependentSub(String subscriberName);

    public boolean doesSubscriberExists(String subscriberName);
    public boolean doesDependentSubscriberExists(String subscriberName);

    void addSubscriber(Subscriber subscriber);
    void addDependentSubscriber(Subscriber subscriber);

    void addDependentSubToSub(Subscriber subscriber, Subscriber mainSub);
}
