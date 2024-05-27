package com.dropwizard.application.service;

import com.dropwizard.application.dao.DaoImpl;
import com.dropwizard.application.exception.ServiceException;
import com.dropwizard.application.models.QueueImpl;
import com.dropwizard.application.models.Subscriber;

import javax.json.JsonObject;
import java.util.List;

public class MessageDeliveryService {
    DaoImpl dao;
    QueueImpl queue;

    public MessageDeliveryService() {
        this.queue = QueueImpl.getINSTANCE();
        dao = new DaoImpl();
    }

    public void processMessages() throws ServiceException {
        while (!queue.isEmpty()) {
            JsonObject message = queue.deque();
            for (Subscriber subscriber :
                    dao.getSubscribers()) {
                if (subscriber.isInterestedInMessage(message)) {
                    subscriber.triggerCallback(message);
                }
            }
        }
    }

    public void addMessageToQueue(JsonObject jsonObject) {
        if (queue.enqueue(jsonObject)) {
            if (!Worker.getINSTANCE().isRunning()) {
                Worker.getINSTANCE().run();
            }
        }
    }

    public List<JsonObject> getMessagesOfSubscriber(String subscriberName) {
        Subscriber subscriber = dao.checkAndGetSubscriber(subscriberName);
        if (subscriber == null) {
            subscriber = dao.checkAndGetDependentSub(subscriberName);
        }
        return subscriber.getMessages();
    }

    public void addSubscriber(String regex, String name) throws ServiceException {
        if (dao.doesSubscriberExists(name) || dao.doesDependentSubscriberExists(name)) {
            throw new ServiceException("Subscriber already exists!");
        }
        Subscriber subscriber = new Subscriber(regex, name);
        dao.addSubscriber(subscriber);
    }

    public void addDependentSubscriber(String regex, String name, String mainSubscriber) throws ServiceException {
        if (dao.doesSubscriberExists(name) || dao.doesDependentSubscriberExists(name)) {
            throw new ServiceException("Subscriber already exists!");
        }
        Subscriber subscriber = new Subscriber(regex, name);
        Subscriber mainSub = dao.checkAndGetSubscriber(mainSubscriber);
        dao.addDependentSubscriber(subscriber);
        dao.addDependentSubToSub(subscriber, mainSub);
    }

}
