package com.dad.urjcacademy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;


@EnableCaching /** Fase 4 -> Invalidacion de caché **/
@SpringBootApplication
//@EnableHazelcastHttpSession
public class UrjcAcademyApplication {
	
	private static final Log LOG = LogFactory.getLog(UrjcAcademyApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(UrjcAcademyApplication.class, args);
	}
	
	
	/*Fase 4 -> Invalidación de caché **/
	@Bean
	public Config config() {
		Config config = new Config();
		JoinConfig joinConfig = config.getNetworkConfig().getJoin();
		joinConfig.getMulticastConfig().setEnabled(false);
		joinConfig.getTcpIpConfig().addMember("10.11.12.101").addMember("10.11.12.102").setEnabled(true);
		return config;
	}
	
	
	@Bean
	public CacheManager cacheManager() {
		LOG.info("Activating cache...");
		return new ConcurrentMapCacheManager("asignaturas");
	}
	
}
