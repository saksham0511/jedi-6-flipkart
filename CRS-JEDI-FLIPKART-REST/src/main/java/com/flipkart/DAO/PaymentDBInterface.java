package com.flipkart.DAO;
/**
 * @author JEDI-06
 * Interface for Payment dao Operations
 *
 */
public interface PaymentDBInterface {
    /**
     * This method is used to get the total payment amount
     * @param studId
     * @return amount
     */
    public int getPaymentDB(int studId);

    /**
     * This method is used to check the payment status
     * @param paymentId
     */
    public void getStatusDB(int paymentId);
}
