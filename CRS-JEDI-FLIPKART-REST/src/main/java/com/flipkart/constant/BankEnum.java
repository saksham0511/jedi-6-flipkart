package com.flipkart.constant;

/**
 * This enum is used for offline payment
 */

public enum BankEnum {

    SBI,ICICI,AXIS,CASH;

    /**
     * This method is used to get Bank Name
     * @param value
     * @return Bank Name
     * */

    public static BankEnum getBankName(int value)
    {
        switch(value)
        {
            case 1:
                return BankEnum.SBI;
            case 2:
                return BankEnum.ICICI;
            case 3:
                return BankEnum.AXIS;
            default:
                return null;

        }

    }
}
