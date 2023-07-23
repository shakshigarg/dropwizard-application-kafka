package com.dropwizard.application.service;

import com.dropwizard.application.dao.Dao;
import com.dropwizard.application.dao.DaoImpl;
import com.dropwizard.application.factoryDesignPattern.IService;
import com.dropwizard.application.factoryDesignPattern.ServiceFactory;

public class Service {
    Dao dao;
    IService service;
    ServiceFactory serviceFactory;

    public Service() {
        this.dao = new DaoImpl();
        this.serviceFactory = new ServiceFactory();
        this.service = serviceFactory.getService();
    }

    public String getDetails() {
        return "success";
    }
}
