package com.jusoft.ehcache.tutorial.repository;

import com.jusoft.ehcache.tutorial.dao.CustomerDao;
import com.jusoft.ehcache.tutorial.model.Customer;
import org.junit.*;
import org.junit.runner.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.cache.Cache;

import static com.googlecode.catchexception.CatchException.*;
import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER;
import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER_ID;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryUTest {

   @Mock
   private CustomerDao mockCustomerDao;

   @Mock
   private Cache mockCustomerCache;

   @InjectMocks
   private CustomerRepository customerRepository = new CustomerRepositoryImpl();

   @Test
   public void testSaveCallsCacheAndStore() {
      customerRepository.save(DEFAULT_CUSTOMER);

      verify(mockCustomerDao).save(DEFAULT_CUSTOMER);
      verify(mockCustomerCache).put(DEFAULT_CUSTOMER.getId(), DEFAULT_CUSTOMER);
   }

   @Test
   public void testSaveDoesntCacheWhenItFails() {
      doThrow(new IllegalArgumentException()).when(mockCustomerDao).save(DEFAULT_CUSTOMER);

      catchException(customerRepository).save(DEFAULT_CUSTOMER);

      assertThat(caughtException(), instanceOf(IllegalArgumentException.class));
      verifyZeroInteractions(mockCustomerCache);
   }

   @Test
   public void testDeleteRemovesFromCacheAndStore() {
      customerRepository.save(DEFAULT_CUSTOMER);
      when(mockCustomerDao.remove(DEFAULT_CUSTOMER.getId())).thenReturn(true);

      boolean deleted = customerRepository.delete(DEFAULT_CUSTOMER_ID);

      assertTrue(deleted);
      verify(mockCustomerCache).evict(DEFAULT_CUSTOMER_ID);
      verify(mockCustomerDao).remove(DEFAULT_CUSTOMER_ID);
   }

   @Test
   public void testFindRetrievesFromCache() {
      when(mockCustomerCache.get(DEFAULT_CUSTOMER_ID, Customer.class)).thenReturn(DEFAULT_CUSTOMER);

      Customer customerFound = customerRepository.find(DEFAULT_CUSTOMER_ID);
      assertThat(customerFound, is(DEFAULT_CUSTOMER));
      verify(mockCustomerCache).get(DEFAULT_CUSTOMER_ID, Customer.class);
      verifyZeroInteractions(mockCustomerDao);
   }

   @Test
   public void testFindRetrievesFromStoreAndAddsToCache() {
      when(mockCustomerDao.find(DEFAULT_CUSTOMER_ID)).thenReturn(DEFAULT_CUSTOMER);

      Customer customerFound = customerRepository.find(DEFAULT_CUSTOMER_ID);

      assertThat(customerFound, is(DEFAULT_CUSTOMER));
      verify(mockCustomerCache).get(DEFAULT_CUSTOMER_ID, Customer.class);
      verify(mockCustomerCache).put(DEFAULT_CUSTOMER_ID, DEFAULT_CUSTOMER);
      verify(mockCustomerDao).find(DEFAULT_CUSTOMER_ID);
      verifyNoMoreInteractions(mockCustomerCache, mockCustomerDao);
   }

   @Test
   public void testFindReturnsNullWhenThereIsNoCustomerForIdProvided() {
      Customer customerFound = customerRepository.find(DEFAULT_CUSTOMER_ID);

      assertThat(customerFound, is(nullValue()));
      verify(mockCustomerDao).find(DEFAULT_CUSTOMER_ID);
      verify(mockCustomerCache).get(DEFAULT_CUSTOMER_ID);
      verifyNoMoreInteractions(mockCustomerCache, mockCustomerDao);
   }
}
