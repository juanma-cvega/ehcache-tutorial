package com.jusoft.ehcache.tutorial.step.crud;

import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER_NAME;
import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER_SURNAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import javax.inject.Inject;

import org.springframework.cache.Cache;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import com.jusoft.ehcache.tutorial.config.CacheConfig;
import com.jusoft.ehcache.tutorial.dao.CustomerDao;
import com.jusoft.ehcache.tutorial.model.Customer;
import com.jusoft.ehcache.tutorial.repository.CustomerRepository;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = CacheConfig.class)
@DirtiesContext
public class CrudSteps {

	@Inject
	private CustomerRepository customerRepository;
	@Inject
	private Cache customerCache;
	private Cache spiedCustomerCache;
	@Inject
	private CustomerDao customerDao;
	private CustomerDao spiedCustomerDao;

	private Customer customer;

	@Before
	public void setupSpies() {
		spiedCustomerCache = spy(customerCache);
		spiedCustomerDao = spy(customerDao);
		ReflectionTestUtils.setField(customerRepository, "customerDao", spiedCustomerDao);
		ReflectionTestUtils.setField(customerRepository, "customerCache", spiedCustomerCache);
	}

	@Given("^a new customer (\\d+) is created$")
	public void a_new_customer_is_created(final long customerId) throws Throwable {
		customer = createCustomer(customerId);
		customerRepository.save(customer);
	}

	@Given("^customer (\\d+) is not in the system$")
	public void customer_is_not_in_the_system(final long customerId) throws Throwable {
		customerRepository.delete(customerId);
	}

	@Then("^customer (\\d+) should be in the cache$")
	public void customer_should_be_in_the_cache(final long customerId) throws Throwable {
		assertThat(customerCache.get(customerId, Customer.class), is(customer));
	}

	@Then("^customer (\\d+) should be in the store$")
	public void customer_should_be_in_the_system(final long customerId) throws Throwable {
		assertThat(customerDao.find(customerId), is(customer));
	}

	@When("^customer (\\d+) is fetched from the system")
	public void customer_is_fetched_from_store(final long customerId) throws Throwable {
		reset(spiedCustomerCache, spiedCustomerDao);
		customer = customerRepository.find(customerId);
	}

	@Then("^customer (\\d+) should be returned$")
	public void customer_should_be_returned(final long customerId) throws Throwable {
		assertThat(customer, is(notNullValue()));
	}

	@Then("^customer (\\d+) should have been retrieved from cache$")
	public void customer_should_have_been_retrieved_from_cache(final long customerId) throws Throwable {
		verify(spiedCustomerCache).get(customerId, Customer.class);
	}

	@Then("^store should not have been accessed$")
	public void store_should_not_have_been_accessed() throws Throwable {
		verify(spiedCustomerDao, never()).find(anyLong());
	}

	@Given("^customer (\\d+) is not cached$")
	public void customer_is_not_cached(final long customerId) throws Throwable {
		customerCache.evict(customerId);
	}

	@Then("^cache should have been updated with customer (\\d+)$")
	public void cache_should_have_been_updated_with_customer(final long customerId) throws Throwable {
		verify(spiedCustomerCache).put(customerId, customer);
	}

	@Then("^customer (\\d+) should have been retrieved from store$")
	public void customer_should_have_been_retrieved_from_store(final long customerId) throws Throwable {
		verify(spiedCustomerDao).find(customerId);
	}

	@When("^customer (\\d+) is removed$")
	public void customer_is_removed(final long customerId) throws Throwable {
		reset(spiedCustomerCache, spiedCustomerDao);
		customerRepository.delete(customerId);
	}

	@Then("^customer (\\d+) should be removed from cache$")
	public void customer_should_be_removed_from_cache(final long customerId) throws Throwable {
		verify(spiedCustomerCache).evict(customerId);
	}

	@Then("^customer (\\d+) should be removed from store$")
	public void customer_should_be_removed_from_store(final long customerId) throws Throwable {
		verify(spiedCustomerDao).remove(customerId);
	}

	private Customer createCustomer(final long customerId) {
		return new Customer(customerId, DEFAULT_CUSTOMER_NAME, DEFAULT_CUSTOMER_SURNAME);
	}
}
