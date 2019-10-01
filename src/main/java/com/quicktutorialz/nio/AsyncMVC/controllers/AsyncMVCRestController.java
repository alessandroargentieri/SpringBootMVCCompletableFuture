package com.quicktutorialz.nio.AsyncMVC.controllers;

import com.quicktutorialz.nio.AsyncMVC.models.Payment;
import com.quicktutorialz.nio.AsyncMVC.models.PaymentDTO;
import com.quicktutorialz.nio.AsyncMVC.services.PaymentService;
import io.reactivex.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;



@RestController
public class AsyncMVCRestController {

    @Autowired
    private PaymentService paymentService;


    @GetMapping(value = "/status")
    public String healthCheck(){
        return "{ \"status\" : \"ok\" }";
    }


    @GetMapping(value = "/error")
    public String error(){
        return "{ \"status\" : \"error\" }";
    }

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

    /* this example convert an Rx Observable to a Completable Future */
    @PostMapping("/save/observable")
    public CompletableFuture<Payment> savePaymentObservable(@RequestBody PaymentDTO paymentDTO){

        return fromSingleObservable( Observable.fromCallable(()-> paymentService.createPaymentFromDTO(paymentDTO))
                                               .map(payment -> paymentService.saveInDB(payment))   );

    }


    /* function to transform observable into completable future  */
    public static <T> CompletableFuture<T> fromSingleObservable(Observable<T> observable) {
        final CompletableFuture<T> future = new CompletableFuture<>();
        observable
                .doOnError(future::completeExceptionally)
                .forEach(future::complete);
        return future;
    }

}
