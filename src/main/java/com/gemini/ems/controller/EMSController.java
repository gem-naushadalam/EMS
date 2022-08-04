package com.gemini.ems.controller;

import com.gemini.ems.model.BillDetails;
import com.gemini.ems.model.Grievance;
import com.gemini.ems.model.RegisterUser;
import com.gemini.ems.model.Response;
import com.gemini.ems.service.ElectricityService;
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
public class EMSController {
    private static Logger logger = LoggerFactory.getLogger(EMSController.class);
    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private ElectricityService electricityService;

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
        return response;
    }

    @GetMapping("/calculate")
    public Response<BillDetails> calculateBill(@RequestParam Integer units) throws ExecutionException,
            InterruptedException {
        Response<BillDetails> response = new Response<>();
        response.setData(electricityService.calculateBillIncludingTax(units));
        if(response.getData() != null){
            response.setMessage("Successfully fetched the bills for " + units + " units");
        }else{
            response.setMessage("Not able to fetch bills for " + units + " units");
        }

        return response;
    }

    @PostMapping("/grievance")
    public Response<String> grievance(@RequestBody Grievance grievance) throws ExecutionException, InterruptedException {

        Response<String> response = new Response<>();
        response.setData(electricityService.registerComplain(grievance).toString());
        if(response.getData() != null){
            response.setMessage("Grievance submitted successfully");
        }else{
            response.setMessage("Not able to submit Grievance");
        }
        return response;
    }

}
