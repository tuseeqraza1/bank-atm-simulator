package semesterproject;

import java.util.*;
import java.io.*;

public class SemesterProject 
{
    private static final File file = new File("BankAccountDetails.txt");
    private static final Scanner input = new Scanner(System.in);
    
    private static final String[][] data_array = new String[100][8];
    
    private static String username;
    private static String fathername;
    private static String age;
    private static String password;
    private static String cnic;
    private static String address;
    private static String amount;
    private static int user_idx;
    
    public static void main(String[] args)
    {
        if (!file.exists()) 
        {
            try 
            {
                file.createNewFile();
            }
            catch (IOException e) 
            {
                System.out.println(e);
            }
        }
        showoptions();
        
        System.out.println("===================================================="
                        +"\n        Thank you for choosing CIIT Bank.ltd"
                        +"\n====================================================");
    }
    
    public static void showoptions()
    {
        while(true)
        {
            System.out.println("===================================================="
                            +"\n              Welcome to CIIT Bank.ltd"
                            +"\n===================================================="
                            +"\nWhat would you want to do."
                            +"\nPress,"
                            +"\n1 for Manager Login."
                            +"\n2 for Banking."
                            +"\n3 for ATM."
                            +"\n4 for Exit.");
            
            int op = 0;
            try
            {
                op = input.nextInt();  
            }
            catch(Exception e)
            {
                System.out.println("An interger is required.");
                input.nextLine();
            }
            
            if(op==1)//new acoount
            {
                input.nextLine();
                Managerlogin();
                System.out.println("====================================================\n");
            }
            else if(op==2)//banking
            {
                Banking();
                System.out.println("====================================================\n");

            }
            else if(op==3)//atm
            {
                input.nextLine();
                ATM();
                System.out.println("====================================================\n");
                
            }
            else if(op==4)
            {
                break;
            }
            else
            {
                System.out.println("Invalid input.");
            }
        }   
    }
    
    public static void Managerlogin()
    {
        while(true)
        {
            DataEntryFileTo2DArray();
            
            System.out.println("===================================================="
                            +"\n                Enter Manager Login"
                            +"\n====================================================");
            System.out.print("Manager Name:\t");
            String manager_name = input.nextLine();
            System.out.print("\nPassword:\t");
            String manager_password = input.nextLine();

            if((manager_name.equalsIgnoreCase(data_array[0][0])) && (manager_password.equalsIgnoreCase(data_array[0][3])))
            {
                System.out.println("\nManager Login Successful.");
                ManagerOptions();
                break;
            }
        }
    }
    
    public static void ManagerOptions()
    {
        while(true)
        {
            System.out.println("===================================================="
                            +"\n                  Manager Option"
                            +"\n===================================================="
                            +"\nWhat would you want to do."
                            +"\nPress,"        
                            +"\n1 for Account Searching."
                            +"\n2 for New Account."
                            +"\n3 for Deleting Account."
                            +"\n4 for Manager Logout.");
            int op = 0;
            try
            {
                op = input.nextInt();  
            }
            catch(Exception e)
            {
                System.out.println("An interger is required.");
            }
            
            if(op==1)
            {
                input.nextLine();
                AccountSearch();
            }
            else if(op==2)
            {
                input.nextLine();
                NewAccount();
                System.out.println("====================================================\n");

            }
            else if(op==3)
            {
                DeleteAccount();
                System.out.println("====================================================\n");
                
            }
            else if(op==4)
            {
                username = null;
                fathername = null;
                age = null;
                password = null;
                cnic = null;
                address = null;
                amount = null;
                user_idx = 0;
                break;
            }
            else
            {
                input.nextLine();
                System.out.println("Invalid input.");
            }
        }
    }
    
    public static void AccountSearch()
    {
        boolean check = true;
        while(check)
        {
            System.out.println("\nEnter the Name of Account Holder.");
            String Acc_name = input.nextLine();
            System.out.println("\nEnter the CNIC of Account Holder.");
            String Acc_cnic = input.nextLine();

            for(int i = 0 ; i < 100 ; i++)
            {
                if(data_array[i][0] == null)
                {
                    System.out.println("\nData not found,\n"
                            + "Please re-Enter Account Holder detailes.");
                    break;
                }
                else if((data_array[i][0].equalsIgnoreCase(Acc_name)) && ((data_array[i][4].equalsIgnoreCase(Acc_cnic))))
                {                    
                    showdetailes(i);
                    check = false;
                    break;
                }
            }
        }
    }
    
