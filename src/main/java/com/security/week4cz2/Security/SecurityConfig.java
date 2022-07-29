package com.security.week4cz2.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

public SecurityConfig(PasswordEncoder passwordEncoder){
    this.passwordEncoder = passwordEncoder;
}

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                .antMatchers("/api/getadmin").permitAll()
//                .anyRequest().hasRole("admin")
//                .and()
//                .httpBasic();
        http
                .csrf()
                .disable()
                .httpBasic();
    }


    @Bean
   @Override
    protected UserDetailsService userDetailsService() {
  //      UserDetails user = User
//
//                .withUsername("user")
//                .password(passwordEncoder.encode("user"))
//                .roles("user")
//                .build();
//
//        UserDetails admin = User
//                .withUsername("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("admin")
//                .build();
//        return new InMemoryUserDetailsManager(user, admin);
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("user"))
                .authorities(Permit.USER_READ.name(), Permit.USER_EDIT.name())
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities(Permit.ADMIN.name())
                .build();

        UserDetails spectator = User.withUsername("spectator")
                .password(passwordEncoder.encode("spectator"))
                .authorities(Permit.USER_READ.name())
                .build();


        return new InMemoryUserDetailsManager(
                user,
                admin,
                spectator
        );
    }



    public enum Permit {
        USER_READ, USER_EDIT, ADMIN
    }

    }


