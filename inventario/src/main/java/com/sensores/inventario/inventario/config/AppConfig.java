package com.sensores.inventario.inventario.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sensores.inventario.inventario.model.repository.DepositarioRepositary;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final DepositarioRepositary depositarioRepositary;

    @Bean
    public UserDetailsService userDetailsService(){
                return username -> depositarioRepositary.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("no se encontro el usuario"));
                }
                
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());        
        daoAuthenticationProvider.setPasswordEncoder(PasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }


}
