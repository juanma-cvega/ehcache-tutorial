package com.jusoft.ehcache.tutorial.util;

import com.jusoft.ehcache.tutorial.model.Customer;

/**
 * Created by carnicj on 13/07/2015.
 */
public final class Fixtures {
   private Fixtures() {
   }

   public static final String DEFAULT_CUSTOMER_SURNAME = "surname";
   public static final String DEFAULT_CUSTOMER_NAME = "name";
   public static final Long DEFAULT_CUSTOMER_ID = 1L;
   public static final Customer DEFAULT_CUSTOMER = new Customer(DEFAULT_CUSTOMER_ID, DEFAULT_CUSTOMER_NAME, DEFAULT_CUSTOMER_SURNAME);
}
