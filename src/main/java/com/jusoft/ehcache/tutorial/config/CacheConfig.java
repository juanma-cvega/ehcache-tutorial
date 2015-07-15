package com.jusoft.ehcache.tutorial.config;

import static net.sf.ehcache.store.MemoryStoreEvictionPolicy.MemoryStoreEvictionPolicyEnum.LRU;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@ComponentScan("com.jusoft")
@EnableCaching
public class CacheConfig implements CachingConfigurer {

	private static final int MAXIMUM_ENTRIES_LOCAL_HEAP = 1000;
	private static final String CUSTOMER_CACHED_NAME = "customer";

	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		return net.sf.ehcache.CacheManager.newInstance(getCacheConfiguration());
	}

	@Override
	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(net.sf.ehcache.CacheManager.newInstance(getCacheConfiguration()));
	}

	@Bean
	public Cache customerCache() {
		return cacheManager().getCache(CUSTOMER_CACHED_NAME);
	}

	private net.sf.ehcache.config.Configuration getCacheConfiguration() {
		CacheConfiguration cacheConfiguration = new CacheConfiguration();
		cacheConfiguration.setName(CUSTOMER_CACHED_NAME);
		cacheConfiguration.setMemoryStoreEvictionPolicy(LRU.name());
		cacheConfiguration.setMaxEntriesLocalHeap(MAXIMUM_ENTRIES_LOCAL_HEAP);

		net.sf.ehcache.config.Configuration configuration = new net.sf.ehcache.config.Configuration();
		configuration.addCache(cacheConfiguration);
		return configuration;
	}

	@Override
	public CacheResolver cacheResolver() {
		return null;
	}

	@Override
	@Bean
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}

	@Override
	public CacheErrorHandler errorHandler() {
		return null;
	}
}
