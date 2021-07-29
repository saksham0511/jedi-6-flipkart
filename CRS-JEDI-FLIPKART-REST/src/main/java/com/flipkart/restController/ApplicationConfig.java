package com.flipkart.restController;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author JEDI-6
 * class ApplicationConfig
 * Description - This class is used to register the controllers
 */

public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {

        register(StudentRESTAPIController.class);
        register(ProfessorRESTAPIController.class);
        register(LoginAndSignupController.class);
        register(AdminRESTAPIController.class);

    }

}
