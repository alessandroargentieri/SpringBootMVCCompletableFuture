package com.quicktutorialz.nio.AsyncMVC.controllers;

import com.quicktutorialz.nio.AsyncMVC.models.Payment;
import com.quicktutorialz.nio.AsyncMVC.models.PaymentDTO;
import com.quicktutorialz.nio.AsyncMVC.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;



@RestController
public class AsyncMVCRestController {

    @Autowired
    private PaymentService paymentService;


    @PostMapping(value = "/save")
    public Payment savePayment(@RequestBody PaymentDTO paymentDTO){
        Payment payment = paymentService.createPaymentFromDTO(paymentDTO);
        return paymentService.saveInDB(payment);
    }

    /* curl -X POST 'http://localhost:8084/save/async' -d '{"value": 34.5, "committer" : "dasda", "committee": "fdfdfd"}' -H "Content-Type: application/json" */

    @PostMapping("/save/async")
    public CompletableFuture<Payment> savePaymentAsync(@RequestBody PaymentDTO paymentDTO){
        return CompletableFuture.supplyAsync( ()-> paymentService.createPaymentFromDTO(paymentDTO))
                .thenApplyAsync(payment -> paymentService.saveInDB(payment));
    }


}