    public static void NewAccount()
    {
        System.out.println("===================================================="
                        +"\n              Enter New User's Detailes"
                        +"\n====================================================");
        System.out.print("\nEnter UserName:\t");
        username = input.nextLine();
        System.out.print("\nEnter Father's Name:\t");
        fathername = input.nextLine();
        System.out.print("\nEnter User Age:\t");
        age = input.nextLine();
        System.out.print("\nEnter CNIC:\t");
        cnic = input.nextLine();
        System.out.print("\nEnter Address:\t");
        address = input.nextLine();
        while(true)
        {
            try
            {
                System.out.print("\nEnter Balance:\t");
                double amount1 = input.nextInt();
                amount = Double.toString(amount1);
                break;
            }
            catch(Exception e)
            {
                System.out.println("\nAn integer is required.");
            }
        }
        input.nextLine();
        System.out.print("\nEnter Password:\t");
        password = input.nextLine();
        
        while(true)
        {
            System.out.print("\nPlease re-enter your password:\t");
            String tempConfirmationPassword = input.nextLine();
            
            if(password.equals(tempConfirmationPassword))
            {
                if(verification())
                {
                    System.out.println("\nRecord with same Username and Password already exists.");
                    System.out.println("Please Enter New Details.");
                    NewAccount();
                    break;
                }
                else if(addSignUpInfo())
                {
                    System.out.println("\nSignup Successful.");
                    DataEntryFileTo2DArray();
                    System.out.println();
                    break;
                }
            }
        }
    }

