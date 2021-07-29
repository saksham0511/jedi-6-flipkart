package com.dropwizard;

import com.flipkart.restController.AdminRESTAPIController;
import com.flipkart.restController.LoginAndSignupController;
import com.flipkart.restController.ProfessorRESTAPIController;
import com.flipkart.restController.StudentRESTAPIController;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author JEDI-6
 * class App
 * Description - This class is used to register all rest controllers
 */
public class App extends Application<Configuration>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        LOGGER.info("Registering REST resources");
        e.jersey().register(new LoginAndSignupController());
        e.jersey().register(new AdminRESTAPIController());
        e.jersey().register(new ProfessorRESTAPIController());
        e.jersey().register(new StudentRESTAPIController());

    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
