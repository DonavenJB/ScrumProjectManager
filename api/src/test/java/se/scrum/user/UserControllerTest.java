package se.scrum.controller.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import se.scrum.ApiApplication;
import se.scrum.domain.user.User;
import se.scrum.service.user.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author fidde test for UserController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ApiApplication.class })
public class UserControllerTest {

    private static final String USERS = "/users";
    private MockMvc mvc;
    private User user;
    @Autowired
    private UserService userService;
    private ObjectMapper mapper;
    private String json;
    private String id;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        user = new User("foodd", "bartd");
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        mapper = new ObjectMapper();

    }

    @After
    public void teardown() {
        userService.dropAllUsers();
    }

    @Test
    public void testGetLinks() throws Exception {
        mvc.perform(get(USERS)).andExpect(status().isOk());
    }

    @Test
    public void testAddUser() throws Exception {
        json = mapper.writeValueAsString(user);

        mvc.perform(
                post(USERS).contentType(MediaType.APPLICATION_JSON).content(
                        json)).andExpect(status().isCreated());
    }

    @Test
    public void testAddUser_fail_nameTooShort() throws Exception {
        user.setName("");
        json = mapper.writeValueAsString(user);

        mvc.perform(
                post(USERS).contentType(MediaType.APPLICATION_JSON).content(
                        json)).andExpect(status().isNotAcceptable());
    }

    @Test
    public void testAddUser_fail_nameIsNull() throws Exception {
        user.setName(null);
        json = mapper.writeValueAsString(user);

        mvc.perform(
                post(USERS).contentType(MediaType.APPLICATION_JSON).content(
                        json)).andExpect(status().isNotAcceptable());
    }

    @Test
    public void testAddUser_fail_passwordIsNull() throws Exception {
        user.setPassword(null);
        json = mapper.writeValueAsString(user);

        mvc.perform(
                post(USERS).contentType(MediaType.APPLICATION_JSON).content(
                        json)).andExpect(status().isNotAcceptable());
    }

    @Test
    public void testAddUser_fail_passwordIsTooShort() throws Exception {
        user.setPassword("");
        json = mapper.writeValueAsString(user);

        mvc.perform(
                post(USERS).contentType(MediaType.APPLICATION_JSON).content(
                        json)).andExpect(status().isNotAcceptable());
    }

    @Test
    public void testAddUser_fail_nameTaken() throws Exception {
        json = mapper.writeValueAsString(user);

        mvc.perform(
                post(USERS).contentType(MediaType.APPLICATION_JSON).content(
                        json)).andExpect(status().isCreated());

        mvc.perform(
                post(USERS).contentType(MediaType.APPLICATION_JSON).content(
                        json)).andExpect(status().isNotAcceptable());
    }

    @Test
    public void testGetUser() throws Exception {

        // we expect to be denied since we dont have a token
        id = userService.addUser(user).getId();
        mvc.perform(get(USERS + "/{id}", id)).andExpect(
                status().isUnauthorized());
    }

    @Test
    public void testRemoveUser() throws Exception {
        id = userService.addUser(user).getId();

        // we expect to be denied since we dont have a token
        mvc.perform(delete(USERS + "/{id}", id)).andExpect(
                status().isUnauthorized());
    }

    @Test
    public void testGetAuth() throws Exception {
        userService.addUser(user);
        String content = mapper.writeValueAsString(user);
        MvcResult result = mvc
                .perform(
                        post(USERS + "/auth").contentType(
                                MediaType.APPLICATION_JSON).content(content))
                .andExpect(status().isOk()).andReturn();

        String header = result.getResponse().getHeader("Token");
        assertNotNull("can get token", header);

        String[] split = header.split(":");
        assertEquals("can get name", user.getName(), split[0]);
    }

}
