package com.jusoft.ehcache.tutorial.dao;

import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER;
import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.isNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.jusoft.ehcache.tutorial.model.Customer;

/**
 * Created by carnicj on 13/07/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerDaoImplUTest {

   private static final String NEW_NAME = "new name";
   private static final String NEW_SURNAME = "new surname";
   
   private CustomerDao customerDao = new CustomerDaoImpl();

   @Test
   public void testSave() {
      final Long id = customerDao.save(DEFAULT_CUSTOMER);
      
      Customer customer = customerDao.find(id);
      
      assertThat(customer, is(DEFAULT_CUSTOMER));
   }
   
   @Test
   public void testFind() {
	   Long id = customerDao.save(DEFAULT_CUSTOMER);
	   Customer customer = customerDao.find(id);
	   
	   assertThat(customer, is(DEFAULT_CUSTOMER));
   }
   
   @Test
   public void testUpdate() {
		Customer newCustomer = new Customer(DEFAULT_CUSTOMER_ID, NEW_NAME, NEW_SURNAME);
		boolean update = customerDao.update(newCustomer);
		
		assertTrue(update);
		Customer storedCustomer = customerDao.find(DEFAULT_CUSTOMER_ID);
		assertThat(storedCustomer, is(newCustomer));
   }
   
   @Test
   public void testDelete() {
	   Long id = customerDao.save(DEFAULT_CUSTOMER);
	   boolean customerRemoved = customerDao.remove(id);
	   
	   assertTrue(customerRemoved);
	   Customer customer = customerDao.find(id);
	   assertThat(customer, is(nullValue()));
   }
}
