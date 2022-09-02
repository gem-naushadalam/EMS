package com.gemini.ems.service;

import com.gemini.ems.dao.EMSDao;
import com.gemini.ems.model.Grievance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
@Service
public class ComplainService {
    @Autowired
    private EMSDao emsDao;

    public UUID registerComplain(Grievance grievance) throws ExecutionException, InterruptedException {
        UUID grievanceId = UUID.randomUUID();
        Boolean flag = CompletableFuture.supplyAsync(() ->
                emsDao.registerComplain(grievance, grievanceId)).get();
        if (flag)
            return grievanceId;
        else
            return null;
    }
}
