package com.gemini.ems.controller;

import com.gemini.ems.model.RegisterUser;
import com.gemini.ems.model.Response;
import com.gemini.ems.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * @author na.alam
 * */
@RestController
@RequestMapping("ems")
public class RegistrationController {
    private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public Response<String> registerUser(@RequestBody RegisterUser registerUser) throws ExecutionException, InterruptedException {
        Response<String> response = new Response<>();
        response.setData(registrationService.register(registerUser).toString());
        if(response.getData() != null) {
            response.setMessage(" User has been registered successfully");
        }else {
            response.setMessage("User has not been registered");
        }
        logger.info("User - " + registerUser + " has been registered successfully with id - " + response.getData());
        return response;
    }
}
