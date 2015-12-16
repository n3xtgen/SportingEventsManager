package cz.muni.fi.PA165.service.config;

import cz.muni.fi.PA165.service.EntryServiceImpl;
import cz.muni.fi.PA165.service.EventServiceImpl;
import cz.muni.fi.PA165.service.SportServiceImpl;
import cz.muni.fi.PA165.service.facade.EntryFacadeImpl;
import cz.muni.fi.PA165.service.facade.EventFacadeImpl;
import cz.muni.fi.PA165.service.facade.SportFacadeImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


import cz.muni.fi.PA165.ApplicationContext;
import cz.muni.fi.PA165.service.UserServiceImpl;
import cz.muni.fi.PA165.service.facade.UserFacadeImpl;

@Configuration
@Import(ApplicationContext.class)
@ComponentScan(basePackageClasses = {UserServiceImpl.class, UserFacadeImpl.class, SportServiceImpl.class, SportFacadeImpl.class,
		EventServiceImpl.class, EventFacadeImpl.class, EntryServiceImpl.class, EntryFacadeImpl.class})

public class ServiceConfiguration {

	@Bean
		public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();
		return dozer;
	}
	

}

