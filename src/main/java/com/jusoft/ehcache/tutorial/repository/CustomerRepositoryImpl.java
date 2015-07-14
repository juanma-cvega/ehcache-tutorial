package com.jusoft.ehcache.tutorial.repository;

import com.jusoft.ehcache.tutorial.dao.CustomerDao;
import com.jusoft.ehcache.tutorial.model.Customer;
import org.springframework.cache.Cache;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CustomerRepositoryImpl implements CustomerRepository {

   @Inject
   private CustomerDao customerDao;
   @Inject
   private Cache customerCache;

   @Override
   public void save(Customer customer) {
      customerDao.save(customer);
      customerCache.put(customer.getId(), customer);
   }

   @Override
   public boolean delete(Long id) {
      customerCache.evict(id);
      return customerDao.remove(id);
   }

   @Override
   public Customer find(Long id) {
      Customer customer = customerCache.get(id, Customer.class);
      if (customer == null) {
         customer = customerDao.find(id);
         if (customer != null) {
            customerCache.put(customer.getId(), customer);
         }
      }
      return customer;
   }
}
