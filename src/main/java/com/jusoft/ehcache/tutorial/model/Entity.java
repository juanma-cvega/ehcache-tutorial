package com.jusoft.ehcache.tutorial.model;

import java.io.Serializable;

/**
 * Created by carnicj on 13/07/2015.
 */
public interface Entity<ID extends Serializable> extends Serializable {
   ID getId();
}
