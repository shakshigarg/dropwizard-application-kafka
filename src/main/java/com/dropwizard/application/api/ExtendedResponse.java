package com.dropwizard.application.api;

import lombok.*;

import java.util.Map;
@ToString
@EqualsAndHashCode
public class ExtendedResponse extends ServiceResponse {

    Map<String, String> inventory;

    public ExtendedResponse(String status, String message, Map<String, String> inventory) {
        super(status, message);
        this.inventory = inventory;
    }

}
