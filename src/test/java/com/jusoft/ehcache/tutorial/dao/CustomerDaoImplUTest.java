package com.jusoft.ehcache.tutorial.dao;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.cache.Cache;

import static com.jusoft.ehcache.tutorial.util.Fixtures.DEFAULT_CUSTOMER;
import static org.mockito.Mockito.verify;

/**
 * Created by carnicj on 13/07/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerDaoImplUTest {

   @Mock
   private Cache mockCache;

   @InjectMocks
   private CustomerDao customerDao = new CustomerDaoImpl();
//   private S mockStore;

   @Test
   public void testSaveStoresInCacheAndStore() {
      final Long customerSaved = customerDao.save(DEFAULT_CUSTOMER);

      verify(mockCache).get(DEFAULT_CUSTOMER.getId());
//      verify(mockStore)
   }
}
