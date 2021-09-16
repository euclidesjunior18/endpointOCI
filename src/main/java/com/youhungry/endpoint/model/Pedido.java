package com.youhungry.endpoint.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long idpedido;

    private String nomePedido;
    private String descricaoPedido;
    @ManyToMany
    @JoinColumn(name = "idprato")
    private List<Prato> pratos;
    @OneToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "idrestaurante")
    private Restaurante restaurante;
}
