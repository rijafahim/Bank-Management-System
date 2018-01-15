
package BankManagementSystem;
import BankManagementSystem.Account;
import static BankManagementSystem.Account.Account_PATH;
import java.io.*;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import static java.lang.System.in;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;


public class Customer 
{
    int customer_id;
    int cust_id = 0;            //generated via objects
    String firstname;
    String lastname;
    String address;
    int age;
    String gender;
    String DOB;
    Date date_registered;
    String occupation;
    String password;
    String Account_type;
    
    Account Customer_Account;
    static String Customer_PATH = System.getProperty("user.dir") +  System.getProperty ("file.separator") + "Customer.txt";
    static String Transaction_PATH = System.getProperty("user.dir") +  System.getProperty ("file.separator") + "Transaction.txt";
    List <Account> AccountList;
    
    public Customer()
    {
        
    }

    public Customer(String customer_id, String firstname, String lastname,  String password, String address,  String age, String gender, String DOB, String occupation, String Account_type, String date_registered) throws ParseException 
    {
        this.customer_id = Integer.parseInt(customer_id);
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.age = Integer.parseInt(age);
        this.gender = gender;
        this.DOB = DOB;
        SimpleDateFormat convert = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy");
        this.date_registered = convert.parse(date_registered);
        this.occupation = occupation;
        this.password = password;
        
        this.Account_type = Account_type;
    }
    public static void mapCustomer(Customer C, int type) throws IOException
    {
        File file = new File (Customer_PATH);
        File file2 = new File (System.getProperty("user.dir") +  System.getProperty ("file.separator") + "temp2.txt");
        BufferedWriter bw = null;
        try
        {
            if(type == 1)
            {
                bw = new BufferedWriter (new FileWriter(file,true));

            }
            else if(type ==2)
            {
                bw = new BufferedWriter (new FileWriter(file2,true));
            }
            String id = Integer.toString(C.getCustomer_id());
            bw.write(id + "\t");
            bw.write(C.getFirstname() + "\t");
            bw.write(C.getLastname() + "\t");
            bw.write(C.getPassword() + "\t");
            bw.write(C.getAddress() + "\t");
            id = Integer.toString(C.getAge());
            bw.write(id + "\t");
            bw.write(C.getGender() + "\t");
            bw.write(C.getDOB() + "\t");
            bw.write(C.getOccupation() + "\t");
            bw.write(C.getAccount_type() + "\t");
            SimpleDateFormat convert = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy");
            String S = convert.format(C.getDate_registered());
            bw.write(S + "\t");
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
    
    public List<Customer> getCustomerObjects() throws IOException, ParseException
    {
        List <Customer> Customer_Objs = new ArrayList<Customer>();
        FileReader in = new FileReader(Customer_PATH);
        BufferedReader br = new BufferedReader(in);
        String[] object;
        String temp;
        Customer C = null;
        String newline;
        try
        {
            while ((newline = br.readLine()) != null)
            {
                object = newline.split("\t");
                C = new Customer(object[0], object[1], object[2], object[3], object[4], object[5], object[6], object[7], object[8], object[9], object[10]);
                Customer_Objs.add(C);
            }
            br.close();
        }
        finally 
        {
             if (in != null)
            {
                in.close();  
            }
     
        }
      
        return Customer_Objs;
    }
    public int getCustomer_id() {
        return customer_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDOB() {
        return DOB;
    }

    public Date getDate_registered() {
        return date_registered;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPassword() {
        return password;
    }

    public String getAccount_type() {
        return Account_type;
    }

    public Account getCustomer_Account() {
        return Customer_Account;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setDate_registered(Date date_registered) {
        this.date_registered = date_registered;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccount_type(String Account_type) {
        this.Account_type = Account_type;
    }

    public void setCustomer_Account(Account Customer_Account) {
        this.Customer_Account = Customer_Account;
    }

    public void setCustomer_PATH(String Customer_PATH) {
        this.Customer_PATH = Customer_PATH;
    }
    public boolean Deposit(int account_id, String type) throws IOException, ParseException
    {
        boolean deposited = false;
        AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        int temp1 = 0;
        String temp2 = null;
        int index = -1;
        for (Account A: AccountList)
        {
            index++;
            A = AccountList.get(index);
            temp1 = A.getAccount_id();
            if (temp1 == account_id)
            {
                System.out.println("CALLED");
                deposited = A.deposit();
                Account.mapAccount(A,1);
            }
            else
            {
                Account.mapAccount(A,1);
            }
        }
        File file1 = new File(Account_PATH);
        File file2 = new File(System.getProperty("user.dir") +  System.getProperty ("file.separator") + "temp.txt");
        file1.delete();
        file2.renameTo(file1);
        return deposited;
    }
    public boolean Withdraw(int account_id, String type) throws IOException, ParseException
    {
        boolean withdraw = false;
        AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        int temp1 = 0;
        String temp2 = null;
        int index = -1;
        for (Account A: AccountList)
        {
            index++;
            A = AccountList.get(index);
            temp1 = A.getAccount_id();
            if (temp1 == account_id)
            {
                withdraw = A.Withdraw();
                Account.mapAccount(A,1);
            }
            else
            {
                Account.mapAccount(A,1);
            }
        }
        File file1 = new File(Account_PATH);
        File file2 = new File(System.getProperty("user.dir") +  System.getProperty ("file.separator") + "temp.txt");
        file1.delete();
        file2.renameTo(file1);
        return withdraw;
    }


    public void NewAccounts() throws IOException, ParseException
    {
        AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        Set<Integer> set = new HashSet<Integer>();
        int user = 0;
        int count = 0;
        int index = -1;
        for (Account A:AccountList)
        {
            index++;
            A = AccountList.get(index);
            user = A.getUser_id();
            Date D = A.getDate_created();
            String S = D.toString();
            
            SimpleDateFormat Df = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH);
            Calendar registered_date = Calendar.getInstance();
            registered_date.setTime(Df.parse(S));
                           
            Calendar Start = Calendar.getInstance();
            Start.set(Calendar.HOUR_OF_DAY, 8);
            Start.set(Calendar.MINUTE, 0);
            Start.set(Calendar.SECOND, 0);
            if (registered_date.after(Start))
            {
                set.add(user);
            }
        }
        count = set.size();
        System.out.println("Number of Customers that opened new accounts today: " + count);
    }
    public boolean AccountExists(int account_id, String type) throws IOException, ParseException
    {
        boolean exists = false;
        List <Account> AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        int temp1 = 0;
        String temp2 = null;
        int index = 0;

        for (Account A: AccountList)
        {
            A = AccountList.get(index);
            index++;
            temp1 = A.getAccount_id();
            temp2 = A.getAccount_type();
            if ((account_id == temp1) && type.equals(temp2))
            {
                exists = true;
                return exists;
            }           
        }
        return exists;
    }

     public void BalanceInquiry(int account_id, String type) throws IOException, ParseException
     {
        String ans = null;
        String currency = null;
        List <Account> AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        int temp1 = 0;
        String temp2 = null;
        int index = -1;
        for (Account A:AccountList)
        {
            index++;
            A = AccountList.get(index);
            temp1 = A.getAccount_id();
            temp2 = A.getAccount_type();
            if (account_id == temp1 && type.equals(temp2))
            {
                double temp = A.getCurrent_balance();
                DecimalFormat df = new DecimalFormat("###,##0.00");
                String temp3 = df.format(temp);
                currency = A.getCurrency();
                System.out.println("Current Balance: " + temp3 + " "+ currency );
            }           
        }
        
    }
    public int getNewCust_id() throws IOException, ParseException
    {   
        List <Customer> CustomerList = new ArrayList<Customer>();
        CustomerList = getCustomerObjects();
        Customer C = new Customer();
        int temp = 0;
        int max = 0;
        System.out.println(CustomerList.size());
        if (CustomerList.size() == 0)
        {
            max = 1;
            return max;
                 
        }
        else
        {
            for (int i=0; i<CustomerList.size(); i++)
            {
                C = CustomerList.get(i);
                temp = C.getCustomer_id();
                if (max<temp)
                {
                    max = temp;
                }
            }
            max++;
            return max;
        }
    }
    public void ClosingBalance() throws IOException, ParseException
    {
        List <Account> AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        int index = -1;
        String ans = null;
        double amount2 = 0;
        double amount = 0;
        String curr_type = null;
        double money = 0;
        double result, result2; 
        
        for (Account A:AccountList)
        {
            index++;
            A = AccountList.get(index);
            curr_type = A.getCurrency();
            if (curr_type.equals("Dollars"))
            {
                money = A.getCurrent_balance();
                result = Account.Dollar2Rupee(money);
                amount = amount + result; //amount represents ruppees.
                amount2 = amount2 + money; //amount2 represents the dollars
               
            }
            if(curr_type.equals("Ruppees"))
            {
                money = A.getCurrent_balance();
                result2 = Account.Rupee2Dollars(money);
                amount2 = amount2 + result2; //amount2 reps dollars, will add after converting
                amount = amount + money; //represents the ruppess
                
            }
        
        }
        System.out.println("Closing Balance: ");
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String temp = df.format(amount);
        System.out.println(temp + " Ruppees");
        df = new DecimalFormat("###,##0.00");
        temp = df.format(amount2);
        System.out.println(temp + " Dollars");
        
    }
    public void ExistingCustomer_newAccount(String id) throws IOException, ParseException
    {
        String temp = Account.makeAccount(id);
        List <Customer> CustomerList = new ArrayList<Customer>();
        CustomerList = getCustomerObjects();
        Customer C = new Customer();
        int temp2 = 0;
        String temp3 = null;
        String temp4 = null;
        for (int i=0; i<CustomerList.size(); i++)
        {
            C = CustomerList.get(i);
            temp2 = C.getCustomer_id();
            temp3 = Integer.toString(temp2);
            if (temp3.equals(id))
            {
                temp4 = C.getAccount_type();
                temp4 = temp4 + temp;
                System.out.println(temp4);
                C.setAccount_type(temp4);
                mapCustomer(C, 2);
            }
            else
            {
                mapCustomer(C, 2);
            }
        }
        File file1 = new File(Customer_PATH);
        File file2 = new File(System.getProperty("user.dir") +  System.getProperty ("file.separator") + "temp2.txt");
        file1.delete();
        file2.renameTo(file1);
        
    }
    public void CloseAccount(String id) throws IOException, ParseException
    {
        List <Account> AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        String temp = null;
        int index = -1;
        for (Account A:AccountList)
        {
            index++;
            A = AccountList.get(index);
            int temp2= A.getAccount_id();
            temp = Integer.toString(temp2);
            if (!temp.equals(id))
            {
                Account.mapAccount(A, 1);
            }           
        }
        
        File file1 = new File(Account_PATH);
        File file2 = new File(System.getProperty("user.dir") +  System.getProperty ("file.separator") + "temp.txt");
        file1.delete();
        file2.renameTo(file1);
        
        List <Customer> CustomerList = new ArrayList<Customer>();
        CustomerList = getCustomerObjects();
        Customer C = new Customer();
        for (int i=0; i<CustomerList.size(); i++)
        {
            C = CustomerList.get(i);
            int temp2 = C.getCustomer_id();
            String temp3 = Integer.toString(temp2);
            String temp4 = null;
            if (temp3.equals(id))
            {
                temp4 = C.getAccount_type();
                temp4 = temp4.replace(id,"");
                C.setAccount_type(temp4);
                mapCustomer(C, 2);
            }
            else
            {
                mapCustomer(C, 2);
            }
        }
        
        File file3 = new File(Customer_PATH);
        File file4 = new File(System.getProperty("user.dir") +  System.getProperty ("file.separator") + "temp2.txt");
        file3.delete();
        file4.renameTo(file3);
        
    }
    public void CustomerInfo_AccountType() throws IOException, ParseException
    {
        List <Integer> BasicBanking_Cust = new ArrayList <Integer>();
        List <Integer> Current_Cust = new ArrayList <Integer>();
        List <Integer> Savings_Cust = new ArrayList <Integer>();
        List <Account> AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        int index = -1;
        String temp = null;
        int temp2 = 0;
        for (Account A:AccountList)
        {
            index++;
            A = AccountList.get(index);
            temp = A.getAccount_type();
            temp2 = A.getUser_id();
            if (temp.equals("BasicBanking"))
            {
                BasicBanking_Cust.add(temp2);
            }
            else if (temp.equals("Current"))
            {
                Current_Cust.add(temp2);
            }
            else if (temp.equals("Savings"))
            {
                Savings_Cust.add(temp2);
            }
        }
        
        int temp3 = 0;
        List <Customer> CustomerList = new ArrayList<Customer>();
        CustomerList = getCustomerObjects();
        Customer C = new Customer();
        if (CustomerList.size() == 0)
        {
            System.out.println("No Customers to show");
        }
        else
        {    
            System.out.println("\t" + "\t" + "Basic Banking Customers");
            System.out.println("-------------------------------------------------------------------------------------" );
            for (int i=0; i<CustomerList.size(); i++)
            {
                C = CustomerList.get(i);
                temp3 = C.getCustomer_id();
                if (BasicBanking_Cust.contains(temp3))
                {
                     C.print();
                     
                }
                
            }
            
            System.out.println("\t" + "\t" + "Current Account Customers");
            System.out.println("-------------------------------------------------------------------------------------" );
            for (int i=0; i<CustomerList.size(); i++)
            {
                C = CustomerList.get(i);
                temp3 = C.getCustomer_id();
                if (Current_Cust.contains(temp3))
                {
                     C.print();
                }
            }
            
            System.out.println("\t" +"\t" +" Savings Account Customers");
            System.out.println("-------------------------------------------------------------------------------------" );
            for (int i=0; i<CustomerList.size(); i++)
            {
                C = CustomerList.get(i);
                temp3 = C.getCustomer_id();
                if (Savings_Cust.contains(temp3))
                {
                     C.print();
                }
            }
            
            
        }
        
    }
    
    public void SummaryofAccountType() throws IOException, ParseException
    {
        List <Integer> BasicBanking_Cust = new ArrayList <Integer>();
        List <Integer> BasicBanking_Acc_id = new ArrayList <Integer>();
        
        List <Integer> Current_Cust = new ArrayList <Integer>();
        List <Integer> Current_Acc_id = new ArrayList <Integer>();
        
        List <Integer> Savings_Cust = new ArrayList <Integer>();
        List <Integer> Savings_Acc_id = new ArrayList <Integer>();
        
        List <Account> AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        int index = -1;
        String temp = null;
        int temp2 = 0;
        int temp3 = 0;
        for (Account A:AccountList)
        {
            index++;
            A = AccountList.get(index);
            temp = A.getAccount_type();
            temp2 = A.getUser_id();
            temp3 = A.getAccount_id();
            if (temp.equals("BasicBanking"))
            {
                BasicBanking_Cust.add(temp2);
                BasicBanking_Acc_id.add(temp3);
            }
            else if (temp.equals("Current"))
            {
                Current_Cust.add(temp2);
                Current_Acc_id.add(temp3);
            }
            else if (temp.equals("Savings"))
            {
                Savings_Cust.add(temp2);
                Savings_Acc_id.add(temp3);
            }
        }
        
        temp3 = 0;
        List <Customer> CustomerList = new ArrayList<Customer>();
        CustomerList = getCustomerObjects();
        Customer C = new Customer();
        if (CustomerList.size() == 0)
        {
            System.out.println("No Customers to show");
        }
        else
        {  

            System.out.println( "\t "+"\t" + "*" +"Basic Banking Account" + "*");
            System.out.println("---------------------------------------------------------------------------------------" ); 
            
            for (int i=0; i<BasicBanking_Cust.size(); i++)
            {

                for (int j=0; j<CustomerList.size(); j++)
                {
                    C = CustomerList.get(j);
                    temp3 = C.getCustomer_id();
                    if (BasicBanking_Cust.get(i) == temp3)
                    {
                        C.print();
                        String var = Integer.toString(BasicBanking_Acc_id.get(i));
                        print_Transactions(var);
                    } 
                }
                
            }

            System.out.println("\t "+"\t" + "*"  +"Current Account" + "*");
            System.out.println("---------------------------------------------------------------------------------------" ); 
            for (int i=0; i<Current_Cust.size(); i++)
            {
                for (int j=0; j<CustomerList.size(); j++)
                {
                    C = CustomerList.get(j);
                    temp3 = C.getCustomer_id();
                    if (Current_Cust.get(i) == temp3)
                    {
                        C.print();
                        String var = Integer.toString(Current_Acc_id.get(i));
                        print_Transactions(var);
                    } 
                }
                
            }
                      
            System.out.println( "\t "+"\t" + "*"+" Savings Account" + "*");
            System.out.println("---------------------------------------------------------------------------------------" ); 
            for (int i=0; i<Savings_Cust.size(); i++)
            {
                for (int j=0; j<CustomerList.size(); j++)
                {
                    C = CustomerList.get(j);
                    temp3 = C.getCustomer_id();
                    if (Savings_Cust.get(i) == temp3)
                    {
                        C.print();
                        String var = Integer.toString(Savings_Acc_id.get(i));
                        print_Transactions(var);
                    } 
                }
                
            }
  
        }
        
    }
    public void NumOfWithdraws() throws FileNotFoundException, IOException, ParseException
    {
        int count = 0;
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

                SimpleDateFormat Df = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH);
                Calendar Date_Withdraw = Calendar.getInstance();
                Date_Withdraw.setTime(Df.parse(object[5]));
                           
                Calendar Start = Calendar.getInstance();
                Start.set(Calendar.HOUR_OF_DAY, 8);
                Start.set(Calendar.MINUTE, 0);
                Start.set(Calendar.SECOND, 0);
                if (object[2].equals("Withdraw") && Date_Withdraw.after(Start))
                {
                      count++;
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
        System.out.println("Number of Withdraws: " + count);
    }
    public void ViewSOA(String account_id) throws IOException, ParseException
    {
        List <Account> AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        int index = -1;
        int id = 0;
        for (Account A:AccountList)
        {
            index++;
            A = AccountList.get(index);
            id = A.getAccount_id();
            String temp = Integer.toString(id);
            if (temp.equals(account_id))
            {
                A.SOA(account_id);
            }
          
        }
        
    }
    public void AccountInfo_TimeLimt(int hours) throws IOException, ParseException
    {
        List <Account> AccountList = new ArrayList<Account>();
        AccountList = Account.getAccountObjects();
        int index = -1;
        for (Account A:AccountList)
        {
            index++;
            A = AccountList.get(index);
            Date registered_date =A.getDate_created();
            Date deadline = new Date(new Date().getTime() - (24*60*60*1000));
            if (registered_date.after(deadline))
            {
                System.out.println("Customer ID: " + A.getUser_id());
                System.out.println("Account ID: " + A.getAccount_id());
                double temp = A.getCurrent_balance();
                DecimalFormat df = new DecimalFormat("###,##0.00");
                String temp3 = df.format(temp);
                System.out.println("Balance: " + temp3);
                System.out.println("Type: " + A.getAccount_type());
                System.out.println("Date Registered : " + A.getDate_created());
                System.out.println("Currency : " + A.getCurrency());
                temp = A.getAnnual_interest_rate();
                df = new DecimalFormat("###,##0.00");
                temp3 = df.format(temp);
                System.out.println("Annual Interest Rate: " + temp3);
                System.out.println("----------------------------------------------------------" );
            }
          
        }
        
    }
    public void CustomerInfo() throws IOException, ParseException
    {
        List <Customer> CustomerList = new ArrayList<Customer>();
        CustomerList = getCustomerObjects();
        Customer temp = new Customer();
        for (int i=0; i<CustomerList.size(); i++)
        {
            temp = CustomerList.get(i);
            temp.print();

        }
    }
    public void NewCustomer() throws IOException, ParseException
    {
        System.out.print("2hoowehjg");
        Scanner user_input = new Scanner(System.in);
        String user_info1, user_info2, user_info3, user_info4, user_info5, user_info6, user_info7, user_info8, user_info9, user_info10, user_info11, user_info12, user_info13;
  
        cust_id = getNewCust_id();
        user_info1 = Integer.toString(cust_id);
        System.out.println("Enter First Name:");
        user_info2 = user_input.nextLine();
        
        System.out.println("Enter Last Name:");
        user_info3 = user_input.nextLine();
        
        System.out.println("Enter Password:");
        user_info4 = user_input.nextLine();
        
        System.out.println("Enter Address:");
        user_info5 = user_input.nextLine();
        
        System.out.println("Enter Age:");  
        user_info6 = user_input.nextLine();
        
        System.out.println("Enter Gender:");
        user_info7 = user_input.nextLine();
        
        System.out.println("Enter Date of Birth(dd/mm/yy):");  
        user_info8 = user_input.nextLine();
        
        System.out.println("Enter Occupation:");  
        user_info9 = user_input.nextLine();
       
        
        SimpleDateFormat convert = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy");
        user_info11 = convert.format(new Date());
        
        user_info10 = Account.makeAccount(user_info1);
        
        Customer Cust = new Customer(user_info1,user_info2, user_info3, user_info4, user_info5, user_info6, user_info7, user_info8, user_info9, user_info10, user_info11);
        System.out.println(Cust.getCustomer_id() + Cust.getFirstname());
        mapCustomer(Cust,1);

    }
    public int LoginCustomer(String firstname, String lastname, String password) throws FileNotFoundException, IOException, ParseException
    {
        int valid = -1;
        List <Customer> CustomerList = new ArrayList<Customer>();
        CustomerList = getCustomerObjects();
        Customer temp = new Customer();
        String temp2;
        String temp3;
        String temp4;
        for (int i=0; i<CustomerList.size(); i++)
        {
            temp = CustomerList.get(i);
            temp2 = temp.getFirstname();
            temp3 = temp.getLastname();
            temp4 = temp.getPassword();
            if (firstname.equals(temp2) && lastname.equals(temp3) && password.equals(temp4))
            {
                valid = temp.getCustomer_id();
                return valid;
            }
        }
        return valid;
    }
    public void print()
    {
        System.out.println("Customer ID: " + getCustomer_id());
        System.out.println("First Name: " + getFirstname());
        System.out.println("Last Name: " + getLastname());
        System.out.println("Address: " + getAddress());
        System.out.println("Age: " + getAge());
        System.out.println("Gender : " +getGender());
        System.out.println("Date of Birth : " + getDOB());
        System.out.println("Occupation : " + getOccupation());
        String temp2 = getAccount_type();
        System.out.println("Account Types:");
            
        if (temp2.contains("1"))
        {
            System.out.println(" - Basic Banking " );
        }
        if (temp2.contains("2"))
        {
            System.out.println(" - Current" );
        }
        if (temp2.contains("3"))
        {
            System.out.println(" - Savings" );
        }
        
            
        System.out.println("Registeration Date:  " + getDate_registered());
        System.out.println("-------------------------------------------------------------------------------------" );
    }
    public void print_Transactions(String account_id) throws IOException, ParseException
    {
        int count = 0;
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

                SimpleDateFormat Df = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZ yyyy", Locale.ENGLISH);
                Calendar Date_Withdraw = Calendar.getInstance();
                Date_Withdraw.setTime(Df.parse(object[5]));
                           
                Calendar Start = Calendar.getInstance();
                Start.set(Calendar.HOUR_OF_DAY, 8);
                Start.set(Calendar.MINUTE, 0);
                Start.set(Calendar.SECOND, 0);
                if (object[1].equals(account_id) && Date_Withdraw.after(Start))
                {
                    System.out.println( "\t" + "Account ID" + "\t" + "Description" + "\t" + "Amount" + "\t" + "Balance" + "\t" + "Date of Transaction");
                    System.out.println( "\t" + object[1] + "\t" + object[2] + "\t" + object[3] + "\t" + object[4] + "\t" + object[5]);
                    System.out.println("\t"+ "-----------------------------------------------------------------------------" ); 
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
