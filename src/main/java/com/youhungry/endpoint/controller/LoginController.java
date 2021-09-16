package com.youhungry.endpoint.controller;


import com.youhungry.endpoint.model.Usuario;
import com.youhungry.endpoint.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("API DE LOGIN")
@RestController
@RequestMapping("/login")
public class LoginController {

    private static List<Usuario> users = new ArrayList<>();
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity update(@RequestBody Usuario usuario){
        users = usuarioRepository.findAll();
        for(Usuario u : users) {
            if (u.getEmail().equals(usuario.getEmail()) && u.getPassword().equals(usuario.getPassword())){
                return ResponseEntity.ok().body(u);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
