package cz.muni.fi.PA165.service.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


import cz.muni.fi.PA165.ApplicationContext;

@Configuration
@Import(ApplicationContext.class)

public class ServiceConfiguration {

	@Bean
		public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();
		return dozer;
	}
	

}

