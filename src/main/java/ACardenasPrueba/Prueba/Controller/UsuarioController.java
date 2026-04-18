/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACardenasPrueba.Prueba.Controller;

import ACardenasPrueba.Prueba.ML.Result;
import ACardenasPrueba.Prueba.ML.Usuario;
import ACardenasPrueba.Prueba.Service.UsuarioService;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digis
 */
@RestController
@RequestMapping("/prueba")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity getUsuarios(@RequestParam(required = false) String sortedBy) {
        Result result = new Result<>();
        try {
            List<Usuario> userList = usuarioService.getAllUsuarios(sortedBy);

            return ResponseEntity.ok(userList);
        } catch (Exception e) {
        }
        return ResponseEntity.status(500).body("Algo salio mal...");
    }

    @GetMapping("/filtro")
    public ResponseEntity filtrarUsuarios(@RequestParam String filter) {
        Result result = new Result();
        try {
            List<Usuario> userlist = usuarioService.filtro(filter);

            return ResponseEntity.ok(userlist);
        } catch (Exception e) {

        }
        return ResponseEntity.status(500).body("Algo malio sal...");
    }

    @PostMapping
    public ResponseEntity addUsuario(@RequestBody Usuario usuario) {
        Result result = new Result();
        try {
            result = usuarioService.add(usuario);
            if (result.correct) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(400).body(result);
            }
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return ResponseEntity.status(500).body("algo malio sal");
    }

    @PatchMapping
    public ResponseEntity updateUsuario(@RequestBody Usuario usuario, @RequestParam int identificador) {
        Result result = new Result();
        try {

            result = usuarioService.update(identificador, usuario);

            if (result.correct) {
                return ResponseEntity.ok().body(result);
            } else {
                return ResponseEntity.status(400).body(result);
            }

        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return ResponseEntity.status(500).body(result);
    }

    @DeleteMapping
    public ResponseEntity deleteUsuario(@RequestParam int identificador) {
        Result result = new Result();
        try {

            result = usuarioService.delete(identificador);

            if (result.correct) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.status(400).body(result);
            }

        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        return ResponseEntity.status(500).body(result);
    }

}
