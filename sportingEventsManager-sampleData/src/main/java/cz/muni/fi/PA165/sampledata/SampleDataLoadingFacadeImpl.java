package cz.muni.fi.PA165.sampledata;

import cz.muni.fi.PA165.entity.Sportsman;
import cz.muni.fi.PA165.service.SportsmanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private SportsmanService sportsmanService;
   
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        
       Sportsman sp = sportsman("password", "admin","bbb","email@mail.com");
        
    }

  
    private Sportsman sportsman(String password, String name, String surname, String email) {
        Sportsman s = new Sportsman();
        s.setName(name);
        s.setSurname(surname);
        s.setEmail(email);
       
        sportsmanService.registerSportsman(s, password);
        log.debug("register sportsman" + s);
        return s;
    }

   
    
}
