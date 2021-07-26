package com.flipkart.input;

import java.util.Scanner;

public class ScannerInput {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Id: ");
        Integer customerId = sc.nextInt();
        System.out.println("Enter customer name ");
        String customerName = sc.nextLine();
        System.out.println(customerId + "and" +customerName);
    }
}
