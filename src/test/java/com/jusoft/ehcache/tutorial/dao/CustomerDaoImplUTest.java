package com.jusoft.ehcache.tutorial.dao;

import com.jusoft.ehcache.tutorial.model.Customer;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.runners.MockitoJUnitRunner;

import static com.googlecode.catchexception.CatchException.*;
import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER;
import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER_ID;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDaoImplUTest {

   private static final String NEW_NAME = "new name";
   private static final String NEW_SURNAME = "new surname";
   
   private CustomerDao customerDao = new CustomerDaoImpl();

   @Test
   public void testSaveStoresCustomer() {
      customerDao.save(DEFAULT_CUSTOMER);

      Customer customer = customerDao.find(DEFAULT_CUSTOMER.getId());
      
      assertThat(customer, is(DEFAULT_CUSTOMER));
   }

   @Test
   public void testSaveThrowsExceptionWhenCustomerIsNull() {
      catchException(customerDao).save(null);

      assertThat(caughtException(), instanceOf(IllegalArgumentException.class));
   }
   
   @Test
   public void testFind() {
      customerDao.save(DEFAULT_CUSTOMER);
      Customer customer = customerDao.find(DEFAULT_CUSTOMER.getId());

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
   public void testDeleteWhenElementExists() {
      customerDao.save(DEFAULT_CUSTOMER);
      boolean customerRemoved = customerDao.remove(DEFAULT_CUSTOMER.getId());

      assertTrue(customerRemoved);
      Customer customer = customerDao.find(DEFAULT_CUSTOMER.getId());
      assertThat(customer, is(nullValue()));
   }

   @Test
   public void testDeleteWhenElementDoesntExist() {
      boolean customerRemoved = customerDao.remove(DEFAULT_CUSTOMER_ID);

      assertFalse(customerRemoved);
      Customer customer = customerDao.find(DEFAULT_CUSTOMER_ID);
      assertThat(customer, is(nullValue()));
   }
}
