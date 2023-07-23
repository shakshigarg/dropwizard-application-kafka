package com.dropwizard.application;
import com.dropwizard.application.resources.ApiResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class DropwizardServiceApplication extends Application<DropwizardServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardServiceApplication().run(args);
    }


    @Override
    public void run(DropwizardServiceConfiguration dropwizardServiceConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new ApiResource());
    }

}
