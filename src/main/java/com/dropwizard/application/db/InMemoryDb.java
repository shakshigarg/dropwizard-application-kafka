package com.dropwizard.application.db;

import com.dropwizard.application.models.Subscriber;

import java.util.*;

public class InMemoryDb {
    List<Subscriber> subscriberList;
    List<Subscriber> dependentSubscribers;
    private static final InMemoryDb INSTANCE = new InMemoryDb();

    private InMemoryDb() {
        subscriberList = new ArrayList<>();
    }

    public static InMemoryDb getInstance() {
        return INSTANCE;
    }

    public List<Subscriber> getSubscriberList() {
        return subscriberList;
    }
    public List<Subscriber> getDependentSubscribers() {
        return dependentSubscribers;
    }

}
