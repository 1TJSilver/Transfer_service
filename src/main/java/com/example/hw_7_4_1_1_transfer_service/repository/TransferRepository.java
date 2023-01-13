package com.example.hw_7_4_1_1_transfer_service.repository;

import com.example.hw_7_4_1_1_transfer_service.base.Account;
import com.example.hw_7_4_1_1_transfer_service.base.Operation;
import com.example.hw_7_4_1_1_transfer_service.exceptions.ServerException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransferRepository {
    private Map<String, Account> cardRepository;
    private Map<String, Operation> operationRepository;
    private Long idCounter;

    public TransferRepository() {
        cardRepository = new ConcurrentHashMap<>();
        operationRepository = new ConcurrentHashMap<>();
        idCounter = 0L;
    }

    public Account findAccount(String numberID, String cvv) {
        Account account = cardRepository.get(numberID);
        account.setTransferIfCvv(cvv);
        return account;
    }

    public Account findAccount(String numberID) {
        return cardRepository.get(numberID);
    }

    public boolean transfer(Account from, Account to, Integer amount) {
        if (from.remove(amount) && to.add(amount)) {
            idCounter++;
            operationRepository.put(idCounter.toString(),
                    new Operation(from, to, amount, idCounter.toString()));
            return true;
        }
        return false;
    }

    public Operation confirmOperation(String operationId, String code) {
        Operation operation = findOperation(operationId);
        if (operation == null) {
            throw new ServerException("Operation not found, check operation id");
        }
        operation.getFrom().setTransferIfCvv(code);
        return operation;
    }

    public Operation findOperation(String id) {
        return operationRepository.get(id);
    }
}
