package com.dropwizard.application.models;

import com.dropwizard.application.exception.ServiceException;

import javax.json.JsonObject;

public class QueueImpl {

    int front = -1;
    int rear = -1;
    JsonObject[] queue;
    int n;

    private final static QueueImpl INSTANCE = new QueueImpl(100);

    private QueueImpl(int n) {
        this.queue = new JsonObject[n];
        this.n = n;
    }

    public static QueueImpl getINSTANCE() {
        return INSTANCE;
    }

    public int size() {
        if (isEmpty()) {
            return 0;
        }
        if (rear > front) {
            return rear - front;
        } else {
            return n - front - rear;
        }
    }

    public boolean enqueue(JsonObject jsonObject) {
        rear++;
        rear = rear % n;
        if (rear == front) {
            return false;
        }
        queue[rear] = jsonObject;
        if (front == -1) {
            front = 0;
        }
        return true;
    }

    public void clear() {
        front = -1;
        rear = -1;
    }

    public JsonObject deque() throws ServiceException {
        if (isEmpty()) {
            throw new ServiceException("Queue is empty!");
        }
        JsonObject frontObject = queue[front];
        if (front == rear) {
            front = -1;
            rear = -1;
        }
        front++;
        front = front % n;
        return frontObject;
    }

    public boolean isEmpty() {
        return front == -1 && rear == -1;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        if (front > rear) {
            for (int i = front; i >= rear; i++) {
                System.out.println(queue[front].toString());
                i = i % n;
            }
        } else {
            for (int i = front; i <= rear; i++) {
                System.out.println(queue[front].toString());
                i = i % n;
            }
        }
    }

}
