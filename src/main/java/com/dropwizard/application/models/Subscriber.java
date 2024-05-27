package com.dropwizard.application.models;

import lombok.Builder;
import lombok.Data;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Builder
@Data
public class Subscriber {
    List<Subscriber> dependentSubscribers = new ArrayList<>();
    List<JsonObject> messages = new ArrayList<>();

    Pattern regex;
    String name;

    public Subscriber(String regex, String name) {
        addRegex(regex);
        this.name = name;

    }

    public List<Subscriber> getDependentSubscribers() {
        return dependentSubscribers;
    }

    public void setDependentSubscribers(List<Subscriber> dependentSubscribers) {
        this.dependentSubscribers = dependentSubscribers;
    }

    public void setMessages(List<JsonObject> messages) {
        this.messages = messages;
    }

    public Pattern getRegex() {
        return regex;
    }

    public void setRegex(Pattern regex) {
        this.regex = regex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addRegex(String regex) {
        this.regex = Pattern.compile(regex);
    }

    public boolean isInterestedInMessage(JsonObject jsonObject) {
        Matcher matcher = this.regex.matcher(jsonObject.toString());
        return matcher.matches();
    }

    public void triggerCallback(JsonObject jsonObject) {
        for (Subscriber subscriber :
                dependentSubscribers) {
            subscriber.triggerCallback(jsonObject);
        }
        callBack(jsonObject);
    }

    private void callBack(JsonObject jsonObject) {
        messages.add(jsonObject);
    }

    public List<JsonObject> getMessages() {
        return messages;
    }

    public void addDependentSub(Subscriber subscriber) {
        dependentSubscribers.add(subscriber);
    }

}
