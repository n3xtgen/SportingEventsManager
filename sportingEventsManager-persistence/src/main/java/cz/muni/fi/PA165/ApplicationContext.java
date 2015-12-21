package cz.muni.fi.PA165;

/**
 * Created by jbouska on 26.10.15.
 */

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@ComponentScan(basePackages = "cz.muni.fi.PA165.dao")
public class ApplicationContext {

	@Bean
	public JpaTransactionManager transactionManager(){
		return  new JpaTransactionManager(entityManagerFactory().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean  entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean jpaFactoryBean = new LocalContainerEntityManagerFactoryBean ();
		jpaFactoryBean.setDataSource(db());
		jpaFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		return jpaFactoryBean;
	}

	@Bean
	public DataSource db(){
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.DERBY).build();
		return db;
	}
}
