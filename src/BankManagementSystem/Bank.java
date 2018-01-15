
package BankManagementSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Bank 
{
    String Bank_name;
    Customer Customer_obj;
    static String Account_PATH = System.getProperty("user.dir") +  System.getProperty ("file.separator") + "Account.txt";
    static String Customer_PATH = System.getProperty("user.dir") +  System.getProperty ("file.separator") + "Customer.txt";
    static String Transaction_PATH = System.getProperty("user.dir") +  System.getProperty ("file.separator") + "Transaction.txt";

    public Bank(String Bank_name) throws IOException
    {
        this.Bank_name = Bank_name;
        this.Customer_obj = new Customer();
        File file1 = new File(Account_PATH);
        File file2 = new File(Customer_PATH);
        File file3 = new File(Transaction_PATH);
        boolean a = file1.createNewFile();
        boolean b = file2.createNewFile();
        boolean c= file3.createNewFile();
        
    }
    
    public void setBankName()
    {
        /*File handling to write Banks Name in files */
    }
    public String getBankName()
    {
        return Bank_name;
    }
    public void SystemAdminMenu() throws IOException, ParseException 
    {
        Scanner user_input = new Scanner(System.in);
        System.out.println("Welcome, System Administrator!");
        System.out.println("Choose one of the following options:");
        System.out.println("1- Create New Customer and New Account");
        System.out.println("2- Create New Account for Existing Customer ");
        System.out.println("3- Close Existing Account");
        System.out.println("4- Search");
        System.out.println("5- Exit to Main Menu");
        int user_option = user_input.nextInt();
        if (user_option == 1)
        {
            Customer_obj.NewCustomer();
        }
        else if (user_option == 2)
        {
            user_input = new Scanner(System.in);
            String user_info1, user_info2, user_info3, user_info4;
            user_option = 0;
            int user_option2;
            System.out.println("First Name: ");
            user_info1 = user_input.nextLine();
            System.out.println("Last Name: ");
            user_info2 = user_input.nextLine();
            System.out.println("Password: ");
            user_info3 = user_input.nextLine();
            int valid = Customer_obj.LoginCustomer(user_info1, user_info2, user_info3) ;
            if (valid > 0)
            {
                user_input = new Scanner(System.in);
                System.out.println("Welcome, " + user_info1 + " " + user_info2+ " !");
                String temp = Integer.toString(valid);
                Customer_obj.ExistingCustomer_newAccount(temp);
                System.out.println("New Account Created!)");
            }
            else
            {
                throw new NoCustomerFound("Sorry, No Customer Found from the given Name and Password");
            }
        }
        
        else if(user_option == 3)
        {
            user_input = new Scanner(System.in);
            String user_info1, user_info2, user_info3, user_info4;
            System.out.println("First Name: ");
            user_info1 = user_input.nextLine();
            System.out.println("Last Name: ");
            user_info2 = user_input.nextLine();
            System.out.println("Password: ");
            user_info3 = user_input.nextLine();
            int valid = Customer_obj.LoginCustomer(user_info1, user_info2, user_info3) ;
            if (valid!=0)
            {            
                System.out.println("Welcome, " + user_info1 + " " + user_info2+ " !");
                System.out.println("Enter you Account ID");
                user_info4 = user_input.nextLine();
                System.out.println("Choose one of the following for your Account Type:");
                System.out.println("1- Basic Banking Account");
                System.out.println("2- Current Account");
                System.out.println("3- Savings Account");
                user_option = user_input.nextInt();
                if (user_option == 1)
                {
                    if (Customer_obj.AccountExists(user_option, "BasicBanking"))
                    {   
                        Customer_obj.CloseAccount(user_info4);
                    }
                    else 
                    {
                        throw new NoAccountFound("The Account Information provided did not match");
                    }
                }
                else if (user_option == 2)
                {
                    if (Customer_obj.AccountExists(user_option, "Current"))
                    {  
                        Customer_obj.CloseAccount(user_info4);
                    }
                    else 
                    {
                        throw new NoAccountFound("The Account Information provided did not match");
                    }
                }
                else if (user_option == 3)
                {
                    if (Customer_obj.AccountExists(user_option, "Savings"))
                    {   
                        Customer_obj.CloseAccount(user_info4);
                    }
                    else 
                    {
                        throw new NoAccountFound("The Account Information provided did not match");
                    }
                }
                else
                {
                    throw new BadInput("Invalid Input");
                }
            }
            else
            {
                throw new NoCustomerFound("No Customer Found from the given information");
            }
        }
        else if(user_option == 4)
        {
            user_input = new Scanner(System.in);
            System.out.println("Choose any of the following search queries: ");
            System.out.println("1- Output the information of all the customers");
            System.out.println("2- Output account details of the customers registered within last 24 hours (time limit can change)");
            System.out.println("3- Details of all the customers that own a particular type of account");
            user_option = user_input.nextInt();
            if (user_option == 1)
            {
                Customer_obj.CustomerInfo();
            }
            else if (user_option == 2)
            {
                System.out.println("Enter time limit in hours ");
                int user_option2 = user_input.nextInt();
                Customer_obj.AccountInfo_TimeLimt(user_option2);
            }
            else if (user_option == 3)
            {
                Customer_obj.CustomerInfo_AccountType();
            }
            else
            {
                throw new BadInput("Bad Input");
            }
        }
        else
        {
            throw new BadInput("Bad Input");
        }
    
    }
  public void CustomerMenu() throws IOException, FileNotFoundException, ParseException 
    {
        Scanner user_input = new Scanner(System.in);
        String user_info1, user_info2, user_info3;
        int user_info4, user_info5;
        int user_option = 0;
        String user_option2;
        System.out.println("First Name: ");
        user_info1 = user_input.nextLine();
        System.out.println("Last Name: ");
        user_info2 = user_input.nextLine();
        System.out.println("Password: ");
        user_info3 = user_input.nextLine();
        int valid = Customer_obj.LoginCustomer(user_info1, user_info2, user_info3) ;
        if (valid > 0)
        {
            System.out.println("Welcome, " + user_info1 + " " + user_info2+ " !");
            System.out.println("Enter you Account ID");
            user_info4 = user_input.nextInt();
            System.out.println("Choose one of the following for your Account Type:");
            System.out.println("1- Basic Banking Account");
            System.out.println("2- Current Account");
            System.out.println("3- Savings Account");
            user_option = user_input.nextInt();
            
            if (user_option == 1)
            {
                if (Customer_obj.AccountExists(user_option, "BasicBanking"))
                {
                    System.out.println("Choose one of the following options:");
                    System.out.println("1- Money Deposit");
                    System.out.println("2- Money Withdraw");
                    System.out.println("3- Balance Enquiry(E-Banking feature)");
                    System.out.println("4- View Statement of Account(E-Banking feature)");
                    user_info5 = user_input.nextInt();
                    if (user_info5 == 1)
                    {
                        if ((Customer_obj.Deposit(user_info4,"BasicBanking")))
                        {
                            System.out.println("Money Successfully Deposited!");
                        }
                    }
                    else if (user_info5== 2)
                    {
                        if (Customer_obj.Withdraw(user_info4,"BasicBanking"))
                        {
                            System.out.println("Money Withdraw Successful");
                        }  
                    }
                
                    else if(user_info5== 3)
                    {
                        Customer_obj.BalanceInquiry(user_option, "BasicBanking");
                    }
                    else if(user_info5== 4)
                    {   
                        String temp = Integer.toString(user_info4);
                        Customer_obj.ViewSOA(temp);
                    }
                    else
                    {
                        throw new BadInput("Invalid Input");
                    }
                }
                else
                {
                    throw new NoAccountFound ("The account doesnt exist");
                }
            }
            else if (user_option == 2)
            {
                if (Customer_obj.AccountExists(user_option, "Current"))
                {
                    System.out.println("Choose one of the following options:");
                    System.out.println("1- Money Deposit");
                    System.out.println("2- Money Withdraw");
                    user_info5 = user_input.nextInt();
                    if (user_info5 == 1)
                    {
                        if ((Customer_obj.Deposit(user_info4,"BasicBanking")))
                        {
                            System.out.println("Money Successfully Deposited!");
                        }
                    }
                    else if (user_info5== 2)
                    {
                        if (Customer_obj.Withdraw(user_info4,"BasicBanking"))
                        {
                            System.out.println("Money Withdraw Successful");
                        }  
                    }
                    else
                    {
                        throw new BadInput("Invalid Input");
                    }
                }
                else
                {
                    throw new NoAccountFound ("The account doesnt exist");
                }
            }
            else if (user_option == 3)
            {
                if (Customer_obj.AccountExists(user_option, "Savings"))
                {
                    System.out.println("Choose one of the following options:");
                    System.out.println("1- Money Deposit");
                    System.out.println("2- Money Withdraw");
                    user_info5 = user_input.nextInt();
                    if (user_info5 == 1)
                    {
                        if ((Customer_obj.Deposit(user_info4,"BasicBanking")))
                        {
                            System.out.println("Money Successfully Deposited!");
                        }
                    }
                    else if (user_info5== 2)
                    {
                        if (Customer_obj.Withdraw(user_info4,"BasicBanking"))
                        {
                            System.out.println("Money Withdraw Successful");
                        }  
                    }
                    else
                    {
                        throw new BadInput("Invalid Input");
                    }
                }
                else
                {
                    throw new NoAccountFound ("The account doesnt exist");
                }
            }
            else
            {
                throw new BadInput("Invalid Input");
            }
        }
        else
        {
            throw new NoCustomerFound ("No Customer Found with the given information");
        }
        user_input.close();

    }

  
    void BankManagerMenu() throws IOException, ParseException
    {
        System.out.println( "\t"+ "\t"+ "*" + "Detail Report" + "*");
        System.out.println("--------------------------------------------------------------------------------" ); 
        Customer_obj.NewAccounts();
        Customer_obj.ClosingBalance();
        Customer_obj.NumOfWithdraws();
        Customer_obj.SummaryofAccountType();
    }

    public static void main(String[] args) throws IOException, ParseException
    {
        String user_type = null;      //variable that stores the user accessng the system
        Bank bank = new Bank("FAST Bank");
        Scanner user_input10 = new Scanner(System.in); 
            System.out.println("\t"+ bank.getBankName());       
            System.out.println("Press the following for Login option:");
            System.out.println("1- Customer");
            System.out.println("2- Bank Manager");
            System.out.println("3- System Administrator");
            System.out.println("Press 0 to Exit");
            
            user_type = user_input10.nextLine();
            
            if (user_type.equals("1")) //Calls function where Customer choose furthur options
            {
                bank.CustomerMenu();
            }
            else if(user_type.equals("2"))
            {
                bank.BankManagerMenu();
              
            }
            else if(user_type.equals("3"))
            {
                bank.SystemAdminMenu();
            }
            else if(user_type.equals("0"))
            {
                System.out.println("Thank you for using FAST Bank :)");
            }
            else
            {
                throw new BadInput("Invalid Input");
            }
       
     
    }
}
