/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACardenasPrueba.Prueba.Component;

import ACardenasPrueba.Prueba.ML.Usuario;
import jakarta.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author digis
 */
@org.springframework.stereotype.Component
public class ObjetosCreados {

    private final List<Usuario> lista = new ArrayList<>();

    @PostConstruct
    public void init() {
        System.out.println("Inicializando los objetos");

        Random rand = new Random();

        ZoneId horaMadagascar = ZoneId.of("Indian/Antananarivo");
        ZonedDateTime madagascarTime = ZonedDateTime.now(horaMadagascar);

        Date horaDeCreacion = Date.from(madagascarTime.toInstant());
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Usuario usuario1 = new Usuario(rand.nextInt(10) + 1, "user1@email.com", "Alfredo", "7441580909", "asd123", "CARA030303HGRRZRA3", horaDeCreacion);

        Usuario usuario2 = new Usuario(rand.nextInt(10) + 1, "user2@email.com", "Fernando", "7441586799", "asd123", "CARA030303HGRRZRA3", horaDeCreacion);

        Usuario usuario3 = new Usuario(rand.nextInt(10) + 1, "user3@email.com", "Carlos", "7440120909", "asd123", "CARA030303HGRRZRA3", horaDeCreacion);

        lista.add(usuario1);

        lista.add(usuario2);

        lista.add(usuario3);
    }

    public List<Usuario> getLista() {
        return lista;
    }
}
