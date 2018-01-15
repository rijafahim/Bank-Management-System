
package BankManagementSystem;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Current extends Account
{
    public Current()
    {
        
    }

    public Current(String user_id, String account_id, String current_balance, String account_type, String date_registered, String currency, String annual_interest_rate) throws ParseException
    {
        super(user_id,account_id,current_balance,account_type,date_registered, currency,annual_interest_rate);
    }
        
    @Override
    boolean Withdraw()
    {    
        boolean ans = true;    
        try 
        {
            Scanner user_input = new Scanner(System.in);
            System.out.println("Enter amount to withdraw");
            String input = user_input.nextLine();
            System.out.println(input);
            double user_withdraw = Double.parseDouble(input);
            double balance = getCurrent_balance();
            if (((balance - user_withdraw) > 0))
            {
                balance = balance - user_withdraw;
                
            }
            else
            {
                System.out.println("You dont have enough money");
                ans = false;
            }
            if (ans == true)
            {
                String temp = Integer.toString(account_id);
                setCurrent_balance(balance);
                DecimalFormat df = new DecimalFormat("###,##0.00");
                String temp2 = df.format(balance);
                Date Transaction_Date = new Date();
                makeTransaction(temp, "Withdraw",input, temp2,Transaction_Date);
            }
        } 
        catch (IOException ex) 
        {
            
        }
        return ans;
    }

    @Override
    boolean openDeposit() 
    {
        boolean ans = true;
        try {
            
            Scanner user_input = new Scanner(System.in);
            System.out.println("Enter amount to deposit");
            String input = user_input.nextLine();
            System.out.println(input);
            double user_deposit = Double.parseDouble(input);
            double balance = getCurrent_balance();
            balance = balance + user_deposit;
            setCurrent_balance(balance);
            System.out.println(balance);
            String temp = Integer.toString(account_id);
            setCurrent_balance(balance);
            DecimalFormat df = new DecimalFormat("###,##0.00");
            String temp2 = df.format(balance);
            Date Transaction_Date = new Date();
            makeTransaction(temp, "Deposit",input, temp2,Transaction_Date);
            return ans;
        } 
        catch (IOException ex) {
           
        }
        return ans;
    }


    

}
