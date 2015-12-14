/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.PA165.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.PA165.RootWebContext;
import cz.muni.fi.PA165.dto.CreateSportsmanDTO;
import cz.muni.fi.PA165.dto.SportsmanDTO;
import cz.muni.fi.PA165.dto.facade.SportsmanFacade;
import cz.muni.fi.PA165.rest.controllers.UsersController;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author jbouska
 */
@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class UserControllerTest extends AbstractTestNGSpringContextTests {

    @Mock
    private SportsmanFacade userFacade;

    @Autowired
    @InjectMocks
    private UsersController usersController;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(usersController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    }

    @Test
    public void getAllUsers() throws Exception {

        doReturn(Collections.unmodifiableList(this.createUsers())).when(userFacade).getAllSportsmans();

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.idSportsman==1)].name").value("John"))
                .andExpect(jsonPath("$.[?(@.idSportsman==2)].name").value("Petr"));

    }

    @Test
    public void createUser() throws Exception {

        CreateSportsmanDTO sp = new CreateSportsmanDTO();
        sp.setName("newUser");
        sp.setSurname("surname");
        sp.setPassword("password");
        sp.setEmail("newUser@mail.cz");

        doReturn(1l).when(userFacade).registerSportsman(
                any(CreateSportsmanDTO.class));

        String json = this.convertObjectToJsonBytes(sp);

        System.out.println(json);

        mockMvc.perform(
                post("/users/create").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

    }

    @Test
    public void getValidUser() throws Exception {

        List<SportsmanDTO> users = this.createUsers();

        doReturn(users.get(0)).when(userFacade).findSportsmanById(1l);
        doReturn(users.get(1)).when(userFacade).findSportsmanById(2l);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.surname").value("Smith"));

        mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.surname").value("Pecka"));

    }

    @Test
    public void getInvalidUser() throws Exception {
        doReturn(null).when(userFacade).findSportsmanById(1l);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().is4xxClientError());

    }

    private List<SportsmanDTO> createUsers() {
        SportsmanDTO userOne = new SportsmanDTO();
        userOne.setIdSportsman(1l);
        userOne.setName("John");
        userOne.setSurname("Smith");
        userOne.setEmail("Smith@mail.com");

        SportsmanDTO userTwo = new SportsmanDTO();
        userTwo.setIdSportsman(2l);
        userTwo.setName("Petr");
        userTwo.setSurname("Pecka");
        userTwo.setEmail("Pecka@mail.cz");

        return Arrays.asList(userOne, userTwo);
    }

    private static String convertObjectToJsonBytes(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsString(object);
    }

}
