package com.helpe.YoHelper.entity;

import com.helpe.YoHelper.util.Rol;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Slf4j
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "el campo name no debe estar vacio")
    private String name;
    @NotEmpty(message = "El campo last Name no debe estar vacio")
    private String lastName;
    @NotEmpty(message = "el campo de email no debe estar vacio")
    @Column(unique = true)
    private String email;
    @NotEmpty
    @Size(min = 8, max=15, message = "La contraseña debe tener entre 8 y 15 caracteres")
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;
    private boolean deleted;
}
