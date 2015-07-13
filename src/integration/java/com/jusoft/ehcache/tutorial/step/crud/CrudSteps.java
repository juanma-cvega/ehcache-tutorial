package com.jusoft.ehcache.tutorial.step.crud;

import com.jusoft.ehcache.tutorial.config.CacheConfig;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.test.context.ContextConfiguration;


/**
 * Created by carnicj on 13/07/2015.
 */
@ContextConfiguration(classes = CacheConfig.class)
public class CrudSteps {

   @Given("^a new customer (\\d+) is created$")
   public void a_new_customer_is_created(int customerId) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Given("^customer (\\d+) is not in the system$")
   public void customer_is_not_in_the_system(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @When("^the customer is stored$")
   public void the_customer_is_stored() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should be in the cache$")
   public void customer_should_be_in_the_cache(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should be in the system$")
   public void customer_should_be_in_the_system(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Given("^customer (\\d+) is cached$")
   public void customer_is_cached(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @When("^customer (\\d+) is fetched from store$")
   public void customer_is_fetched_from_store(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should be returned$")
   public void customer_should_be_returned(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should have been retrieved from cache$")
   public void customer_should_have_been_retrieved_from_cache(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^store should not have been accessed$")
   public void store_should_not_have_been_accessed() throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Given("^customer (\\d+) is not cached$")
   public void customer_is_not_cached(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^cache should have been updated with customer (\\d+)$")
   public void cache_should_have_been_updated_with_customer(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should have been retrieved from store$")
   public void customer_should_have_been_retrieved_from_store(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Given("^customer (\\d+) exists in the system$")
   public void customer_exists_in_the_system(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @When("^customer (\\d+) is removed$")
   public void customer_is_removed(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should be removed from cache$")
   public void customer_should_be_removed_from_cache(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should be removed from store$")
   public void customer_should_be_removed_from_store(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should not be in the system$")
   public void customer_should_not_be_in_the_system(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }

   @Then("^customer (\\d+) should not be in the cache$")
   public void customer_should_not_be_in_the_cache(int arg1) throws Throwable {
      // Write code here that turns the phrase above into concrete actions
      throw new PendingException();
   }
}
