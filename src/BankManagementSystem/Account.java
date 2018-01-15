
package BankManagementSystem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.in;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public abstract class Account          /*An abstract class for account to represent the basic Account */
{
    int user_id;
    static int acc_id;
    static int transaction_no;
    int account_id;
    double current_balance;
    String account_type;
    Date date_registered;
    String currency;
    double annual_interest_rate;
    static String Account_PATH = System.getProperty("user.dir") +  System.getProperty ("file.separator") + "Account.txt";
    static String Transaction_PATH = System.getProperty("user.dir") +  System.getProperty ("file.separator") + "Transaction.txt";
    public Account()
    {
        
    }
    public void SOA(String temp2) throws IOException
    {
        System.out.println("IN SOA");
        BasicBanking A = new BasicBanking();
        A.StatementofAccount(temp2);
    }
    public Account(String user_id, String account_id, String current_balance, String account_type, String date_registered, String currency, String annual_interest_rate) throws ParseException 
    {
        this.user_id = Integer.parseInt(user_id);
        this.account_id = Integer.parseInt(account_id);
        current_balance = current_balance.replace(",", "");
        this.current_balance = Double.valueOf(current_balance);
        this.account_type = account_type;
        SimpleDateFormat convert = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy");
        this.date_registered = convert.parse(date_registered);
        this.currency = currency;
        this.annual_interest_rate = Double.valueOf(annual_interest_rate);
      
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    public static List<Account> getAccountObjects() throws IOException, ParseException
    {
        List <Account> Account_Objs = new ArrayList<Account>();
        FileReader in = new FileReader(Account_PATH);
        BufferedReader br = new BufferedReader(in);
        String[] object;
        String temp;
        Account A = null;
        String newline;
        try
        {
            while ((newline = br.readLine()) != null)
            {
                object = newline.split("\t");
                if (object[3].equals("BasicBanking"))
                {
                    A = new BasicBanking(object[0], object[1], object[2], object[3], object[4], object[5], object[6]);
                    Account_Objs.add(A);
                }
                else if (object[3].equals("Current"))
                {
                    A = new Current(object[0], object[1], object[2], object[3], object[4], object[5], object[6]);
                    Account_Objs.add(A);
                }
                else if(object[3].equals("Savings"))
                {
                    A = new Savings(object[0], object[1], object[2], object[3], object[4], object[5], object[6]);
                    Account_Objs.add(A);
                }

            }
            
        }
                
        finally 
        {
             if (in != null)
            {
                in.close();  
            }
     
        }

        return Account_Objs;
    }
    public static int getNewAcc_No() throws IOException, ParseException
    {
        List <Account> AccountList = new ArrayList<Account>();
        AccountList = getAccountObjects();
        int temp = 0;
        int index = 0;
        int max = 0;
        if (AccountList.size() == 0)
        {
            max = 1;
            return max;
        }
        else
        {
            for (Account A: AccountList)
            {
                A = AccountList.get(index);
                index++;
                temp = A.getAccount_id();
                if (temp>max)
                {
                    max = temp;
                }           
            }
            max++;
            return max;
        }
    }
    public static String makeAccount(String user_id) throws ParseException, IOException
    {
        Scanner user_input = new Scanner(System.in);
        Account A = null;
        acc_id = getNewAcc_No(); 
        String temp = Integer.toString(acc_id);
        System.out.println("Choose one of the following account you wish to open:"); 
        System.out.println("1- Basic Banking Account");
        System.out.println("2- Current Account");
        System.out.println("3- Savings Account");
        String type = user_input.nextLine();
        SimpleDateFormat convert = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy");
        String temp2 = convert.format(new Date());
        String temp3 = null;
        System.out.println("Currency? Press R for Pakistani Ruppees and D for Dollars");
        
        temp3 = user_input.nextLine();
        if (temp3.equals("R") || temp3.equals("r") )
        {
            temp3 = "Ruppees";            
        }
        else if (temp3.equals("D") || temp3.equals("d"))
        {
            temp3 = "Dollars";
        }
        
        System.out.println("Enter the interest rate?");
        String temp4 = user_input.nextLine();
 
        if (type.equals("1"))
        {
            A = new BasicBanking(user_id, temp,"0","BasicBanking", temp2, temp3, temp4);
            A.openDeposit();
            mapAccount(A, 2);
        }
        else if (type.equals("2"))
        {
            A = new Current(user_id, temp,"0","Current", temp2, temp3, temp4);
            A.openDeposit();
            mapAccount(A, 2);
        }
        else if (type.equals("3"))
        {
            A = new Savings(user_id, temp,"0","Savings", temp2, temp3, temp4);
            A.openDeposit();
            mapAccount(A, 2);
        } 
        return type;
    }
    public static void mapAccount(Account A, int type) throws IOException
    {
        File file = new File (Account_PATH);
        File file2 = new File (System.getProperty("user.dir") +  System.getProperty ("file.separator") + "temp.txt");
        BufferedWriter bw = null;
        try
        {
            if (type == 1) /*used for deleting and editing information*/
            {
                bw = new BufferedWriter(new FileWriter(file2,true));
            }
            else if (type == 2) /*used for appending only. Opening new accounts*/
            {
                bw = new BufferedWriter(new FileWriter(file,true));
            }
            
            String id = Integer.toString(A.getUser_id());
            bw.write(id + "\t");
                
            id = Integer.toString(A.getAccount_id());
            bw.write( id + "\t");
                
            double temp = A.getCurrent_balance();
            DecimalFormat df = new DecimalFormat("###,##0.00");
            String temp3 = df.format(temp);
            bw.write(temp3+ "\t");
                
            SimpleDateFormat convert = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy");
            String S = convert.format(A.getDate_created());
             
            bw.write(A.getAccount_type() + "\t");
            bw.write(S + "\t");
            bw.write(A.getCurrency() + "\t");
                
            temp = A.getAnnual_interest_rate();
            df = new DecimalFormat("###,##0.00");
            temp3 = df.format(temp);
            bw.write(temp3+ "\t");
                
            bw.newLine();
            bw.close();
        }        
        finally 
        {
            if (in != null)
            {
                in.close();  
            }
     
        }
             
    }
    boolean deposit() throws FileNotFoundException, IOException
    {
        boolean ans = true;
        Scanner user_input1 = new Scanner(System.in);
        System.out.println("Enter amount to deposit");
        String input = user_input1.nextLine();
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
        makeTransaction(temp, "Deposit", input, temp2,Transaction_Date);
        return ans;
    }


    public int getAccount_id() {
        return account_id;
    }

    public double getCurrent_balance() {
        return current_balance;
    }

    public String getAccount_type() {
        return account_type;
    }

    public Date getDate_created() {
        return date_registered;
    }

    public String getCurrency() {
        return this.currency;
    }

    public double getAnnual_interest_rate() {
        return annual_interest_rate;
    }

    public String getAccount_PATH() {
        return Account_PATH;
    }


    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setCurrent_balance(double current_balance) {
        this.current_balance = current_balance;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public void setDate_created(Date date_created) {
        this.date_registered = date_created;
    }

    public void setDollars(boolean dollars) {
        this.currency = "Dollars";
    }

    public void setAnnual_interest_rate(double annual_interest_rate) {
        this.annual_interest_rate = annual_interest_rate;
    }

    public void setAccount_PATH(String Account_PATH) {
        this.Account_PATH = Account_PATH;
    }
    public int getTransaction_no() throws FileNotFoundException, IOException
    {
        File file1 = null;
        FileReader in = null;
        int num = 0;
        String newline = null;
        try
        {
            file1 = new File(Transaction_PATH);
            in = new FileReader(Transaction_PATH);
            BufferedReader br = new BufferedReader(in);
            while ((newline = br.readLine()) != null)
            {
                num++;
            }
            br.close();
        }
        finally
        {
            if (in!=null)
            {
                in = null;
            }
        }
        num++;
        return num;
    }
     public void makeTransaction(String account_id, String description, String user_input, String amount, Date D) throws IOException
    {
        transaction_no = getTransaction_no();
        File FilePath = new File(Transaction_PATH);
        FileWriter out = null;
        try
        {
            String filenum = Integer.toString(transaction_no);
            BufferedWriter bw = new BufferedWriter (new FileWriter(FilePath,true));
            bw.write(filenum + "\t");
            
            int temp = getAccount_id();
            String temp2 = Integer.toString(temp);
            bw.write(temp2 + "\t");
      
            bw.write(description +  "\t");
            bw.write(user_input + "\t");
            bw.write(amount + "\t");

            String temp3 = D.toString();
            bw.write(temp3 + "\t");
            bw.newLine();
            bw.close();
        }
        finally 
        {
            if (out != null)
            {
                 out.close();  
            }
        }
    }
    static double Dollar2Rupee(double money)
    {
        return (money*104.33);
    }  
    
    static double Rupee2Dollars(double money)
    {
        return (money/104.33);
    } 
    
    abstract boolean openDeposit();
    abstract boolean Withdraw();
}
