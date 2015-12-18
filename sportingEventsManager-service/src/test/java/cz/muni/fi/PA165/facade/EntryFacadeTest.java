package cz.muni.fi.PA165.facade;

import cz.muni.fi.PA165.dto.facade.EntryFacade;
import cz.muni.fi.PA165.service.config.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by Jamik on 17.12.2015.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EntryFacadeTest extends AbstractTestNGSpringContextTests  {

    @Autowired
    private EntryFacade entryFacade;

    @BeforeClass
    public void setupData(){

    }

    @Test
    public void shouldCreate(){

    }

    @Test
    public void shouldUpdate(){

    }

    @Test
    public void shouldDelete(){

    }

    @Test
    public void shouldFindById(){

    }

    @Test
    public void shouldFindBySportId(){

    }

    @Test
    public void shouldFindByUserId(){

    }

    @Test
    public void shouldFindBySportAndUserId(){

    }
}
