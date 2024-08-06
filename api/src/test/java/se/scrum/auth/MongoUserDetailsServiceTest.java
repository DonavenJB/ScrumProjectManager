package se.scrum.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.scrum.ApiApplication;
import se.scrum.domain.user.User;
import se.scrum.repo.UserRepo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApiApplication.class })
public class MongoUserDetailsServiceTest {

    private UserDetailsServiceImpl service;
    @Autowired
    private UserRepo repo;

    @Before
    public void setUp() throws Exception {
        service = new UserDetailsServiceImpl(repo);
    }

    @Test
    public void testGetService() {
        assertNotNull("can get service", service);
    }

    @Test
    public void testGetDetails() throws Exception {
        repo.save(new User("foo", "bar"));
        UserDetails result = service.loadUserByUsername("foo");
        assertNotNull("can get details", result);

        assertEquals("user is named foo", "foo", result.getUsername());
        assertEquals("password is bar", "bar", result.getPassword());
    }

}
