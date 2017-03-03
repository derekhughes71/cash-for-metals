/*
 * CashforMetalsClient.java
 * 
 * Runs the CashforMetalsClient program. Interacts with a 
 * user to create customers, accounts and transactions
 * based on whether customer is personal or commercial customer
 *
 * @author Derek Hughes
 * @version 1.0
 * Derek Hughes
 * CIS 314 - DL - Sec 55
 * Winter 2013
 * Project #2
*/

import java.util.*;
import java.util.ArrayList;

public class CashforMetalsClient {
	/*
	* General notes #1: Per Dr. Akkawi's direction, the interest rate of 3% is applied to current customer balance 
	* whenever option 5 (Display Customer) is selected. Thus, expect account balance to reflect a 3% increase 
	* whenever option 5 is selected. Furthermore, these differences in sales amounts based on the type of customer
	* are also reflected in the transaction values. This means that commercial transaction values will display 3% higher
	* than a personal customer value. However, if option 7 (Display Grand Summary) is selected, only the current account balance
	* will be shown - the interest rate is NOT applied here as this option is considered to be a "final" view of the 
	* customer's information.
	*/ 
	
    // Data structure to store customers
    private static ArrayList<Customer> customers = new ArrayList<Customer>();
    
    /* 
    * Assumption #1: Each transaction must have its own unique ID, date of transaction, amount of metals sold, 
    * and total value. Thus, all transactions are stored in ArrayList custTransactions because unknown number 
    * of transactions can be added. The Transaction ArrayList is associated to customers ArrayList by the 
    * customer's unique ID number. Furthermore, transaction rates vary based on the type of customer making the transaction.
    */
	private static ArrayList<Transaction> custTransactions = new ArrayList<Transaction>();
     
