package com.youhungry.endpoint.controller;

import com.youhungry.endpoint.model.Usuario;
import com.youhungry.endpoint.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("API DE USUARIOS")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //select * from
    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    //select com where no ID
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return usuarioRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    //insert
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario adicionar(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //update
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Usuario usuario) {
        return usuarioRepository.findById(id)
                .map(record -> {
                    if(usuario.getNome() != null){
                        record.setNome(usuario.getNome());}

                    if(usuario.getPassword()!= null){
                        record.setPassword(usuario.getPassword());}

                    if(usuario.getEmail() != null){
                        record.setEmail(usuario.getEmail());}

                    if(usuario.getCidade() != null){
                        record.setCidade(usuario.getCidade());}

                    if(usuario.getEndereco() != null){
                        record.setEndereco(usuario.getEndereco());}

                    if(usuario.getCep() != null){
                        record.setCep(usuario.getCep());}
                    Usuario updated = usuarioRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    //delete
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return usuarioRepository.findById(id)
                .map(record -> {
                    usuarioRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
