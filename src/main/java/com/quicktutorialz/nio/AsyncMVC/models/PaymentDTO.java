package com.quicktutorialz.nio.AsyncMVC.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class PaymentDTO {

    private  Float value;
    private  String committer;
    private  String committee;

}
