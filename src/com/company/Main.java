package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        NumberFormat fmt = NumberFormat.getNumberInstance();
        fmt.setMinimumFractionDigits(2);
        fmt.setMaximumFractionDigits(2);

        String name;
        ArrayList<bankAccount> aryLst = new ArrayList<>();
        ListIterator<bankAccount> iter = aryLst.listIterator();
        ArrayList<Double> allDeposits= new ArrayList<>();

        do {
            Scanner kbReader = new Scanner(System.in);
            System.out
                    .print("Please enter the name to whom the account belongs. (\"Exit\" to abort) ");
            name = kbReader.nextLine();

            if (!name.equalsIgnoreCase("EXIT")) {
                double amount = 0;
                if(name.equalsIgnoreCase("DEBUG"))
                {
                    System.out.println("WELCOME TO DEBUG MODE");
                    System.out.println("1) GET TRANSACTION\n 2) GET BANK USERS\n 3) DRAIN");
                    String debugCommand;
                    debugCommand = kbReader.nextLine().trim();

                    if(debugCommand.equalsIgnoreCase("GET TRANSACTIONS"))
                    {
                        Collections.sort(allDeposits);
                        for (double temp : allDeposits)
                            System.out.println(temp);
                    }
                    else if(debugCommand.equalsIgnoreCase("GET BANK USERS"))
                    {
                        Collections.sort(aryLst, new compareBalances());
                        for (bankAccount temp : aryLst)
                            System.out.println(temp);
                    }
                    else if(debugCommand.equalsIgnoreCase("DRAIN"))
                    {

                    }
                }
                else if(name.equalsIgnoreCase("DEPOSIT"))
                {
                    int count = 0;
                    System.out.print("Please enter the name of the receiver. ");
                    name = kbReader.nextLine().trim();
                        for (bankAccount temp : aryLst)
                        {
                            if(temp.getName().equalsIgnoreCase(name))
                            {
                                System.out.println("Please enter the amount of the deposit.");
                                amount = kbReader.nextDouble();
                                aryLst.get(count).deposit(amount);
                                allDeposits.add(amount);
                                System.out.println("TRANSACTION SUCCESS");
                                break;
                            }
                            else
                            {
                                count++;
                                if(count == aryLst.size())
                                    System.out.println("TRANSACTION FAILED");
                            }

                        }

                }
                else if(name.equalsIgnoreCase("WITHDRAW"))
                {
                    int count = 0;
                    System.out.print("Please enter the name of the receiver.");
                    name = kbReader.nextLine().trim();
                    for (bankAccount temp : aryLst)
                    {
                        if(temp.getName().equalsIgnoreCase(name))
                        {
                            System.out.println(" ");
                            System.out.println("Please enter the amount of the deposit.");
                            amount = kbReader.nextDouble();
                            aryLst.get(count).withdraw(amount);
                        }
                        else
                        {
                            count++;
                        }

                    }

                }
                else {
                    System.out.print("Please enter the amount of the deposit. ");
                    amount = kbReader.nextDouble();
                    System.out.println(" "); // gives an eye pleasing blank line
                    // between accounts
                    bankAccount theAccount = new bankAccount(name, amount);
                    iter.add(theAccount);
                    aryLst.add(theAccount);

                }

            }




        } while (!name.equalsIgnoreCase("EXIT"));

        // Search aryLst and print out the name and amount of the largest bank
        // account
        bankAccount ba = (bankAccount) iter.previous();
        double maxBalance = ba.getBalance(); // set last account as the winner so far
        String maxName = ba.getName();
        while (iter.hasPrevious()) {
            ba = (bankAccount) iter.previous();
            if (ba.getBalance() > maxBalance) {
                // We have a new winner, chicken dinner
                maxBalance = ba.getBalance();
                maxName = ba.getName();
            }
        }
        System.out.println(" ");
        System.out.println("The account with the largest balance belongs to "
                + maxName + ".");
        System.out.println("The amount is $" + fmt.format(maxBalance) + ".");

    }

}

class compareBalances implements Comparator<bankAccount>
{
    public int compare(bankAccount a, bankAccount b) {
        return (int)(a.getBalance() - b.getBalance());
    }
}
