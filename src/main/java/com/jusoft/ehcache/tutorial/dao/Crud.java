package com.jusoft.ehcache.tutorial.dao;

import com.jusoft.ehcache.tutorial.model.Entity;

import java.io.Serializable;

/**
 * Created by carnicj on 13/07/2015.
 */
public interface Crud<T extends Entity, ID extends Serializable> {

   ID save(T objectToSave);

   boolean update(T objectToUpdate);

   boolean remove(ID id);
}
