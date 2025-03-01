package com.sensores.inventario.inventario.service.securityService;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sensores.inventario.inventario.Exceptions.BadRequestException;
import com.sensores.inventario.inventario.Exceptions.ResourceNotFoundException;
import com.sensores.inventario.inventario.model.authDtos.AuthResponse;
import com.sensores.inventario.inventario.model.authDtos.LoginRequest;
import com.sensores.inventario.inventario.model.authDtos.RegisterRequest;
import com.sensores.inventario.inventario.model.entities.Depositario;
import com.sensores.inventario.inventario.model.entities.Rol;
import com.sensores.inventario.inventario.model.entitiesDtos.DepositarioDto;
import com.sensores.inventario.inventario.model.entitiesDtos.DepositarioMapper;
import com.sensores.inventario.inventario.repository.DepositarioRepositary;
import com.sensores.inventario.inventario.repository.RolRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private final DepositarioRepositary depRepositary;
    private final PasswordEncoder encoder;
    private final RolRepository rolRepository;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    /**
     * Registra un depositario.
     * 
     * @param request objeto que contiene la informacion del depositario a registrar
     * @return objeto que contiene el mensaje de exito
     * @throws BadRequestException si el depositario con el numero economico especificado
     *             ya existe o si el rol especificado no existe
     */
    @Override
    public AuthResponse register(RegisterRequest request) {
        if (depRepositary.existsByNumeroEco(request.getNumeroEco())) {
            throw new BadRequestException("El depositario con numero economico " + request.getNumeroEco() + " ya existe");
        }
        Rol rol = rolRepository.findByTipo(request.getRol()).orElseThrow(() -> new BadRequestException("El rol no existe"));

        var usuario = Depositario.builder()
                .nombre(request.getNombre())
                .numeroEco(request.getNumeroEco())
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .rol(rol)
                .build();
        depRepositary.save(usuario);
        var jwToken = jwtService.generateToken(usuario);

        return AuthResponse.builder()
                .token(jwToken)
                .depositario(DepositarioMapper.Mapper.deptoDto(usuario))
                .build();
    }

/**
 * Autentica un depositario y genera un token JWT.
 *
 * @param request objeto que contiene el nombre de usuario y la contraseña del depositario
 * @return un objeto AuthResponse que contiene el token JWT y la información del depositario
 * @throws ResourceNotFoundException si el depositario no ha sido encontrado
 */

    @Override
    public AuthResponse login(LoginRequest request) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), 
                    request.getPassword())
                    );
        var usuario = depRepositary.findByUsername(request.getUsername()).orElseThrow(() -> new ResourceNotFoundException("El depositario no ha sido encontrado"));
        DepositarioDto dep=DepositarioMapper.Mapper.deptoDto(usuario);
        var jwToken=jwtService.generateToken(usuario);
        return AuthResponse.builder().token(jwToken).depositario(dep).build();
    }

}
