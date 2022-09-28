package com.yrol.pma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

/**
 * Authentication being used with JDBC.
 * */
@Configuration
@EnableWebSecurity
@Profile("dev-jdbc")
public class SecurityConfigurationJDBC {


    /**
     * DataSource will automatically point to whatever is defined in properties - in this case JDBC
     * */
    @Autowired
    DataSource dataSource;

    /**
     * Made available / loaded via BCryptPasswordEncoder Bean in WebConfig.java
     * */
    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    /**
     * User Authentication connected to the JDBC datasource
     * The tables "user_accounts" and "user_accounts_seq" must exist prior to running this.
     * Using the encrypted(BCrypt) passwords generated via: https://bcrypt-generator.com/
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled " +
                        "from user_accounts where username = ?")
                .authoritiesByUsernameQuery("select username, role " +
                        "from user_accounts where username = ?").dataSource(dataSource)
                .passwordEncoder(bCryptEncoder)
                .and().build();
    }

    /**
     * User Authorization mapping. Ex: only ADMIN users can create new projects and Employees. Everything else "/", "/**" can be access by normal users.
     * */
    @Bean
    public SecurityFilterChain authorizationManager(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/projects/new").hasRole("ADMIN")
                .antMatchers("/projects/save").hasRole("ADMIN")
                .antMatchers("/employees/new").hasRole("ADMIN")
                .antMatchers("/employees/save").hasRole("ADMIN")
                .antMatchers("/", "/**").permitAll()
                .and().formLogin();

        return http.build();
    }
}
