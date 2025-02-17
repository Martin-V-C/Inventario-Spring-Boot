package com.sensores.inventario.inventario.service.apiService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sensores.inventario.inventario.Exceptions.BadRequestException;
import com.sensores.inventario.inventario.Exceptions.ResourceNotFoundException;
import com.sensores.inventario.inventario.model.entities.Bienes;
import com.sensores.inventario.inventario.model.entities.Depositario;
import com.sensores.inventario.inventario.model.entities.Ubicacion;
import com.sensores.inventario.inventario.model.entitiesDtos.BienMapper;
import com.sensores.inventario.inventario.model.entitiesDtos.BienesDto;
import com.sensores.inventario.inventario.model.othersDTO.Bienflat;
import com.sensores.inventario.inventario.repository.BienesRepository;
import com.sensores.inventario.inventario.repository.DepositarioRepositary;
import com.sensores.inventario.inventario.repository.UbicacionesRepository;

import lombok.var;

@Service
public class BienesServiceImp implements BienesService {

    @Autowired
    BienesRepository bienesRepository;

    @Autowired
    DepositarioRepositary depositarioRepositary;

    @Autowired
    UbicacionesRepository ubicacionRepository;

    /**
     * Devuelve una lista de todos los bienes
     * @return una lista de BienesDto
     */
    @Override
    public List<BienesDto> getListaBienes() {
        List<Bienes> bienes = bienesRepository.findAll();
        List<BienesDto> bienesDtos = new ArrayList<>();
        for (Bienes bien : bienes) {
            bienesDtos.add(BienMapper.Mapper.BienToDto(bien));
        }
        return bienesDtos;
    }

    /**
     * Devuelve el bien con el id especificado
     * @param id el id del bien a obtener
     * @return el bien con el id especificado
     */
    @Override
    public Bienflat getBien(Integer id) {
        Bienes bien = bienesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "El bien con id " + id + " no ha sido encontrado"));           
        var bienDto = Bienflat.builder()
                .id(bien.getId())
                .descripcion(bien.getDescripcion())
                .etiqueta(bien.getEtiqueta())
                .estado(bien.getEstado())
                .depositario(bien.getDepositario().getNombre())
                .ubicacion(bien.getUbicacion().getLugar())
                .build();
        return bienDto;
    }


    /**
     * Crea un nuevo bien.
     * @param request objeto que contiene la informacion del bien a crear
     * @throws BadRequestException si el bien con el id especificado ya ha sido creado
     * @throws ResourceNotFoundException si la ubicacion o el depositario especificados no han sido encontrados
     */
    @Override
    public void saveBien(Bienflat request) {
        if (bienesRepository.existsById(request.getId())) {
            throw new BadRequestException("el bien con id " + request.getId() + " ya ha sido creado");
        }
        Ubicacion lugar = ubicacionRepository.findByLugar(request.getUbicacion())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La ubicacion " + request.getUbicacion() + " no ha sido encontrada"));
        Depositario dep = depositarioRepositary.findByNombre(request.getDepositario())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El depositario " + request.getDepositario() + " no ha sido encontrado"));
        var bien = Bienes.builder()
                .id(request.getId())
                .descripcion(request.getDescripcion())
                .etiqueta(request.getEtiqueta())
                .estado(request.getEstado())
                .depositario(dep)
                .ubicacion(lugar)
                .build();
        bienesRepository.save(bien);
    }

    /**
     * Elimina el bien con el id especificado
     * @param id el id del bien a eliminar
     * @throws ResourceNotFoundException si el bien con el id especificado no ha sido encontrado
     */
    @Override
    public void deleteBien(Integer id) {
        if (!bienesRepository.existsById(id)) {
            throw new ResourceNotFoundException("El bien con id " + id + " no ha sido encontrado");
        }
        bienesRepository.deleteById(id);
    }

    /**
     * Actualiza el bien con el id especificado.
     * @param request objeto que contiene la nueva informacion del bien a actualizar
     * @throws ResourceNotFoundException si el bien con el id especificado no ha sido encontrado
     * o si el depositario o la ubicacion especificados no han sido encontrados
     */
    @Override
    public void updateBien(Bienflat request) {
        Bienes bienes = bienesRepository.findById(request.getId()).orElseThrow(
                () -> new ResourceNotFoundException("el bien con id " + request.getId() + " no ha sido encontrado"));
        Depositario depositario = depositarioRepositary.findByNombre(request.getDepositario())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El depositario " + request.getDepositario() + " no ha sido encontrado"));
        Ubicacion ubicacion = ubicacionRepository.findByLugar(request.getUbicacion())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "La ubicacion " + request.getUbicacion() + " no ha sido encontrada"));
        bienes.setDescripcion(request.getDescripcion());
        bienes.setEtiqueta(request.getEtiqueta());
        bienes.setEstado(request.getEstado());
        bienes.setDepositario(depositario);
        bienes.setUbicacion(ubicacion);
        bienesRepository.save(bienes);
    }

    /**
     * Devuelve una lista de resumenes, donde cada resumen contiene el nombre de una etiqueta y el conteo de bienes que tiene en esa etiqueta.
     * @return una lista de Object[] con los resumenes
     */
    @Override
    public List<Object[]> contarBienesPorEtiqueta() {
        return bienesRepository.contarBienesPorEtiqueta();
    }

    /**
     * Devuelve una lista de resumenes, donde cada resumen contiene el nombre de una ubicacion y el conteo de bienes que tiene en esa ubicacion.
     * @return una lista de Object[] con los resumenes
     */
    @Override
    public List<Object[]> contarBienesPorUbicacion() {
        return bienesRepository.contarBienesPorUbicacion();
    }


/**
 * Devuelve una lista de resumenes, donde cada resumen contiene el nombre de un estado 
 * y el conteo de bienes que tiene en ese estado.
 * @return una lista de Object[] con los resumenes
 */
    @Override
    public List<Object[]> contarBienesPorEstado() {
        return bienesRepository.contarBienesPorEstado();
    }

/**
 * Devuelve una lista de resumenes, donde cada resumen contiene el nombre de un depositario 
 * y el conteo de bienes que tiene ese depositario.
 * @return una lista de Object[] con los resumenes
 */

    @Override
    public List<Object[]> contarBienesPorDepositario() {
        return bienesRepository.contarBienesPorDepositario();
    }

}
