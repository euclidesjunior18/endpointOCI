package com.youhungry.endpoint.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Restaurante {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idrestaurante;

    @Column(length = 60, nullable = false)
    private String nome;
    @Column(length = 30, nullable = false)
    private String password;
    @Column(length = 80)
    private String especialidade;
    @Column(length = 80)
    private String endereco;
    @Column(length = 40)
    private String cidade;
    @Column(length = 20)
    private String cep;
    @Column(length = 40, nullable = false)
    private String cnpj;

}
