package com.international.codyweb.services;

//import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CachingService {
	
	@Autowired
	CacheManager cacheManager;
	
	public void evictAllCaches() {
//		Stream<String> caches = cacheManager.getCacheNames().stream();
//		caches.iterator()
		
	    cacheManager.getCacheNames().stream()
	      .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
	}
	
	//evict all cache every 3 sec
//	@Scheduled(fixedRate = 10000)
//	public void evictAllcachesAtIntervals() {
//		System.out.println("Evicting ...");
//	    evictAllCaches();
//	}
}
