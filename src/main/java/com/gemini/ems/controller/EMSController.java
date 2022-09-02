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
    private ElectricityService electricityService;

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
        logger.info("Total bill amount generated - " + response.getData().getTotalBillAmount() + " for units - " + units);
        return response;
    }



}
