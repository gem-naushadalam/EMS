package com.gemini.ems.service;

import com.gemini.ems.model.BillDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ElectricityService {
    private static Logger logger = LoggerFactory.getLogger(ElectricityService.class);

    public BillDetails calculateBillIncludingTax(Integer units) {
        CompletableFuture<BillDetails> billDetailsCompletableFuture = CompletableFuture.supplyAsync(() -> calculateBillAmount(units))
                .thenApply(this::billWithGreenTax)
                .thenApply(this::totalBillAmountWithGST)
                .thenApply(totalBillAmount -> new BillDetails.Builder()
                        .setTotalBillAmount(totalBillAmount).build());
        try {
            return billDetailsCompletableFuture.get();
        }catch (Exception ex){
            logger.error("Exception occurred while fetching bill details from completable future object", ex);
        }
        return null;
    }

    private double totalBillAmountWithGST(Double billWithGreenTax) {
        return billWithGreenTax + billWithGreenTax * 18 / 100;
    }

    private double billWithGreenTax(double billAmount) {
        return billAmount + billAmount * 3 / 100;
    }

    private double calculateBillAmount(Integer noOfUnits) {
        double billAmount = 0;
        if (noOfUnits >= 0 && noOfUnits <= 100)
            billAmount = noOfUnits * 5;
        else if (noOfUnits > 100 && noOfUnits <= 400)
            billAmount = noOfUnits * 6;
        else if (noOfUnits > 400) {
            billAmount = noOfUnits * 8;
        }
        return billAmount;
    }

}
