package com.sensores.inventario.inventario.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "depositario", schema = "inventariodb")
public class Depositario implements Serializable {
    @Id
    @Column(name = "iddepositario", nullable = false)
    private Integer id;

    @Column(name = "no_economico", nullable = false)
    private Integer no_economico;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "rol")
    private Rol rol;

    @OneToMany(mappedBy = "depositario")
    private List<Bienes> bienes;

}