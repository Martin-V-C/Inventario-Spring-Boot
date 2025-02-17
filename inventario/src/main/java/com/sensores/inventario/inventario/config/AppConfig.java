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

import com.sensores.inventario.inventario.repository.DepositarioRepositary;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final DepositarioRepositary depositarioRepositary;

    /**
     * UserDetailsService que carga un depositario por su username.
     * Lanza una UsernameNotFoundException si no se encuentra el depositario.
     * @return UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService(){
                return username -> depositarioRepositary.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("no se encontro el usuario"));
                }
                

    /**
     * Proveedor de autenticacion que utiliza un UserDetailsService 
     * para obtener los datos del depositario y un PasswordEncoder 
     * para verificar la contrasenia del depositario.
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());        
        daoAuthenticationProvider.setPasswordEncoder(PasswordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Proveedor de encriptacion de contraseñas. El proveedor por defecto
     * es BCryptPasswordEncoder.
     * @return Proveedor de encriptacion de contraseñas.
     */
    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean que devuelve el AuthenticationManager configurado por defecto
     * por Spring Security.
     * @param config Configuracion de autenticacion de Spring Security.
     * @return AuthenticationManager configurado por defecto.
     * @throws Exception Si no se puede obtener el AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }


}
