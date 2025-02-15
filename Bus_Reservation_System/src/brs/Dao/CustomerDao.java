package brs.Dao;

import java.sql.SQLException;
import java.util.List;

import brs.Exception.CustomerException;
import brs.model.Customer;

public interface CustomerDao {
	
	void registerCustomer(Customer customer) throws CustomerException;
	
	 boolean validateCustomer(int customerId) throws CustomerException;
    
    Customer getCustomerById(int customerId) throws CustomerException;
    
    List<Customer> getAllCustomers() throws CustomerException;
    
    void updateCustomer(Customer customer) throws CustomerException;
    
    void deleteCustomer(int customerId) throws CustomerException;
    
}
