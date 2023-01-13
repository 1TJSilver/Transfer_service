package com.example.hw_7_4_1_1_transfer_service.controller;

import com.example.hw_7_4_1_1_transfer_service.base.Account;
import com.example.hw_7_4_1_1_transfer_service.service.TransferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TransferController {
    TransferService service;

    public TransferController (TransferService service){
        this.service = service;
    }
    @PostMapping("/transfer")
    public void transfer(String cardFromValidTill, String cardFromCVV,
                         String cardToNumber, Integer amount){
        service.transfer(cardFromValidTill, cardFromCVV, cardToNumber, amount);
    }
    @PostMapping("/confirmOperation")
    public void confirmOperation(String operationId, String code){
        service.confirmOperation(operationId, code);
    }

}
