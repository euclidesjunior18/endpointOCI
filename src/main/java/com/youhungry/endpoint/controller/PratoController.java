package com.youhungry.endpoint.controller;

import com.youhungry.endpoint.model.Prato;
import com.youhungry.endpoint.model.Usuario;
import com.youhungry.endpoint.repository.PratoRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("API DE PRATOS")
@RestController
@RequestMapping("/pratos")
public class PratoController {
    @Autowired
    private PratoRepository pratoRepository;

    //select * from
    @GetMapping
    public List<Prato> listarPratos(){
        return pratoRepository.findAll();
    }

    //select com where no ID
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return pratoRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    //insert
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prato adicionar(@RequestBody Prato prato){
        return pratoRepository.save(prato);
    }

    //update
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Prato prato) {
        return pratoRepository.findById(id)
                .map(record -> {
                    if(prato.getNome() != null){
                        record.setNome(prato.getNome());}

                    if(prato.getDescricao()!= null){
                        record.setDescricao(prato.getDescricao());}

                    if(prato.getTempoParaPreparo() != null){
                        record.setTempoParaPreparo(prato.getTempoParaPreparo());}

                    if(prato.getAcompanhamento() != null){
                        record.setAcompanhamento(prato.getAcompanhamento());}

                    if(prato.getPreco() < 0){
                        record.setPreco(prato.getPreco());}

                    if(prato.getUrl() != null){
                        record.setUrl(prato.getUrl());}

                    Prato updated = pratoRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    //delete
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return pratoRepository.findById(id)
                .map(record -> {
                    pratoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
