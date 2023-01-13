package com.example.hw_7_4_1_1_transfer_service.service;

import com.example.hw_7_4_1_1_transfer_service.base.Account;
import com.example.hw_7_4_1_1_transfer_service.base.Operation;
import com.example.hw_7_4_1_1_transfer_service.exceptions.InvalidCredentialsException;
import com.example.hw_7_4_1_1_transfer_service.exceptions.ServerException;
import com.example.hw_7_4_1_1_transfer_service.exceptions.UnAuthorizedException;
import com.example.hw_7_4_1_1_transfer_service.repository.TransferRepository;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    TransferRepository repository;

    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }

    public void transfer(String cardFromValidTill, String cardFromCVV,
                         String cardToNumber, Integer amount) {
        if (isEmpty(cardFromValidTill) || isEmpty(cardFromCVV)
                || isEmpty(cardToNumber)) {
            throw new InvalidCredentialsException("Error input data");
        }
        Account from = repository.findAccount(cardFromValidTill, cardFromCVV);
        if (from == null) {
            throw new UnAuthorizedException("From card not found");
        }
        Account to = repository.findAccount(cardToNumber);
        if (to == null) {
            throw new UnAuthorizedException("To card not found");
        }
        if (repository.transfer(from, to, amount)) {
            throw new ServerException("Unexpected server error");
        }
    }

    public String confirmOperation(String operationId, String code) {
        Operation operation = repository.confirmOperation(operationId, code);
        if (!operation.getFrom().canTransfer()) {
            throw new InvalidCredentialsException("Code not correct");
        }
        return operationId;
    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
