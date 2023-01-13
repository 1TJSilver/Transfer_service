package com.example.hw_7_4_1_1_transfer_service.config;

import com.example.hw_7_4_1_1_transfer_service.controller.TransferController;
import com.example.hw_7_4_1_1_transfer_service.repository.TransferRepository;
import com.example.hw_7_4_1_1_transfer_service.service.TransferService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransferConfig {
    @Bean
    public TransferController transferController (TransferService service){
        return new TransferController(service);
    }

    @Bean
    public TransferService transferService (TransferRepository repository){
        return new TransferService(repository);
    }

    @Bean
    public TransferRepository transferRepository (){
        return new TransferRepository();
    }
}
