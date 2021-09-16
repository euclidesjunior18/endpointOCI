package com.youhungry.endpoint.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idusuario;

    @Column(length = 60, nullable = false)
    private String email;
    @Column(length = 30, nullable = false)
    private String password;
    @Column(length = 60, nullable = false)
    private String nome;
    @Column
    private String endereco;
    @Column
    private String cidade;
    @Column
    private String cep;

}
