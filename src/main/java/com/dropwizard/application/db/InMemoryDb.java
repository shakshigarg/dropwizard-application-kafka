package com.dropwizard.application.db;
import java.util.HashMap;
import java.util.Map;

public class InMemoryDb {
    Map<String, String> inMemoryDb;
    private static final InMemoryDb INSTANCE = new InMemoryDb();

    private InMemoryDb() {
        inMemoryDb = new HashMap<>();
    }

    public static InMemoryDb getInstance() {
        return INSTANCE;
    }

}
