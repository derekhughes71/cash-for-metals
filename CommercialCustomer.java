 /*
 * Derek Hughes
 * CIS 314 - DL - Sec 55
 * Winter 2013
 * Project #2
 */
public class CommercialCustomer extends Customer {

// default constructor - not used but made available for possible future applications
	public CommercialCustomer(){		
	}
	
// constructor takes one String - name and creates customer ID (long)
	public CommercialCustomer(String n){
		setName(n); // this is received from Customer.java and sets the customer's name
		createCustId(); // this is received from Customer.java which holds the custId()
	}
	
// main constructor and inherits constructor from Customer.java and sets contact name, phone, customer type	
	public CommercialCustomer(String cName, long cId, String contactName2, String cAddress, String contactPhone2, Account act2){
		super(cName, cId, cAddress, act2); // super() constructor from Customer.java
		contactName = contactName2;
		contactPhone = contactPhone2;
		cusType = 2; // simple, quick way to identify if customer personal or commercial throughout program
	}

// set methods - address, contact name, contact phone
		public void setAddress(String address1){
			custAddress= address1;
		}
		public void setContactName(String contactName1){
			contactName = contactName1;
		}
		public void setContactPhone(String contactPhone1){
			contactPhone = contactPhone1;
		}
		
// get methods - name, address, contact name, contact phone, custId
		public String getName(){
			return custName;
		}
		public String getAddress(){
			return custAddress;
		}
		public String getContactName(){
			return contactName;
		}
		public String getContactPhone(){
			return contactPhone;
		}
		public long getCustId(){
			return custId;
		}
	
	
// toString to override super.toString() - includes call to Customer.toString() 
@Override
		public String toString(){
			return String.format("%s\nContact Person: %s\n" +
					"Contact Person Phone: %s\n", super.toString(), contactName, contactPhone);
		}
	
} // end class - CommercialCustomer.java