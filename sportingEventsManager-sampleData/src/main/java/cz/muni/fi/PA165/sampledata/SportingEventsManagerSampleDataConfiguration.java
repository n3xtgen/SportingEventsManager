package cz.muni.fi.PA165.sampledata;

import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class SportingEventsManagerSampleDataConfiguration {

    final static Logger log = LoggerFactory.getLogger(SportingEventsManagerSampleDataConfiguration.class);

    @Autowired
    SampleDataLoadingFacade sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() throws IOException {
        log.debug("dataLoading()");
        sampleDataLoadingFacade.loadData();
    }
}
