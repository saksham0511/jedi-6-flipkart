package com.flipkart.bean;

import com.flipkart.bean.Card;
import com.flipkart.constant.BankEnum;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.PaymentModeEnum;

/**
 *
 * @author JEDI-06
 * Payment Class
 *
 */

public class Payment {
   private int studentId;
   private PaymentModeEnum paymentModeEnum;
   private BankEnum bankEnum;
   private Card card;

   /**
     * Default Constructor;
    */

   public Payment(){
       card = new Card();
   }

    /**
     * Method to get StudentId
     * @return
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Method to set StudentId
     * @param studentId
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Method to get Payment Mode
     * @return
     */
    public PaymentModeEnum getPaymentModeEnum() {
        return paymentModeEnum;
    }

    /**
     * Method to set Payment Mode
     * @param paymentModeEnum
     */
    public void setPaymentModeEnum(PaymentModeEnum paymentModeEnum) {
        this.paymentModeEnum = paymentModeEnum;
    }

    /**
     * Method to get Bank Name
     * @return
     */
    public BankEnum getBankEnum() {
        return bankEnum;
    }

    /**
     * Method to set Bank Name
     * @param bankEnum
     */
    public void setBankEnum(BankEnum bankEnum) {
        this.bankEnum = bankEnum;
    }

    /**
     * Method to get Card Details
     * @return
     */
    public Card getCard() {
        return card;
    }

    /**
     * Method to set Card Details
     * @param card
     */
    public void setCard(Card card) {
        this.card = card;
    }
}
