package com.flipkart.constant;

/**
 * This enum is used for payment mode
 */

public enum PaymentModeEnum {

    CREDIT_CARD,NET_BANKING,DEBIT_CARD,OFFLINE;

    /**
     * This method is used to get mode of payment
     * @param value
     * @return Payment Mode
     * */

    public static PaymentModeEnum getModeofPayment(int value)
    {
        switch(value)
        {
            case 1:
                return PaymentModeEnum.CREDIT_CARD;
            case 2:
                return PaymentModeEnum.NET_BANKING;
            case 3:
                return PaymentModeEnum.DEBIT_CARD;
            default:
                return null;

        }

    }
}
