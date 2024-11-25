package com.sensores.inventario.inventario.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles", schema = "inventariodb")
public class Rol implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol", nullable = false)
    private Integer id;

    @ColumnDefault("'User'")
    @Column(name = "tipo", length = 45)
    private String tipo;

    @OneToMany(mappedBy = "rol")
    private List<Depositario> depositarios;

}