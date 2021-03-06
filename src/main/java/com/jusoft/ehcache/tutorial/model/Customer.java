package com.jusoft.ehcache.tutorial.model;

import lombok.Data;

@Data
public class Customer implements Entity<Long> {

   private static final long serialVersionUID = -2260437109766862592L;
   private final Long id;
   private final String name;
   private final String surname;

}
