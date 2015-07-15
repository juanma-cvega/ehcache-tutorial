package com.jusoft.ehcache.tutorial.repository;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER;
import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER_ID;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.cache.Cache;

import com.jusoft.ehcache.tutorial.dao.CustomerDao;
import com.jusoft.ehcache.tutorial.model.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryUTest {

	@Mock
	private CustomerDao mockCustomerDao;

	@Mock
	private Cache mockCustomerCache;

	@InjectMocks
	private final CustomerRepository customerRepository = new CustomerRepositoryImpl();

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
		verify(mockCustomerCache).get(DEFAULT_CUSTOMER_ID, Customer.class);
		verifyNoMoreInteractions(mockCustomerCache, mockCustomerDao);
	}
}