	/*
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
              
        Customer customer;
        Transaction transaction;
        
        double withdrawalAmount = 0;
        
        boolean finished = false;
        
        while (finished == false)
        {
            // Menu Display and Get user input
            int inputInt = 0;
            while (inputInt == 0)
            {
                inputInt = displayMenuAndGetInput();

                        // if the input is out of range
                        if ((inputInt < 1) || (inputInt > 8))
                        {
                                System.out.println("\nThe input is out of range!");
                                System.out.println();
                                inputInt = 0;
                        }
                } //end while

                // switch to correspondence function
                switch (inputInt)
                {
                    case 1:
                        customer = createPersonalCustomer();
                        System.out.println("\nThe Personal customer has been created: \n" + customer.toString());
                        customers.add(customer);
                        break;
                    case 2:
                        customer = createCommercialCustomer();
                        System.out.println("\nThe Commercial customer has been created: \n" + customer.toString());
                        customers.add(customer);
                        break;
                    case 3:
                        transaction = recordTransaction();
                        if(transaction != null)
                            {System.out.println("\nThe Transaction has been created: " + transaction.toString() + "\n");
                        	custTransactions.add(transaction);}
                        else
                            System.out.println("\nThe ID could not be found. \n");
                        break;
                    case 4:
                        withdrawalAmount = makeWithdrawal();
                        if(withdrawalAmount > 0)
                            System.out.println("\nAmount withdrawn from this account: " + StringFormatter.formatMoney(withdrawalAmount) + "\n");
                        else
                            System.out.println("\nThe ID could not be found. \n");
                        break;
                    case 5:
                    	// NOTE: this method increases the customer's account balance by the designated interest rate
                    	// each time it is called...so, the customer's account will increase by 3% each time this method is invoked
                        displayCustomer();
                        break;
                    case 6:
                        displayCustomerSummary();
                        break;
                    case 7:
                        displayGrandSummary();
                        break;
                    case 8:
                        // exit
                        finished = true;
                        break;
                    default:
                        System.out.println("Invalid Input!");
                        System.out.println("");
                        break;
                } // end switch
        } // end while

    } // end method - main
//--------------------------------------------------------------------------------------------

    public static int displayMenuAndGetInput() {
    
        int inputInt =-1;
        Scanner input = new Scanner( System.in );
                
        // Menu Display
        System.out.println("***\t\t\t\t\t\t\t***");
        System.out.println("***\tWelcome to Cash for Metals Calculator!!!\t***");
        System.out.println("***\t\t\t\t\t\t\t***");
        System.out.println(" 1.  Create Personal Customer");
        System.out.println(" 2.  Create Commercial Customer");
        System.out.println(" 3.  Record Transaction");
        System.out.println(" 4.  Make Withdrawal");
        System.out.println(" 5.  Display Customer");
        System.out.println(" 6.  Display Customer Summary");
        System.out.println(" 7.  Display Grand Summary");
        System.out.println(" 8.  Exit");
        System.out.println("");

        do{
            // Get the input from the user
            System.out.print("Please input your choice (1-8): ");

            // Check for valid user input
            while(!input.hasNextInt()){
            	System.out.print("Please enter valid input (1-8): ");
            	input.nextLine();
            }
            inputInt = input.nextInt();
            } while(inputInt < 1 && inputInt > 8); 
            
    return inputInt;
    
    } // end method - displayMenuAndGetInput()
    
// Create a new PerosonalCustomer
    public static Customer createPersonalCustomer(){
    	Scanner input= new Scanner(System.in);
    	PersonalCustomer personal;
    	String name, address, homePhone, workPhone;
    	
    	// receive user input section (name, address, home/work phone)
    	
    		// name input
    		do{
    		System.out.print("\nEnter the customer name > ");
    		name = input.nextLine();
    		
    		// string data validation check if numeric value entered
    		if(!name.matches("[a-zA-Z\\s]+"))
    			System.out.print("Name contains invalid character - please try again. \n");
    		
    		} while(name.matches("[a-zA-Z\\s]+")== false);	
    	
    		// PersonalCustomer.java initialization sends name to constructor
    		personal = new PersonalCustomer(name); 
    	
    		// address input
    		do{
        		System.out.print("\nEnter the customer address > ");
        		address = input.nextLine();
        		
        		// string data validation check if numeric value entered
        		if(!address.matches("[a-zA-Z\\s0-9\\d]+"))
        			System.out.print("Name contains invalid character - please try again. \n");
        		
        		} while(address.matches("[a-zA-Z\\s0-9\\d]+")== false);
    		
    		// set address in PersonalCustomer.java
    		personal.setAddress(address);
    		
    		
    		// home phone input
    		String exp = "^\\(?(\\d{3})\\)?[-]?(\\d{3})[-]?(\\d{4})$";
    		do{
        		System.out.print("\nEnter the customer home phone > ");
        		homePhone = input.nextLine();
        		
        		// string data validation check if numeric value entered
        		if(!homePhone.matches(exp))
        			System.out.print("Phone number must be in the form XXX-XXX-XXXX\n");
        		
        		} while(homePhone.matches(exp)== false);
    		
    		// set home phone in PersonalCustomer.java
    		personal.setHomePhone(homePhone);
    		
    		// work phone input
    		do{
        		System.out.print("\nEnter the customer work phone > ");
        		workPhone = input.nextLine();
        		
        		// string data validation check if numeric value entered
        		if(!workPhone.matches("^\\(?(\\d{3})\\)?[-]?(\\d{3})[-]?(\\d{4})$"))
        			System.out.print("Phone number must be in the form XXX-XXX-XXXX\n");
        		
        		} while(workPhone.matches("^\\(?(\\d{3})\\)?[-]?(\\d{3})[-]?(\\d{4})$")== false);
    		
    		// set work phone in PersonalCustomer.java
    		personal.setWorkPhone(workPhone);
    		
    	  // Account object to be stored within PersonalCustomer p listed below
    	  Account act = new Account(personal.getCustId(), 0.00, Calendar.getInstance());
    	
    	  // PersonalCustomer object to be stored in customers ArrayList
    	PersonalCustomer p = new PersonalCustomer(personal.getCustId(), personal.getName(), personal.getAddress(), personal.getHomePhone(), personal.getWorkPhone(), act);
    	
   	  return p;
   	  
    } // end method - PersonalCustomer.java
    
// Create a new CommercialCustomer
    public static Customer createCommercialCustomer(){
    	Scanner input= new Scanner(System.in);
    	CommercialCustomer commercial;
    	String name, address, contact, contactPhone;
    	
    	// receive user input section (name, address, home/work phone)
    	
    		// name input
    		do{
    		System.out.print("\nEnter the customer name > ");
    		name = input.nextLine();
    		
    		// string data validation check if numeric value entered
    		if(!name.matches("[a-zA-Z\\s]+"))
    			System.out.print("Name contains invalid character - please try again. \n");
    		
    		} while(name.matches("[a-zA-Z\\s]+")== false);	
    	
    		// PersonalCustomer.java initialization sends name to constructor
    		commercial = new CommercialCustomer(name); 
    	
    		// address input
    		do{
        		System.out.print("\nEnter the customer address > ");
        		address = input.nextLine();
        		
        		// string data validation check if numeric value entered
        		if(!address.matches("[a-zA-Z\\s0-9\\d]+"))
        			System.out.print("Name contains invalid character - please try again. \n");
        		
        		} while(address.matches("[a-zA-Z\\s0-9\\d]+")== false);
    		// set address in PersonalCustomer.java
    		commercial.setAddress(address);
    		
    		// contact input
    		do{
    		System.out.print("\nEnter the customer contact person > ");
    		contact = input.nextLine();
    		
    		// string data validation check if numeric value entered
    		if(!contact.matches("[a-zA-Z\\s]+"))
    			System.out.print("Name contains invalid character - please try again. \n");
    		
    		} while(contact.matches("[a-zA-Z\\s]+")== false);	
    	
    		// PersonalCustomer.java initialization sends name to constructor
    		commercial.setContactName(contact);
    		    		
    		// contact's phone input
    		do{
        		System.out.print("\nEnter the customer contact person phone > ");
        		contactPhone = input.nextLine();
        		
        		// string data validation check if numeric value entered
        		if(!contactPhone.matches("^\\(?(\\d{3})\\)?[-]?(\\d{3})[-]?(\\d{4})$"))
        			System.out.print("Phone number must be in the form XXX-XXX-XXXX\n");
        		
        		} while(contactPhone.matches("^\\(?(\\d{3})\\)?[-]?(\\d{3})[-]?(\\d{4})$")== false);
    		// set work phone in PersonalCustomer.java
    		commercial.setContactPhone(contactPhone);
    		
    		// Account object to be stored within CommercialCustomer c listed below
    		Account act = new Account(commercial.getCustId(), 0.00, Calendar.getInstance());
    	// CommercialCustomer object to be stored in customers ArrayList  
    	CommercialCustomer c = new CommercialCustomer(commercial.getName(), commercial.getCustId(), commercial.getContactName(), commercial.getAddress(), commercial.getContactPhone(), act);
    	
    	return c;
    } // end method - CommercialCustomer.java
    
    
// Create a new Transaction
    public static Transaction recordTransaction(){
    	
    	Scanner input= new Scanner(System.in);
    	Transaction trans = new Transaction();
    	
    	Customer c;
    	long id;
    	    	
    	// ask for customer ID
    	System.out.print("Enter the customer ID to create the transaction > ");
    	while (!input.hasNextLong()) { 
		    System.out.print("Invalid input type: please try again >");
		    input.next();
		   } // end while - hasNextLong();
    	id= input.nextLong();
    	    	
    	
    	// compare custId input to custId in Customer ArrayList
    	c = findCustomer(id); // finds customer from Customer ArrayList and sets to c
    	if(c != null)
    	{		
    		
    			// gold input
    			do{ 
    				System.out.print("Enter the weight of gold > ");
    						
    				// validation- check input value type
    					   while (!input.hasNextDouble()) { 
    					    System.out.println("Invalid input type: please try again >");
    					    input.next();
    					   } // end while - hasNextDouble();
    			
    					   double goldWeight = input.nextDouble(); // after validating the data type, set the local variable input
    					   trans.setGold(goldWeight);
    					   
    				} while (trans.getGold() < 0.0); // check that value input is greater than zero
    			trans.setGold(trans.getGold()); // gold weight set in Transaction class after gold validation checks complete
    			
    			
    			// platinum input
    	 			do{ 
    	  				System.out.print("Enter the weight of platinum > ");
    	  						
    	   				// validation- check input value type
    	   					   while (!input.hasNextDouble()) { 
    	   					    System.out.println("Invalid input type: please try again >");
    	   					    input.next();
    	   					   } // end while - hasNextDouble();
    	   			
    	   					double platinumWeight = input.nextDouble(); // after validating the data type, set the local variable input
    	   					trans.setPlatinum(platinumWeight);
    	   					
    	   				} while (trans.getPlatinum() < 0.0); // check that value input is greater than zero
    	 			trans.setPlatinum(trans.getPlatinum()); // platinum weight set in Transaction class after platinum validation checks complete
    	    							
    	    					
    	    	// silver input
    			do{ 
    			System.out.print("Enter the weight of silver > ");
    		
    			// validation- check input value type
    				   while (!input.hasNextDouble()) { // check input value type
    				    System.out.println("Invalid input type: please try again >");
    				    input.next();
    				   } // end while - hasNextDouble();
    		
    				   double silverWeight = input.nextDouble(); // after validating the data type, set the local variable input
    				   trans.setSilver(silverWeight);
    				   
    				} while (trans.getSilver() < 0.0); // check that value input is greater than zero
    			trans.setSilver(trans.getSilver()); // silver weight set in Transaction class after validation checks complete
    		
    		    // update customer account via makeDeposit() method in Account
    			// balance is updated based on customerType (Personal or Commercial)
    			// if cusType = 2, then calculateTotals() method retrieves current commercial rate from CommercialCustomer
    			// and uses it to calculate transaction totals appropriately
    			double balance = c.act.makeDeposit(c.act.getBalance(), trans.calculateTotals(c.getCustType()));
     			c.act.setBalance(balance);  // set new balance 
    			
    		// inserts transaction into transaction array
    		// transaction linked to customer via customerID
    		// transactionID initialized in Transaction constructor 
        	Transaction t = new Transaction(c.custId, trans.getId(), c.getCustType(), Calendar.getInstance(), trans.getGold(), trans.getPlatinum(), trans.getSilver()); 	    	 	
        	
        	return t; // returns Transaction type to case: 3 to invoke message and addition of t into Transaction ArrayList
    		
    		} // end if - custID is found
    	    	  	
    	return null; // if nothing returned then return null to case: 3 to invoke "id not found"
    	
    } // end method - recordTransaction()
    
    
            
// Make a withdrawal
    public static double makeWithdrawal(){
    	Scanner input= new Scanner(System.in);
    	double withDrawal = 0; // user input withdraw amount and amount returned to case: 5
    	
    	Customer c; // customer object to be used
    	
    	// ask for customer ID
    	System.out.print("Enter the customer ID to make a withdrawal > ");
    	while (!input.hasNextLong()) { 
		    System.out.println("Invalid input type: please try again >");
		    input.next();
		   } // end while - hasNextLong();
    		
    		long id= input.nextLong();
   		    		
    		// compare custId input to custId in Customer ArrayList
        	c = findCustomer(id); // finds customer from Customer ArrayList and sets to c
        	if(c != null)
        	{
    	  	 
    		// continue to ask for withdraw amount until amount entered is less than customer balance	
    		    		
    		/* Makewithdrawal() - intentionally modified from project directions to create a more user
    		* friendly application. This approach repeats  It also allows user to attempt another amount that will be accepted. 
    		*/ 
        	int withdrawLoop = 0; // do...while loop sentinel
    		do{
    			
    			// receive withdrawal amount and validate user input and greater than zero
	    		do{
	    		System.out.print("Enter the amount to withdraw > ");
		    		
	    		while (!input.hasNextInt()) { 
		    		    System.out.println("Invalid input type: please try again >");
		    		    input.next();
		    		   }
		    		withDrawal = input.nextDouble();
		    			
	    		} while(withDrawal <= 0);
    		
	    		// makeWithDrawal() method from Account.java - uses current balance and user input withdrawal amount
	    		withDrawal = c.act.makeWithdrawal(c.act.getBalance(), withDrawal);
	    		
	    		// informs user of overdrawn account if user withdraw amount more than current customer balance
	    		if(withDrawal == 0)
	    			System.out.println("Amount will overdraw account balance - please try again \n");
	    		
	    		if(withDrawal != 0)
	    			{
						double balance = c.act.getBalance(); // gets current balance 
	    				balance -= withDrawal; // removes withdrawal amount from balance
	    				c.act.setBalance(balance); // set new balance
	    				withdrawLoop = 1; // change while loop sentinel to break out of loop with successful balance update
	    			}
	    	
    		} while(withdrawLoop == 0);  // 
	    	
