package com.dropwizard.application.service;

import com.dropwizard.application.exception.ServiceException;

public class Worker extends Thread {
    private static Worker INSTANCE=new Worker();

    public static Worker getINSTANCE() {
        return INSTANCE;
    }

    private boolean running;
    MessageDeliveryService messageDeliveryService;

    private Worker(){
        this.messageDeliveryService=new MessageDeliveryService();
    }

    public void run() {
        running = true;
        while(running) {
            try {
                messageDeliveryService.processMessages();
            } catch (ServiceException e) {
                running=false;
            }
            if (Thread.interrupted()) {
                running=false;
                return;
            }
            running=false;
        }
    }

    public void stopRunning() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}