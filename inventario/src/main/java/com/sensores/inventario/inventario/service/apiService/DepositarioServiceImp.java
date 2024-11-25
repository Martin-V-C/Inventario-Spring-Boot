package com.sensores.inventario.inventario.service.apiService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sensores.inventario.inventario.Exceptions.BadRequestException;

import com.sensores.inventario.inventario.model.entities.Depositario;
import com.sensores.inventario.inventario.model.entities.Rol;
import com.sensores.inventario.inventario.model.entitiesDtos.DepositarioDto;
import com.sensores.inventario.inventario.model.entitiesDtos.DepositarioMapper;
import com.sensores.inventario.inventario.model.othersDTO.UpdateDepositarioDto;
import com.sensores.inventario.inventario.repository.DepositarioRepositary;
import com.sensores.inventario.inventario.repository.RolRepository;

@Service
public class DepositarioServiceImp implements DepositarioService {

    @Autowired
    DepositarioRepositary depRepositary;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    PasswordEncoder encoder;
    

    @Override
    public List<DepositarioDto> getDepositariosDto() {
        List<Depositario> depositarios = depRepositary.findAll();
        List<DepositarioDto> depositarioDtos = new ArrayList<>();
        for (Depositario depositario : depositarios) {
            depositarioDtos.add(DepositarioMapper.Mapper.deptoDto(depositario));
        }
        return depositarioDtos;
    }

    @Override
    public void deleteDepositario(Integer noEco) {
        Depositario depositario=depRepositary.findByNumeroEco(noEco).orElseThrow(() -> new BadRequestException(
                "El depositario con numero economico " + noEco + " no ha sido encontrado"
        ));
        depRepositary.deleteById(depositario.getId());

    }

    @Override
    public void updateDepositario(Integer noEco, UpdateDepositarioDto request) {
        Depositario depositario = depRepositary.findByNumeroEco(noEco).orElseThrow(() -> new BadRequestException(
                "El depositario con numero economico " + noEco + " no ha sido encontrado"));
        Rol rol = rolRepository.findByTipo(request.getRol())
                .orElseThrow(() -> new BadRequestException("El rol no existe"));
        depositario.setNombre(request.getNombre());
        depositario.setPassword(encoder.encode(request.getPassword()));
        depositario.setUsername(request.getUsername());
        depositario.setRol(rol);
        depRepositary.save(depositario);
    }

    @Override
    public UpdateDepositarioDto getDepositarioForUpdate(Integer noEco) {
        Depositario depositario = depRepositary.findByNumeroEco(noEco).orElseThrow(() -> new BadRequestException(
                "El depositario con numero economico " + noEco + " no ha sido encontrado"));
        var updateDepositarioDto = UpdateDepositarioDto.builder()
        .nombre(depositario.getNombre())
        .username(depositario.getUsername())
        .rol(depositario.getRol().getTipo()).build();

        return updateDepositarioDto;
    }

}