	    	return withDrawal; 
    	
    	  } // end if - c.getCustId()
    	
    	return withDrawal;
    	
    } // end method - makeWithdrawal()
    
    
    
// Display customer summary - 
// NOTE: this method increases the customer's account balance by the designated interest rate
// each time it is called...so, the customer's account will increase by 3% each time this method is invoked
    public static void displayCustomer(){
    	Scanner input= new Scanner(System.in);
    	Customer c;
     	// ask for customer ID
    	
    	do{
    	System.out.print("Enter the customer ID to view a summary > ");
    	while (!input.hasNextLong()) { 
		    System.out.print("Invalid input type: please try again >");
		    input.next();
		   } // end while - hasNextLong();
    	
    	long id= input.nextLong();
    	
    	// compare custId input to custId in Customer ArrayList
    	c = findCustomer(id); // finds customer from Customer ArrayList and sets to c
    	if(c == null)
    		System.out.println("Invalid customer ID - locate valid customer ID from above and re-enter\n");
    	
    	} while(c == null); // method does not return anything so continues loop until valid ID is entered
    	
    	// update account by current interest rate each time customer is displayed
    	double balance = c.act.getBalance() * (Account.getInterest() + 1);
    	c.act.setBalance(balance);
    	
    	System.out.println(c);
 
    } // end method - displaySummary()
    
    
    
