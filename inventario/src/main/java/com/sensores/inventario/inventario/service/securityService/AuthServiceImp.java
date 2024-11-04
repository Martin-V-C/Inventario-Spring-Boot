package com.sensores.inventario.inventario.service.securityService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sensores.inventario.inventario.model.dto.auth.AuthResponse;
import com.sensores.inventario.inventario.model.dto.auth.LoginRequest;
import com.sensores.inventario.inventario.model.dto.auth.RegisterRequest;
import com.sensores.inventario.inventario.model.entities.Depositario;
import com.sensores.inventario.inventario.model.entities.Rol;
import com.sensores.inventario.inventario.model.repository.DepositarioRepositary;
import com.sensores.inventario.inventario.model.repository.RolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final DepositarioRepositary depRepositary;
    private final PasswordEncoder encoder;
    private final RolRepository rolRepository;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        Rol rol = rolRepository.findByTipo("User").orElseThrow(() -> new RuntimeException("El rol no existe"));

        var usuario = Depositario.builder()
                .nombre(request.getNombre())
                .no_economico(request.getNo_economico())
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .rol(rol)
                .build();
        depRepositary.save(usuario);
        var jwToken = jwtService.generateToken(usuario);

        return AuthResponse.builder()
                .token(jwToken).build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), 
                    request.getPassword())
                    );
        var usuario = depRepositary.findByUsername(request.getUsername()).orElseThrow();
        var jwToken=jwtService.generateToken(usuario);
        return AuthResponse.builder().token(jwToken).build();
    }

}
