package com.youhungry.endpoint.controller;

import com.youhungry.endpoint.model.Pedido;
import com.youhungry.endpoint.repository.PedidoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("API DE PEDIDOS")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    //select * from
    @GetMapping
    public List<Pedido> listarPedidos(){
        return pedidoRepository.findAll();
    }

    //select com where no ID
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return pedidoRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    //insert
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido adicionar(@RequestBody Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    //update
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Pedido pedido) {
        return pedidoRepository.findById(id)
                .map(record -> {
                    if(pedido.getNomePedido() != null){
                        record.setNomePedido(pedido.getNomePedido());}

                    if(pedido.getDescricaoPedido()!= null){
                        record.setDescricaoPedido(pedido.getDescricaoPedido());}

                    if(pedido.getPratos() != null){
                        record.setPratos(pedido.getPratos());}

                    if(pedido.getUsuario() != null){
                        record.setUsuario(pedido.getUsuario());}

                    if(pedido.getRestaurante() != null){
                        record.setRestaurante(pedido.getRestaurante());}

                    Pedido updated = pedidoRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    //delete
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return pedidoRepository.findById(id)
                .map(record -> {
                    pedidoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
