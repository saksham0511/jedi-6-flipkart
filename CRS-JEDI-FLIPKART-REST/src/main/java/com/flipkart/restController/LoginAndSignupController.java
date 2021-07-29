package com.flipkart.restController;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.exception.*;
import com.flipkart.operations.AdminInterface;
import com.flipkart.operations.AdminOperations;
import com.flipkart.operations.AuthCredInterface;
import com.flipkart.operations.AuthCredOperations;


import javax.ws.rs.*;

import javax.ws.rs.core.Response;


@Path("/authenticate")
public class LoginAndSignupController {



    /**
     * This method is used for user registration
     *
     */
    @POST
    @Path("/register")
    @Consumes("application/json")

    public Response register(Student student) {
        try {
            AuthCredInterface authCred = new AuthCredOperations();
            authCred.registerStudent(student);
            return Response.status(200).entity("Student Name : " + student.getName() + " added to database").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    /**
     * This method is used for user login
     *
     */
    @POST
    @Path("/login")
    @Consumes("application/json")

    public Response login(User user) {
        try {
            //name, password, email, address
            AuthCredInterface authCred = new AuthCredOperations();
            int userId = user.getUserId();
            String password = user.getPassword();
            Role role = user.getRole();
            boolean status = authCred.authenticateUser(userId, password, role);
            if (status == true){
                if("STUDENT".equals(role.toString())){
                    AdminInterface adminInterface = new AdminOperations();
                    boolean isApproved = false;
                    isApproved = adminInterface.isApproved(userId);
                    if(isApproved){
                        return Response.status(200).entity(user.getUserId() + " logged in successfully.").build();
                    }
                    else{
                        return Response.status(500).entity("Student is not approved.").build();
                    }
                }
                else{
                    return Response.status(200).entity(user.getName() + " logged in successfully.").build();
                }

            }
        } catch (AuthorizationException authorizationException) {
            authorizationException.printStackTrace();
        }
        catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
        return Response.status(500).entity("Login unsuccessful, Check credentials.").build();
    }

}
