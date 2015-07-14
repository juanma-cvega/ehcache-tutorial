package com.jusoft.ehcache.tutorial.dao;

import com.jusoft.ehcache.tutorial.model.Customer;

import javax.inject.Named;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Named
public class CustomerDaoImpl implements CustomerDao {

	private Map<Long, Customer> store = new ConcurrentHashMap<>();
	
	@Override
	public void save(Customer customer) {
		if (customer == null) {
			throw new IllegalArgumentException("Null values cannot be stored");
		}
		store.put(customer.getId(), customer);
	}

	@Override
	public boolean update(Customer customer) {
		store.put(customer.getId(), customer);
		return true;
	}

	@Override
	public boolean remove(Long id) {
		Customer removedCustomer = store.remove(id);
		boolean result = true;
		if (removedCustomer == null) {
			result = false;
		}
		return result;
	}

	@Override
	public Customer find(Long id) {
		return store.get(id);
	}
}
