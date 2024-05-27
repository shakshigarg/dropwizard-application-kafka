package com.dropwizard.application.dao;

import com.dropwizard.application.db.InMemoryDb;
import com.dropwizard.application.models.Subscriber;

import java.util.List;
import java.util.stream.Collectors;

public class DaoImpl implements Dao {
    InMemoryDb inMemoryDb = InMemoryDb.getInstance();

    @Override
    public List<Subscriber> getSubscribers() {
        return inMemoryDb.getSubscriberList();
    }

    @Override
    public Subscriber checkAndGetSubscriber(String subscriberName) {
        return inMemoryDb.getSubscriberList().stream().filter(subscriber -> subscriber.getName().equals(subscriberName)).collect(Collectors.toList()).get(0);
    }

    @Override
    public Subscriber checkAndGetDependentSub(String subscriberName) {
        return inMemoryDb.getDependentSubscribers().stream().filter(subscriber -> subscriber.getName().equals(subscriberName)).collect(Collectors.toList()).get(0);
    }

    @Override
    public boolean doesSubscriberExists(String subscriberName) {
        return inMemoryDb.getSubscriberList().stream().anyMatch(subscriber -> subscriber.getName().equals(subscriberName));
    }

    @Override
    public boolean doesDependentSubscriberExists(String subscriberName) {
        return inMemoryDb.getDependentSubscribers().stream().anyMatch(subscriber -> subscriber.getName().equals(subscriberName));
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        inMemoryDb.getSubscriberList().add(subscriber);
    }

    @Override
    public void addDependentSubscriber(Subscriber subscriber) {
        inMemoryDb.getDependentSubscribers().add(subscriber);
    }

    @Override
    public void addDependentSubToSub(Subscriber subscriber, Subscriber mainSub) {
        for (Subscriber sub:
             inMemoryDb.getSubscriberList()) {
            if(sub.getName().equals(mainSub)){
                mainSub.addDependentSub(subscriber);
            }
        }
    }
}
