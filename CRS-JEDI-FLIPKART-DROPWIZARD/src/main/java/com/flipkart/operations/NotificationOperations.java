package com.flipkart.operations;

import com.flipkart.DAO.NotificationDB;
import com.flipkart.DAO.NotificationDBInterface;
import com.flipkart.bean.Card;
import com.flipkart.constant.BankEnum;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.PaymentModeEnum;

public class NotificationOperations implements NotificationInterface {
    @Override
    public int sendNotification(NotificationType type, int studentId, PaymentModeEnum modeOfPayment, BankEnum bankEnum, int amount, Card card) {
        NotificationDBInterface notificationDBInterface = new NotificationDB();
        return notificationDBInterface.sendNotification(type,studentId,modeOfPayment,bankEnum,amount,card);
    }

    @Override
    public boolean studentApproval(int studId) {
        NotificationDBInterface notificationDBInterface = new NotificationDB();
        return notificationDBInterface.studentApprovalDB(studId);
    }
}
