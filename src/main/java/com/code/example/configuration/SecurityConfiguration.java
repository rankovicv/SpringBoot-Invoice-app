package com.code.example.configuration;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by veljko on 9.9.18.
 */
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final @NonNull
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
//        auth.
//                jdbcAuthentication()
//                .usersByUsernameQuery(usersQuery)
//                .authoritiesByUsernameQuery(rolesQuery)
//                .dataSource(dataSource)
//                .passwordEncoder(bCryptPasswordEncoder);

        auth
                .userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/customers").hasAuthority("CLIENT")
                .antMatchers("/index").authenticated()
                .antMatchers("/customer").authenticated()
                .antMatchers("/products").authenticated()
//                .antMatchers("/customer").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and().logout()    //logout configuration
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**","/webjars/**");
    }

}

