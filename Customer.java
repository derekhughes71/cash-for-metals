 /*
 * Derek Hughes
 * CIS 314 - DL - Sec 55
 * Winter 2013
 * Project #2
 */

import java.util.ArrayList;
import java.util.Random;
import java.util.Calendar;
public class Customer {

// holds common variables and methods for customer data
	Random randNumber = new Random();
	
	protected int cusType; // sets simple way to identify customer type as personal or commercial
	protected long custId = createCustId(); // sets customer ID on initialization
	protected String custName, contactName, custAddress, homePhone, workPhone, contactPhone;

	Calendar date = getCalendar();
		
	Account act; // Account object to implement composition
	
// default constructor
	public Customer(){
	}
	
// regular customer constructor
	public Customer(String cName, long cId, String cAddress, Account a1){
			custId = cId;
			custName = cName;
			custAddress = cAddress;
			act = a1;
	}
		
// set methods
	public void setName(String name){
		custName = name;
	}
	
// get methods
	public String getName(){
		return custName;
	}
	
	public long getCustId(){
		return custId;
	}
	
	public int getCustType(){
		return cusType;
	}
		
	public Calendar getCalendar(){
		return Calendar.getInstance();
	}
	
	public long createCustId(){
		return UniqueIDFactory.generateUniqueID();
	}


// returns Transaction toString after appending transactions if customer has multiple transactions
	private static String transInfo(long id2){
	  
	  ArrayList<Transaction> custTransactions = CashforMetalsClient.getTransactionArray();
		  
		  String transactionToAppend = ""; // additional transaction to append to transaction toString()
		  String transactionToDisplay= ""; // complete concatenated transaction toString();
		
	// looping to find all transactions associated with customer
	// if additional transactions are located they are appended to previous string
	// to create new string that is used within Customer toString() 
	  for(Transaction trans: custTransactions){
		  if(trans.getCustId() == id2){
			 
			  Transaction p = new Transaction(trans.getCustId(), trans.getId(), trans.getCusType(), trans.getTransDate(), trans.getGold(), trans.getPlatinum(), trans.getSilver());
			  transactionToAppend += p.toString()+"\n";
			   
		  } // end if - play.getId()
		  transactionToDisplay= transactionToAppend;
	  } // end for - play

	  	return transactionToDisplay;
   } // end method - getTransInfo();
	
	
// toString to override Object.toString()
	@Override	
	public String toString(){
		String cust = String.format("Customer Name: %s\n" +
				"Customer ID: %d\n" +
				"Customer Address: %s\n", 
				getName(), custId, custAddress); // toString for Customer.java
				
		String trans = transInfo(act.getAccount()); // calls toString for Transaction.java
				
		return String.format("%s%s%s", cust, act, trans); // combines toString's from Customer.java, Account.java, and Transactions.java
	
	} // end method - toString()
}
