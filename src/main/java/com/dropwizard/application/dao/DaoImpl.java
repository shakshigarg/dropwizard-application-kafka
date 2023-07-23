package com.dropwizard.application.dao;

import com.dropwizard.application.db.InMemoryDb;
public class DaoImpl implements Dao {

    InMemoryDb inMemoryDb = InMemoryDb.getInstance();

}
