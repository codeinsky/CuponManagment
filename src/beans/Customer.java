package beans;

import java.util.ArrayList;
import java.util.Collection;

public class Customer {
private long id;
private String custName;
private String password;
private Collection<Coupon> coupon = new ArrayList<Coupon>();
public Customer(long id, String custName, String password) {
	super();
	this.id = id;
	this.custName = custName;
	this.password = password;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getCustName() {
	return custName;
}
public void setCustName(String custName) {
	this.custName = custName;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + "]";
}

}
