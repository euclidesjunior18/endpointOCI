package com.youhungry.endpoint.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Prato {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idprato;

    @Column(length = 40, nullable = false)
    private String nome;
    @Column(length = 90, nullable = false)
    private String descricao;
    @OneToOne
    @JoinColumn(name = "idrestaurante",nullable=false)
    private Restaurante restaurante;
    @Column @Temporal(TemporalType.TIME) // hh:mm:ss
    private Date tempoParaPreparo;
    @Column
    private String acompanhamento;
    @Column
    private double preco;
    @Column(length = 200)
    private String url;

}
