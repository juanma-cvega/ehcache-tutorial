package com.jusoft.ehcache.tutorial.dao;

import com.jusoft.ehcache.tutorial.model.Customer;

/**
 * Created by carnicj on 13/07/2015.
 */
public class CustomerDaoImpl implements CustomerDao {

   @Override
   public Long save(Customer objectToSave) {
      return null;
   }

   @Override
   public boolean update(Customer objectToUpdate) {
      return false;
   }

   @Override
   public boolean remove(Long aLong) {
      return false;
   }
}
