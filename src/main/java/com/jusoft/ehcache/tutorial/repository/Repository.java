package com.jusoft.ehcache.tutorial.repository;

import com.jusoft.ehcache.tutorial.model.Entity;

import java.io.Serializable;

public interface Repository<T extends Entity<ID>, ID extends Serializable> {
   void save(T objectToSave);

   boolean delete(ID id);

   T find(ID id);
}
