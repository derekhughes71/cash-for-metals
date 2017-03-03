 /*
 * Derek Hughes
 * CIS 314 - DL - Sec 55
 * Winter 2013
 * Project #2
 */
import java.util.Calendar;
public class Account {

// instance variables
	private long actNumber;
	private double balance;
	protected static final double INTEREST = 0.03;  
	private Calendar date;

// default constructor
	public Account(){
	}
	
// regular Account constructor
	public Account(long actNumber2, double balance2, Calendar date2){
		actNumber = actNumber2;
		balance = balance2;
		date = date2;
	}
	
// set methods
	public double setBalance(double bal){
		if(bal < 0){ 
			System.out.print("balance below zero - unable to make change");
			return bal;}
			
			return balance = bal;
	}
	
// get methods
	public long getAccount(){
		return actNumber;
	}
	
	public double getBalance(){
		return balance;
	}
	
	public static double getInterest(){
		return INTEREST;
	}
	
	public Calendar getDate(){
		return date;
	}
	
// make deposit method
	protected double  makeDeposit(double bal, double amtDeposit){
		double balance = bal;
		return balance += amtDeposit;  // returns new balance with updated deposit		
	}
	
// make withdrawal method - 
	protected double makeWithdrawal(double bal, double amtWithDrawal){
		double balance = bal;
		double withDrawal = amtWithDrawal;
		
		// Ensures balance cannot be withdrawn below zero -    	
    	// withdraw amount from balance only if withdraw amount not greater than balance
    	if(balance < withDrawal){
	    		withDrawal= 0;  // reset amount to withdraw amount to zero if overdraws account balance
	    		return withDrawal;
    	}else
	    		return withDrawal;
	}
	
// toString to override super.toString()
	@Override	
	public String toString(){
		return String.format("\nAccount Number: %d\n"+
				"Balance: $%,01.2f\n" +
				"Interest Rate: %,01.2f\n" +
				"Date Opened: %tD\n", 
				getAccount(), getBalance(), getInterest(), getDate());
	} 
	
} // end class - Account.java
