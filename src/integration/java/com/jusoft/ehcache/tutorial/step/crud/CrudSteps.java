package com.jusoft.ehcache.tutorial.step.crud;

import com.jusoft.ehcache.tutorial.config.CacheConfig;
import com.jusoft.ehcache.tutorial.dao.CustomerDao;
import com.jusoft.ehcache.tutorial.model.Customer;
import com.jusoft.ehcache.tutorial.repository.CustomerRepository;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.cache.Cache;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import javax.inject.Inject;

import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER_NAME;
import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER_SURNAME;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


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
   public void a_new_customer_is_created(long customerId) throws Throwable {
      customer = new Customer(customerId, DEFAULT_CUSTOMER_NAME, DEFAULT_CUSTOMER_SURNAME);
      customerRepository.save(customer);
   }

   @Given("^customer (\\d+) is not in the system$")
   public void customer_is_not_in_the_system(long customerId) throws Throwable {
      customerRepository.delete(customerId);
   }

   @When("^the customer is stored$")
   public void the_customer_is_stored() throws Throwable {
      customerRepository.save(customer);
   }

   @Then("^customer (\\d+) should be in the cache$")
   public void customer_should_be_in_the_cache(long customerId) throws Throwable {
      assertThat(customerCache.get(customerId), is(notNullValue()));
   }

   @Then("^customer (\\d+) should be in the system$")
   public void customer_should_be_in_the_system(long customerId) throws Throwable {
      assertThat(customerDao.find(customerId), is(notNullValue()));
   }

   @When("^customer (\\d+) is fetched from the system")
   public void customer_is_fetched_from_store(long customerId) throws Throwable {
      customer = customerRepository.find(customerId);
   }

   @Then("^customer (\\d+) should be returned$")
   public void customer_should_be_returned(long customerId) throws Throwable {
      assertThat(customer, is(notNullValue()));
   }

   @Then("^customer (\\d+) should have been retrieved from cache$")
   public void customer_should_have_been_retrieved_from_cache(long customerId) throws Throwable {
      verify(spiedCustomerCache).get(customerId, Customer.class);
   }

   @Then("^store should not have been accessed$")
   public void store_should_not_have_been_accessed() throws Throwable {
      verify(spiedCustomerDao, never()).find(anyLong());
   }

   @Given("^customer (\\d+) is not cached$")
   public void customer_is_not_cached(long customerId) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^cache should have been updated with customer (\\d+)$")
   public void cache_should_have_been_updated_with_customer(long customerId) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should have been retrieved from store$")
   public void customer_should_have_been_retrieved_from_store(long customerId) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Given("^customer (\\d+) exists in the system$")
   public void customer_exists_in_the_system(long customerId) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @When("^customer (\\d+) is removed$")
   public void customer_is_removed(long customerId) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should be removed from cache$")
   public void customer_should_be_removed_from_cache(long customerId) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should be removed from store$")
   public void customer_should_be_removed_from_store(long customerId) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should not be in the system$")
   public void customer_should_not_be_in_the_system(long customerId) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should not be in the cache$")
   public void customer_should_not_be_in_the_cache(long customerId) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }
}
