 /*
 * Derek Hughes
 * CIS 314 - DL - Sec 55
 * Winter 2013
 * Project #2
 */
// uses variables and methods from customer.java and personalizes the info 
public class PersonalCustomer extends Customer{

// default constructor - not used but made available for possible future applications
	public PersonalCustomer(){		
	}
	
// constructor takes one String - name and creates customer ID (long)
	public PersonalCustomer(String n){
		setName(n); // this is received from Customer.java and sets the customer's name
		createCustId(); // this is received from Customer.java which creates the custId()
	}
	
// main constructor and inherits constructor from Customer.java and sets home phone, work phone, customer type
	public PersonalCustomer(long cId, String cName, String cAddress, String hPhone, String wPhone, Account act){
		super(cName, cId, cAddress, act); // super() constructor from Customer.java
		homePhone = hPhone;
		workPhone = wPhone;
		cusType = 1; // simple, quick way to identify if customer personal or commercial throughout program
	}

// set methods - address, home phone, work phone
		public void setAddress(String address1){
			custAddress= address1;
		}
		public void setHomePhone(String homePhone1){
			homePhone = homePhone1;
		}
		public void setWorkPhone(String workPhone1){
			workPhone = workPhone1;
		}
		
// get methods - name, address, home phone, work phone, custId
		public String getName(){
			return custName;
		}
		public String getAddress(){
			return custAddress;
		}
		public String getHomePhone(){
			return homePhone;
		}
		public String getWorkPhone(){
			return workPhone;
		}
		public long getCustId(){
			return custId;
		}
		

// toString to override super.toString() - includes call to Customer.toString() 
	@Override
	public String toString(){
		return String.format("%s\nHome Phone: %s\n" +
				"Work Phone: %s\n", super.toString(), homePhone, workPhone);
	}
	
} // end class - PersonalCustomer.java