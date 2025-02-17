package com.sensores.inventario.inventario.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bienes", schema = "inventariodb")
public class Bienes implements Serializable {
    @Id
    @Column(name = "idbienes", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "estado", nullable = false, length = 45)
    private String estado;

    @Column(name = "etiqueta", nullable = false, length = 45)
    private String etiqueta;

    @Column(name = "precio")
    private Float precio;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "depositario")
    @JsonIgnore
    private Depositario depositario;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "ubicacion")
    @JsonIgnore
    private Ubicacion ubicacion;

}