// Display total customer summary
    public static void displayCustomerSummary(){
    	double total = 0;
    	
    	System.out.printf("\nTotal number of customers : %s\n", customers.size());
    	
    	for(Customer c: customers){
    		total += c.act.getBalance();
    	}
    		
    	System.out.printf("Total value of all accounts : $%.2f\n\n", total);
    	    	
    } // end method - displayCustomerSummary();
 
    
// Display all customers, accounts and transactions
    public static void displayGrandSummary(){
    	for(Customer c: customers){
    		if(c.getCustType() == 1){
				PersonalCustomer per = new PersonalCustomer(c.custId, c.custName, c.custAddress, c.homePhone, c.workPhone, c.act);
				System.out.println(per);
			}
			
			if(c.getCustType() == 2){
				CommercialCustomer com = new CommercialCustomer(c.custName, c.custId, c.contactName, c.custAddress, c.contactPhone, c.act);
	    		System.out.println(com);
			}
	
    		
    		} // for - Customer c
    } // end method - displayGrandSummary()
    
    
// Helper method to determine if a customer exists
    public static Customer findCustomer(long customerID){
    	long id = customerID; // custID received from user
    	for(Customer c: customers){ // user custID compared to each element in customers ArrayList
    		if(c.getCustId() == id){ // to locate Customer object with same custID 
    			
    			// set commercial or personal customer depending on custType
    			// this could be accomplished by checking if customer has 
    			
    			if(c.getCustType() == 1){
    				PersonalCustomer per = new PersonalCustomer(c.custId, c.custName, c.custAddress, c.homePhone, c.workPhone, c.act);
    				return per;
    			}
    			
    			if(c.getCustType() == 2){
    				CommercialCustomer com = new CommercialCustomer(c.custName, c.custId, c.contactName, c.custAddress, c.contactPhone, c.act);
    				return com;	
    			}
    			    			
    		} // end if - c.getCustId()
    	} // end for - Customer c
    	return null;
    } // end method - findCustomer()

    
// public get method that allows private Transaction ArrayList to be retrieved and used within other classes
 	public static ArrayList<Transaction> getTransactionArray(){
 		return custTransactions;
 	}

}  // end class - CashforMetalsClient.java