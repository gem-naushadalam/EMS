package com.gemini.ems.controller;

import com.gemini.ems.model.Grievance;
import com.gemini.ems.model.Response;
import com.gemini.ems.service.ComplainService;
import com.gemini.ems.service.ElectricityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
/**
 * @author na.alam
 * */
@RestController
@RequestMapping("ems")
public class GrievanceController {
    private static Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private ComplainService complainService;
    @PostMapping("/grievance")
    public Response<String> grievance(@RequestBody Grievance grievance) throws ExecutionException, InterruptedException {

        Response<String> response = new Response<>();
        response.setData(complainService.registerComplain(grievance).toString());
        if(response.getData() != null){
            response.setMessage("Grievance submitted successfully");
        }else{
            response.setMessage("Not able to submit Grievance");
        }
        logger.info("Grievance submitted for - " + grievance + " with complaintId - " + response.getData());
        return response;
    }
}
