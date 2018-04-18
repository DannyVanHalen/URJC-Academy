package com.dad.urjcacademy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

/** Fase 4 -> Invalidacion de caché **/
@EnableCaching
@SpringBootApplication
public class UrjcAcademyApplication {
	
	private static final Log LOG = LogFactory.getLog(UrjcAcademyApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(UrjcAcademyApplication.class, args);
	}
	
	
	/*Fase 4 -> Invalidación de caché **/
	@Bean
	public CacheManager cacheManager() {
		LOG.info("Activating cache...");
		return new ConcurrentMapCacheManager("usuarios");
	}
	
}
