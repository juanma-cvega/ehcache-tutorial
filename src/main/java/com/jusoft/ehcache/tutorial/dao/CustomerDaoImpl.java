package com.jusoft.ehcache.tutorial.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jusoft.ehcache.tutorial.model.Customer;

/**
 * Created by carnicj on 13/07/2015.
 */
public class CustomerDaoImpl implements CustomerDao {

	private Map<Long, Customer> store = new ConcurrentHashMap<>();
	
	@Override
	public Long save(Customer customer) {
		store.put(customer.getId(), customer);
		return customer.getId();
	}

	@Override
	public boolean update(Customer customer) {
		store.put(customer.getId(), customer);
		return true;
	}

	@Override
	public boolean remove(Long id) {
		if (store.containsKey(id)) {
			store.remove(id);
			return true;
		}
		return false;
	}

	@Override
	public Customer find(Long id) {
		return store.get(id);
	}
}
