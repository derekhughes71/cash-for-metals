 /*
 * Derek Hughes
 * CIS 314 - DL - Sec 55
 * Winter 2013
 * Project #2
 */
import java.util.Calendar;

public class Transaction{

// instance variables and CONSTANTS
	private long custId; // used to associate unique customer ID number to customer's transactions
	private long id; // transaction ID - unique to each transaction
	private int cusType; // determine if customer is personal or commercial (1 = personal, 2 = commercial) - associated with custID
	private Calendar transDate; // date transaction is created (MM/DD/YYYY)
	
	public static final double COMMERCIALRATE = 1.03; // current commercial rate for transactions - 3% more than personal customers
	private static final double GOLD_VALUE = 400.50;  // constant for gold value
	private static final double PLATINUM_VALUE = 500.50; // constant for platinum value
	private static final double SILVER_VALUE = 6.25; // constant for silver value
	private double goldWeight; // gold weight from user
	private double platinumWeight; // platinum weight from user
	private double silverWeight; // silver weight from user
	private double value;
	
// default constructor
	public Transaction(){
		// set transaction ID
		// set date/time when transaction created
		id = UniqueIDFactory.generateUniqueID();
		transDate = Calendar.getInstance();
	}
	
// standard Transaction constructor to set values based on individual transaction pulled from Transaction ArrayList
	public Transaction(long custId2, long id2, int cusType2, Calendar transDate2, double goldWeight2, double platinumWeight2, double silverWeight2){
		custId = custId2;
		id = id2;
		cusType = cusType2;
		transDate= transDate2;
		goldWeight= goldWeight2;
		platinumWeight= platinumWeight2;
		silverWeight= silverWeight2;
		value= calculateTotals(cusType); // uses calculateTotals() method to determine transaction value based on customer type
	}
			
// set methods
	public void setGold(double g){
		goldWeight = g;
		}
	public void setPlatinum(double p){
		platinumWeight = p;
		}
	public void setSilver(double s){
		silverWeight = s;
		}
	
// get methods
	public long getCustId(){
		return custId;
	}
	public long getId(){
		return id;
	}
	public int getCusType(){
		return cusType;
	}
	public Calendar getTransDate(){
		return transDate;
	}
	public double getGold(){
		return goldWeight;
		}
	public double getPlatinum(){
		return platinumWeight;
		}
	public double getSilver(){
		return silverWeight;
		}
	public double getValue(){
		return value;
		}
	
// calculate totals for current transaction object
	public double calculateTotals(int cusType2){
		cusType = cusType2; // determine customer type
		double goldTotal = getGold() * GOLD_VALUE; // local variable - total gold value
		double platinumTotal = getPlatinum() * PLATINUM_VALUE; // local variable - total platinum value
		double silverTotal = getSilver() * SILVER_VALUE; // local variable - total silver value
		value = (goldTotal + platinumTotal + silverTotal); // total - sum of all metals in transaction
			
			if(cusType == 1)
				return value; // returns standard value if personal customer
			else{
				return value * COMMERCIALRATE; // current commercial rate from commercial customer - (+3% more)
				}
	
	} // end method- calculateTotals

	
// toString to display weights and total value of transaction
	@Override
	public String toString(){
		return String.format("\nTransaction ID: %d\n" +
		"Gold Weight: %01.2f\n" +
		"Platinum Weight: %.2f\n" +
		"Silver Weight: %.2f\n" +
		"Total Value: $%01.2f",
		id, getGold(), getPlatinum(), getSilver(), getValue());
	}
	
} // end class - Transaction.java