    public static boolean verification()
    {
        DataEntryFileTo2DArray();
        try
        {
            for(int i = 0; i < 100; i++)
            {
                if(data_array[i][0] == null)
                {
                    break;
                }
                else if((data_array[i][0].equalsIgnoreCase(username)) && (data_array[i][3].equalsIgnoreCase(password)))
                {
                    user_idx = i;
                    return true;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
    
    public static boolean addSignUpInfo()
    {

        try{
            if (!file.exists()) 
            {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            {
                bw.write(username + "\t\t" + fathername + "\t\t" + age + "\t\t" 
                        + password + "\t\t" + cnic + "\t\t" + amount + "\t\t" + address );
                bw.newLine();
                bw.close();
                
                return true;
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        
        return false;
    }
       
    public static void DeleteAccount()
    {
        input.nextLine();
        boolean check = true;
        while(check)
        {
            System.out.println("===================================================="
                            +"\n           Enter Account Detailes To Delete"
                            +"\n====================================================");
            System.out.println("\nName.");
            String Acc_name = input.nextLine();
            System.out.println("\nCNIC.");
            String Acc_cnic = input.nextLine();

            for(int i = 0 ; i < 100 ; i++)
            {
                if(data_array[i][0] == null)
                {
                    System.out.println("\nData not found,\n"
                            + "Please re-Enter Account detailes.");
                    break;
                }
                else if((data_array[i][0].equalsIgnoreCase(Acc_name)) && ((data_array[i][4].equalsIgnoreCase(Acc_cnic))))
                {                    
                    showdetailes(i);
                    
                    System.out.println("\nUser Data found."
                                      +"\nPress Y for confirmation of Account Deletion."
                                      +"\nPress N to Cancel.");
                    String temp = input.nextLine();
                    if(temp.equalsIgnoreCase("y"))
                    {
                        deletenswaping(i);
                        System.out.println("\nAccount deleted Successfully.");
                    }
                    
                    check = false;
                    break;
                }
            }
        }
    }
    
    public static void deletenswaping(int i)
    {
        for(int j = 0; j<8 ; j++)
        {
            data_array[i][j] = null;
        }
        
        for(int k = 0; k<100; k++)
        { 
            
            if((data_array[k][0] == null) && (data_array[k+1][0] == null))
            {
                break;
            }
            if(data_array[k][0] == null)
            {
                if(data_array[k+1][0] != null)
                {
                    for(int l = 0; l<8; l++)
                    {
                        data_array[k][l] = data_array[k+1][l];
                        data_array[k+1][l] = null;
                    }
                }
            }
        }
        arraytofilewriting();
    }
    
    public static void DataEntryFileTo2DArray()
    {
        try
        {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int i = 0;
            while (((line = br.readLine()) != null) && (i<100))
            {

                String[] splitted = line.split("\t\t");
                for(int j = 0 ; j < 7 ; j++)
                {
                    data_array[i][j] = splitted[j];
                } 
                i++;
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static void Banking()
    {
        while(true)
        {   
            System.out.println("===================================================="
                            + "\n           Welcome to the Banking Option"
                            +"\n===================================================="
                            + "\nWhat you want to do...\n"
                            + "Press,"
                            + "\n1 for Deposite."
                            + "\n2 for withdraw By check."
                            + "\n3 for Exit.");
            int op = 0;
            try
            {
                op = input.nextInt();   
            }
            catch(Exception e)
            {
                System.out.println("4" + e.getMessage());
            }
            
            if(op==1)
            {
                input.nextLine();
                deposite();
            }
            else if(op==2)
            {
                input.nextLine();
                withdraw();
            }
            else if(op==3)
            {
                username = null;
                fathername = null;
                age = null;
                password = null;
                cnic = null;
                address = null;
                amount = null;
                user_idx = 0;
                break;
            }
            else
            {
                System.out.println("Invalid input.");
            }
        }
    }
    
    public static void deposite()
    {
        boolean check = true;
        try
        {
            while(check)
            {
                DataEntryFileTo2DArray();
                
                System.out.println("\nEnter User Name.");
                username = input.nextLine();
                System.out.println("\nEnter the CNIC.");
                cnic = input.nextLine();

                for(int i = 0 ; i < 100 ; i++)
                {
                    if(data_array[i][0] == null)
                    {
                        break;
                    }
                    else if((data_array[i][0].equalsIgnoreCase(username)) && ((data_array[i][4].equalsIgnoreCase(cnic))))
                    {
                        while(true)
                        {
                            user_idx = i;
                            try
                            {
                                System.out.println("\nEnter the Amount you want to deposite.");
                                double amount1 = input.nextInt();
                                amount = Double.toString(amount1);
                                break;
                            }
                            catch(Exception e)
                            {
                                System.out.println("\nAn integer is required.");
                            }
                        }
                        showdetailes(i);
                        input.nextLine();
                        System.out.println("\nPress Y for the confirmation of your deposite."
                                         + "\nPress N to Cancel.");
                        String temp = input.nextLine();

                        check = false;

                        if(temp.equalsIgnoreCase("y"))
                        {
                            confirmed(i , '+');
                            
                            System.out.println("Deposite Successful.");
                            
                            System.out.println("\nDo you want to see Deposite slip.(Y/N)");
                            temp = input.nextLine();
                            if(temp.equalsIgnoreCase("y"))
                            {
                                DisplayBalance("Deposite Slip");
                            }
                        }
                        
                        break;
                    }
                }

                if(check)
                {
                    System.out.println("\nData not found.Kindly enter new detailes.\n"
                                        + "Do you want to continue(Y/N)");
                    String temp = input.nextLine();
                    if(temp.equalsIgnoreCase("y"))
                    {
                        check = true;
                    }
                    else
                    {
                        check = false;
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public static void withdraw()
    {
        boolean check = true;
        try{
            while(check)
            {
                DataEntryFileTo2DArray();
                
                System.out.println("\nEnter User Name.");
                username = input.nextLine();
                System.out.println("\nEnter the CNIC.");
                cnic = input.nextLine();

                for(int i = 0 ; i < 100 ; i++)
                {
                    if(data_array[i][0] == null)
                    {
                        break;
                    }
                    else if((data_array[i][0].equalsIgnoreCase(username)) && ((data_array[i][4].equalsIgnoreCase(cnic))))
                    {
                        while(true)
                        {
                            user_idx = i;
                            try
                            {
                                System.out.println("\nEnter the Amount you want to Withdraw.");
                                double amount1 = input.nextInt();
                                amount = Double.toString(amount1);
                                break;
                            }
                            catch(Exception e)
                            {
                                System.out.println("\nAn integer is required.");
                            }
                        }
                        showdetailes(i);
                        input.nextLine();
                        System.out.println("\nPress Y for the confirmation of your Withdrawal."
                                          +"\nPress N to Cancel.");
                        String temp = input.nextLine();
                        
                        check = false;

                        if(temp.equalsIgnoreCase("y"))
                        {
                            confirmed(i , '-');
                            
                            System.out.println("Withdrawal Successful.");
                            
                            System.out.println("\nDo you want to see Withdrawal slip.(Y/N)");
                            temp = input.nextLine();
                            if(temp.equalsIgnoreCase("y"))
                            {
                                DisplayBalance("Withdrawal Slip");
                            }
                        }
                        
                        
                        break;
                    }
                }

                if(check)
                {
                    System.out.println("\nData not found.Kindly enter new detailes.\n"
                                        + "Do you want to continue(Y/N)");
                    String temp = input.nextLine();
                    if(temp.equalsIgnoreCase("y"))
                    {
                        check = true;
                    }
                    else
                    {
                        check = false;
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public static void confirmed(int i , char a)
    {
        String temp = data_array[i][5];
        double value1 = Double.parseDouble(temp);
        double value2 = Double.parseDouble(amount);
        
        if(a=='+')
        {
            data_array[i][7] = ("+" + value2);
            value1 = value1 + value2;
        }
        else if(a=='-')
        {
            data_array[i][7] = ("-" + value2);
            value1 = value1 - value2;
        }
        
        String temp1 = Double.toString(value1);
        
        data_array[i][5] = temp1;
        
        arraytofilewriting();
    }
    
    public static void arraytofilewriting()
    {
        try{
            PrintWriter writer;
            writer = new PrintWriter(file);
            
            for(int i = 0; i<100 ; i++)
            {
                if(data_array[i][0] == null)
                {
                    break;
                }
                for(int j = 0 ; j<8 ; j++)
                {
                    writer.print(data_array[i][j] + "\t\t");
                }
                writer.println();
            }
            writer.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public static void showdetailes(int i)
    {
        try
        {
        System.out.println("===================================================="
                        +"\n              Account Holder Detailes"
                        +"\n===================================================="
                           +"\n\t*Name of User" + "\t\t" + data_array[i][0]
                           +"\n\t*Father's Name" + "\t\t" + data_array[i][1]
                           +"\n\t*CNIC of User" + "\t\t" + data_array[i][4]
                           +"\n\t*Address  " + "\t\t" + data_array[i][6]
                        +"\n====================================================");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
       
    public static void ATM()
    {
        DataEntryFileTo2DArray();
        boolean check = true;
        while(check)
        {   
            System.out.println("\n===================================================="
                             + "\n                     ATM login"
                             + "\n====================================================");
            
            System.out.println("\nEnter User Name.");
            username = input.nextLine();
            System.out.println("\nEnter Password.");
            password = input.nextLine();
            if(verification())
            {
                System.out.println("\nSuccessfully Loged in.");
                check = false;
                ATM_options();
                break;      
            }
            
            else if(check)
            {
                System.out.println("\nData not found.Kindly enter new detailes.\n"
                                    + "Do you want to continue(Y/N)");
                String temp = input.nextLine();
                if(!(temp.equalsIgnoreCase("y")))
                {
                    check = false;
                }
            }
        }
    }
    
    public static void ATM_options()
    {
        while(true)
        {   
            System.out.println("\n===================================================="
                             + "\n              Welcome to the ATM Options"
                             + "\n===================================================="
                            + "\nWhat you want to do...\n"
                            + "Press,"
                            + "\n1 for Diplay Balance."
                            + "\n2 for Transection."
                            + "\n3 for withdraw."
                            + "\n4 for logout.");
            int op = 0;
            try
            {
                op = input.nextInt();   
            }
            catch(Exception e)
            {
                System.out.println("4" + e);
            }
            
            if(op==1)
            {
                DisplayBalance("ATM Slip");
            }
            else if(op==2)
            {
                Transection();
            }
            else if(op==3)
            {
                ATMwithdraw();
            }
            else if(op==4)
            {
                username = null;
                fathername = null;
                age = null;
                password = null;
                cnic = null;
                address = null;
                amount = null;
                user_idx = 0;
                break;
            }
            else
            {
                System.out.println("Invalid input.");
            }
        }
    }
    
    public static void DisplayBalance(String head)
    {
        DataEntryFileTo2DArray();
        try
        {
            System.out.println("===================================================="
                            +"\n \t\t\t" + head
                            +"\n===================================================="
                            +"\n\t*Name of User" + "\t\t" + data_array[user_idx][0]
                            +"\n\t*Father's Name" + "\t\t" + data_array[user_idx][1]
                            +"\n\n\t*CNIC of User" + "\t\t" + data_array[user_idx][4]
                            +"\n\t*Address  " + "\t\t" + data_array[user_idx][6]
                            +"\n\n\t*Last Transection         " + data_array[user_idx][7]
                            +"\n\t*Balance  " + "\t\t" + data_array[user_idx][5]
                            +"\n====================================================");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public static void Transection()
    {
        input.nextLine();
        boolean check = true;
        while(check)
        {
            System.out.println("\nEnter the Name of Payee.");
            String payee = input.nextLine();
            System.out.println("\nEnter the CNIC of Payee.");
            String payee_cnic = input.nextLine();

            for(int i = 0 ; i < 100 ; i++)
            {
                if(data_array[i][0] == null)
                {
                    System.out.println("\nData not found,\n"
                            + "Please re-Enter payee detailes.");
                    break;
                }
                else if((data_array[i][0].equalsIgnoreCase(payee)) && ((data_array[i][4].equalsIgnoreCase(payee_cnic))))
                {
                    while(true)
                    {
                        try
                        {
                            System.out.println("\nEnter the Amount you want to send.");
                            double amount1 = input.nextInt();
                            amount = Double.toString(amount1);
                            break;
                        }
                        catch(Exception e)
                        {
                            System.out.println("\nAn integer is required.");
                        }
                    }
                    
                    showdetailes(i);
                    input.nextLine();
                    
                    System.out.println("\nPress Y for to confirmation of transection."
                                      +"\nPress N to Cancel.");
                    String temp = input.nextLine();
                    if(temp.equalsIgnoreCase("y"))
                    {
                        confirmed(i , '+');
                        confirmed(user_idx , '-');
                        
                        System.out.println("\nTransection Successful.");
                        System.out.println("\nDo you want to see ATM slip.(Y/N)");
                        temp = input.nextLine();
                        if(temp.equalsIgnoreCase("y"))
                        {
                            DisplayBalance("ATM Slip");
                        }
                    }

                    check = false;
                    break;
                }
            }
        }
    }
    
    public static void ATMwithdraw()
    {
        boolean check = true;
        input.nextLine();
        while(check)
        {
            while(true)
            {
                try
                {
                    System.out.println("\nEnter the Amount you want to Withdraw, multiple of 500.");
                    double amount1 = input.nextInt();
                    amount = Double.toString(amount1);
                    break;
                }
                catch(Exception e)
                {
                    System.out.println("\nAn integer is required.");
                    input.nextLine();
                }
            }

            double value = Double.parseDouble(amount);
            double balance = Double.parseDouble(data_array[user_idx][5]);
            if(value%500 == 0.0)
            {
                if(value <= 25000.0)
                {
                    if(balance >= value)
                    {
                        input.nextLine();
                        System.out.println("\nValid Withdrawal Amount given,"
                                        + "\nPress Y for the confirmation of your Withdrawal."
                                        + "\nPress N to Cancel.");
                        String temp = input.nextLine();

                        if(temp.equalsIgnoreCase("y"))
                        {
                            confirmed(user_idx , '-');
                            
                            System.out.println("Withdrawal Successful.");
                            System.out.println("\nDo you want to see ATM slip.(Y/N)");
                            temp = input.nextLine();
                            if(temp.equalsIgnoreCase("y"))
                            {
                                DisplayBalance("ATM Slip");
                            }
                        }

                        check = false;
                    }
                    else
                    {
                        System.out.println("\nThe Withdrawal Amount given is more than Balance.\n"
                                + "Your Balance is " + data_array[user_idx][5]
                                + "\nPlease give withdrawal amount less then your Balance.");
                    }
                }
                else
                {
                    System.out.println("\nWithdrawal Amount exceeds the ATM limit.\n"
                            + "ATM Limit is 25000.");
                }
            }
            else
            {
                System.out.println("\nInvalid Withdrawal Amount given.\n"
                                + "Please give withdrawal amount as multiple of 500.");
            }
        }
    }
}