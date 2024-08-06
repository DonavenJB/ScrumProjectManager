package se.scrum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import se.scrum.auth.TokenFilter;
import se.scrum.controller.user.UserController;
import se.scrum.service.user.UserService;

/**
 * Spring security config
 * 
 * @author fidde
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // we make sure that static resources are available as well as basic api
        // functions
        http.addFilterAfter(new TokenFilter(service),
                BasicAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, UserController.USERS)
                .permitAll()
                .antMatchers(HttpMethod.POST, UserController.USERS,
                        UserController.USERS + UserController.AUTH).permitAll()
                .antMatchers(UserController.USERS + "/**").hasRole("USER")
                .antMatchers("/**").permitAll().antMatchers("/**/**")
                .permitAll().and().csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {

        auth.userDetailsService(userDetailsService);
    }

}
