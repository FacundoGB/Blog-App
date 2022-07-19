package com.spring.blogapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 1 << 18, 4);
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // disable CSRF as we do not serve browser clients
                .csrf().disable()
                // add JWT authorization filter
                // allow access restriction using request matcher
                .authorizeRequests()
                // authenticate requests to endpoint
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()
                // allow all other requests
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        // make sure we use stateless session, session will not be used to store user's state
        //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

    @Bean
    protected InMemoryUserDetailsManager configureAuthentication(){
        UserDetails Juan = User.builder().username("Juan")
                .password(passwordEncoder().encode("password")).roles("USER").build();
        UserDetails Susana = User.builder().username("admin")
                .password(passwordEncoder().encode("admin")).roles("ADMIN").build();

        return new InMemoryUserDetailsManager(Juan,Susana);
    }


   /* public void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetails Juan = User.builder().username("Juan")
                        .password(passwordEncoder().encode("password")).roles("USER").build();
        auth.inMemoryAuthentication().withUser(Juan);
        UserDetails Susana = User.builder().username("Susana")
                .password(passwordEncoder().encode("password")).roles("ADMIN").build();
        auth.inMemoryAuthentication().withUser(Juan);
    }*/
}
