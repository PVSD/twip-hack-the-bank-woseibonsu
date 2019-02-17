package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by dpennebacker on 2/13/17.
 */
public class bankAccount implements Comparable  {

    private static FileWriter accountWriter;
    private static FileWriter transactionWriter;

    public bankAccount(String nm, double amt) throws IOException {
        accountWriter = new FileWriter("BANK_USERS.txt");
        transactionWriter = new FileWriter("TRANSACTION_LOG.txt");
        name = nm;
        balance = amt;
        accountWriter.write("NAME:\t" + name + "\tBALANCE:\t" + balance + "\n");
        transactionWriter.write(getDate() + "\tDEPOSIT:\t" + balance + "\n");
    }

    private String getDate()
    {
        Date date = new Date();
        return "[ " + date.toString() + " }";
    }

    public int compareTo(Object otherObject) {
        bankAccount otherAccount = (bankAccount) otherObject;
        int retValue;
        if (balance < otherAccount.balance) {
            retValue = -1;
        } else {
            if (balance > otherAccount.balance) {
                retValue = 1;
            } else {
                retValue = 0;
            }
        }
        return retValue;
    }

    public void deposit(double dp) throws IOException{
        balance = balance + dp;
        transactionWriter.write(getDate() + "\tDEPOSIT:\t" + dp + "\n");
    }

    public void withdraw(double wd) throws IOException{
        balance = balance - wd;
        transactionWriter.write(getDate() + "\tWITHDRAWAL:\t" + wd + "\n");
    }

    public String getName()
    {
        return name;
    }
    public double getBalance()
    {
        return balance;
    }
    private String name;
    private double balance;

}
