
package BankManagementSystem;

import static BankManagementSystem.Account.transaction_no;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicBanking extends Account
{
    int withdraw_amount;
    public BasicBanking()
    {
        
    }
  
    public BasicBanking(String user_id, String account_id, String current_balance, String account_type, String date_registered, String currency, String annual_interest_rate) throws ParseException
    {
        super(user_id,account_id,current_balance,account_type,date_registered, currency,annual_interest_rate);
    }

    boolean CheckLimit() throws IOException, ParseException
    {
        double limit = 0;
        File FilePath = new File(Transaction_PATH);
        FileWriter out = null;
        String newline = null;
        try
        {
            BufferedReader br = new BufferedReader (new FileReader(FilePath));
            while ((newline = br.readLine()) != null)
            {
                String[] object = newline.split("\t");
                int temp = getAccount_id();
                String temp2 = null;
                temp2 = Integer.toString(temp);
                if (object[1].equals(temp2) && object[2].equals("Withdraw"))
                {
                    String date_withdraw = object[5];
                    SimpleDateFormat convert = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy");
                    Date transaction_date = convert.parse(date_withdraw);
                    Date deadline = new Date(new Date().getTime() - (24*60*60*1000));
                    if (transaction_date.after(deadline))
                    {
                        double amount = Double.valueOf(object[3]);
                        limit = limit + amount;
                    }
                }
            }
            br.close();
        }
        finally 
        {
            if (out != null)
            {
                 out.close();  
            }
        }
        System.out.println("Limit for today" + limit);
        if (limit>30000)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

      
    @Override
    boolean Withdraw()
    {
        boolean ans = true;
        try {
            
            Scanner user_input = new Scanner(System.in);
            System.out.println("Enter amount to withdraw");
            String input = user_input.nextLine();
            System.out.println(input);
            double user_withdraw = Double.parseDouble(input);
            double balance = getCurrent_balance();
            if (CheckLimit())
            {
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
                    setCurrent_balance(balance);
                    String temp = Integer.toString(account_id);
                    setCurrent_balance(balance);
                    DecimalFormat df = new DecimalFormat("###,##0.00");
                    String temp2 = df.format(balance);
                    Date Transaction_Date = new Date();
                    makeTransaction(temp, "Withdraw",input, temp2,Transaction_Date);
                }
            }
            else
            {
                System.out.println("You have exceeded today's withdraw limit");
            }
           
        } 
        catch (IOException ex) 
        {
            
        } 
        catch (ParseException ex) 
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
            System.out.println("Enter atleast Rs. 1,000 to deposit");
            String input = user_input.nextLine();
            double user_deposit = Double.parseDouble(input);
            double balance = getCurrent_balance();
            balance = balance + user_deposit;
            while (balance<1000)
            {
                System.out.println("Enter atleast Rs. 1,000 to deposit");
                input = user_input.nextLine();
                System.out.println(input);
                user_deposit = Double.parseDouble(input);
                balance = getCurrent_balance();
                balance = balance + user_deposit;
            }
            setCurrent_balance(balance);
            System.out.println(balance);
            setCurrent_balance(balance);
            System.out.println(balance);
            String temp = Integer.toString(account_id);
            setCurrent_balance(balance);
            DecimalFormat df = new DecimalFormat("###,##0.00");
            String temp2 = df.format(balance);
            Date Transaction_Date = new Date();
            makeTransaction(temp, "Deposit", input, temp2,Transaction_Date);
        } catch (IOException ex) {
            
        }
        return ans;
    }
    void StatementofAccount(String temp2) throws FileNotFoundException, IOException
    {
        File FilePath = new File(Transaction_PATH);
        FileWriter out = null;
        String newline = null;
        int counter = 0;
        try
        {

            BufferedReader br = new BufferedReader (new FileReader(FilePath));
            while ((newline = br.readLine()) != null)
            {
                String[] object = newline.split("\t");
                if (object[1].equals(temp2))
                {
                    counter++;
                    if (counter == 1)
                    {
                        System.out.println( "\t" + "Statement of Account of "+ object[1]);
                        System.out.println("----------------------------------------------------------");
                    }
                    System.out.println("Transaction Number: " + counter);
                    System.out.println("Transaction Type : " + object[2]);
                    System.out.println("Amount : " + object[3]);
                    System.out.println("Current Balance : " + object[4]);
                    System.out.println("Date of Transaction : " + object[5]);
                    System.out.println("----------------------------------------------------------");
                }
            }
            br.close();
        }
        finally 
        {
            if (out != null)
            {
                 out.close();  
            }
        }
    }
}
