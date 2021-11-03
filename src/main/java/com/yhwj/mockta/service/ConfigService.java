package com.yhwj.mockta.service;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.env.ConfigurablePropertyResolver;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class ConfigService {
	private static ConfigService instance = new ConfigService();
	private final MutablePropertySources propertySources;

	private final ConfigurablePropertyResolver propertyResolver;
	
	private ConfigService() {
		this(new MutablePropertySources());
	}

	public static ConfigService getInstance(){
		return instance;
	}
	
	public String getProperty(String key){
		return this.propertyResolver.getProperty(key);
	}
	
	protected ConfigService(MutablePropertySources propertySources) {
		this.propertySources = propertySources;
		this.propertyResolver = createPropertyResolver(propertySources);
		try {
			customizePropertySources(propertySources);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected ConfigurablePropertyResolver createPropertyResolver(MutablePropertySources propertySources) {
		return new PropertySourcesPropertyResolver(propertySources);
	}
	
	protected void customizePropertySources(MutablePropertySources propertySources) throws IOException {
		//ClassPathResource classPathResource = new ClassPathResource("config/*.properties");
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();        
	    //Resource[] resources=resolver.getResources("classpath*:META-INF/INDEX.LIST");  
	    Resource[] resources = resolver.getResources("classpath:config/*.properties");  
	    
	    //System.out.println(resources.length);
	    for(Resource res: resources){
	    	String filename = res.getFilename();
	    	//System.out.println(filename);
	    	Properties prop = new Properties();
	    	prop.load(res.getInputStream());
	    	propertySources.addLast(new PropertiesPropertySource(res.getFilename(), prop));
	    }
	    
	    Properties prop = new Properties();
	    Resource res = new ClassPathResource("application.properties");
	    prop.load(res.getInputStream());
	    propertySources.addLast(new PropertiesPropertySource("application.properties", prop));
	    
	}

}
