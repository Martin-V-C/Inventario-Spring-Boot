package com.sensores.inventario.inventario.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "depositario", schema = "inventariodb")
public class Depositario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddepositario", nullable = false)
    private Integer id;

    @Column(name = "no_economico", nullable = false)
    private Integer no_economico;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "username")
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "rol")
    private Rol rol;

    @OneToMany(mappedBy = "depositario")
    private List<Bienes> bienes;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.getTipo()));
    }

    @Override
    public String getUsername() {
       return username;
    }

    @Override
    public String getPassword() {
        return password;
    }


}