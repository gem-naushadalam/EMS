package com.gemini.ems.service;

import com.gemini.ems.dao.EMSDao;
import com.gemini.ems.model.RegisterUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class RegistrationService {

    @Autowired
    private EMSDao emsDao;

    public UUID register(RegisterUser registerUser) throws ExecutionException, InterruptedException {
        UUID customerId = UUID.randomUUID();
        Boolean flag = CompletableFuture.supplyAsync(()->
                emsDao.createUser(registerUser,customerId)).get();
        if (flag)
            return customerId;
        else
            return null;
    }
}
