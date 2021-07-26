package com.flipkart.DAO;

import com.flipkart.bean.Card;
import com.flipkart.constant.*;

import java.sql.SQLException;

/**
 * @author JEDI-06
 * Interface for Notification dao Operations
 *
 */

public interface NotificationDBInterface {
    /**
     * This method is used to send payment notification to student
     * @param type
     * @param studentId
     * @param modeOfPayment
     * @param bankEnum
     * @param amount
     * @param card
     * @return
     */
    public int sendNotification(NotificationType type, int studentId, PaymentModeEnum modeOfPayment, BankEnum bankEnum, int amount, Card card);

    /**
     * This method is used to check if student has paid the fee or not
     * @param studId
     * @return
     */
    public boolean studentApprovalDB(int studId) ;

}
