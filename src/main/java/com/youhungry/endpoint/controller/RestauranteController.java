package com.youhungry.endpoint.controller;

import com.youhungry.endpoint.model.Restaurante;
import com.youhungry.endpoint.repository.RestauranteRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api("API DE RESTAURANTES")
@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    //select * from
    @GetMapping
    public List<Restaurante> listarRestaurantes(){
        return restauranteRepository.findAll();
    }

    //select com where no ID
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return restauranteRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    //insert
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }

    //update
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Restaurante restaurante) {
        return restauranteRepository.findById(id)
                .map(record -> {
                    if(restaurante.getNome() != null){
                        record.setNome(restaurante.getNome());}

                    if(restaurante.getCnpj() != null){
                        record.setCnpj(restaurante.getCnpj());}

                    if(restaurante.getPassword()!= null){
                        record.setPassword(restaurante.getPassword());}

                    if(restaurante.getEspecialidade() != null){
                        record.setEspecialidade(restaurante.getEspecialidade());}

                    if(restaurante.getCidade() != null){
                        record.setCidade(restaurante.getCidade());}

                    if(restaurante.getEndereco() != null){
                        record.setEndereco(restaurante.getEndereco());}

                    if(restaurante.getCep() != null){
                        record.setCep(restaurante.getCep());}
                    Restaurante updated = restauranteRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    //delete
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return restauranteRepository.findById(id)
                .map(record -> {
                    restauranteRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
