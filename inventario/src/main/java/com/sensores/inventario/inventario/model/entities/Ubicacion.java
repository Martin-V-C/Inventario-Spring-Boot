package com.sensores.inventario.inventario.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ubicaciones", schema = "inventariodb")
public class Ubicacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idubicacion", nullable = false)
    private Integer id;

    @Column(name = "lugar", length = 45)
    private String lugar;

    @OneToMany(mappedBy = "ubicacion")
    private List<Bienes> bienes;

}