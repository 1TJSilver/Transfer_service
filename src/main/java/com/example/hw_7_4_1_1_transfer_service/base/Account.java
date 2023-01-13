package com.example.hw_7_4_1_1_transfer_service.base;

import java.util.List;

public class Account{
    private String numberID;
    private String cvv;
    private List<Powers> powers;

    private Integer totalAmount;

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public boolean add (Integer increase){
        totalAmount += increase;
        return true;
    }

    public boolean remove (Integer increase){
        totalAmount -= increase;
        return true;
    }

    public List<Powers> getPowers() {
        return powers;
    }

    public String getNumberID() {
        return numberID;
    }

    public String getCvv() {
        return cvv;
    }

    public boolean canTransfer (){
        return powers.contains(Powers.TRANSFER);
    }
    public boolean setTransferIfCvv (String cvv){
        if (canTransfer()){
            return true;
        } else if (this.cvv.equals(cvv)){
            powers.add(Powers.TRANSFER);
            return true;
        } else {
            return false;
        }
    }
    public void setTransfer (){
        if (!canTransfer() && this.cvv.equals(cvv)){
            powers.add(Powers.TRANSFER);
        }
    }
}
