package cz.muni.fi.PA165.service.config;

import cz.muni.fi.PA165.service.SportServiceImpl;
import cz.muni.fi.PA165.service.facade.SportFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


import cz.muni.fi.PA165.ApplicationContext;
import cz.muni.fi.PA165.service.SportsmanServiceImpl;
import cz.muni.fi.PA165.service.facade.SportsmanFacadeImpl;

@Configuration
@Import(ApplicationContext.class)
@ComponentScan(basePackageClasses = {SportsmanServiceImpl.class, SportsmanFacadeImpl.class, SportServiceImpl.class, SportFacadeImpl.class})
public class ServiceConfiguration {

	@Bean
		public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();
		return dozer;
	}
	

}

