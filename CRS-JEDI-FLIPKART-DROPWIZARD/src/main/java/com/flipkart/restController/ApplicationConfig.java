package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;



public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {

        register(StudentRESTAPIController.class);
        register(ProfessorRESTAPIController.class);
        register(LoginAndSignupController.class);
        register(AdminRESTAPIController.class);

    }

}
