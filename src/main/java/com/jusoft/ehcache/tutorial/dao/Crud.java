package com.jusoft.ehcache.tutorial.dao;

import com.jusoft.ehcache.tutorial.model.Entity;

import java.io.Serializable;

public interface Crud<T extends Entity<ID>, ID extends Serializable> {

   void save(T objectToSave);

   boolean update(T objectToUpdate);

   boolean remove(ID id);

   T find(ID id);
}
