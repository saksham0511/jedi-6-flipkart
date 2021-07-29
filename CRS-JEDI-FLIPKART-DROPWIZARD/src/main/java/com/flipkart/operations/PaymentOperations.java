package com.flipkart.operations;

import com.flipkart.DAO.PaymentDB;
import com.flipkart.DAO.PaymentDBInterface;

public class PaymentOperations implements PaymentInterface {
    @Override
    public int getPayment(int studId) {
        PaymentDBInterface paymentDBInterface = new PaymentDB();
        int amount=paymentDBInterface.getPaymentDB(studId);
        return amount;
    }


}
