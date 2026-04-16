/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACardenasPrueba.Prueba.Service;

import ACardenasPrueba.Prueba.ML.Direcciones;
import ACardenasPrueba.Prueba.ML.Result;
import ACardenasPrueba.Prueba.ML.Usuario;
import ACardenasPrueba.Prueba.PruebaApplication;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author digis
 */
@Service
public class UsuarioService {

//    ApplicationContext context = SpringApplication.run(PruebaApplication.class, args);
//    
//    @Autowired
//     ObjetosCargados objetosCargados = context.getBean(ObjetosCargados.class);
    private final List<Usuario> lista = new ArrayList<>();

    public List<Usuario> getAllUsuarios(String sortedBy) {

        agregarUsuarios();
        if (sortedBy == null || sortedBy.isEmpty()) {
            return lista;
        }

        return lista.stream()
                .sorted((u1, u2) -> {
                    switch (sortedBy.toLowerCase()) {

                        case "email":
                            return u1.getEmail().compareTo(u2.getEmail());
                        case "name":
                            return u1.getName().compareTo(u2.getName());
                        case "phone":
                            return u1.getPhone().compareTo(u2.getPhone());
                        case "tax_id":
                            return u1.getTax_id().compareTo(u2.getTax_id());

                        default:
                            return 0;
                    }
                })
                .collect(Collectors.toList());
    }

    public List<Usuario> filtro(String filter) {

        String letraPrueba = "H";
        agregarUsuarios();
        String[] partes = filter.split(" ");
        String atributo = partes[0];
        String orden = partes[1];
        String valor = partes[2];
        List<Usuario> listaFiltrada;
        switch (atributo) {
            case "email":
                listaFiltrada = ordenConValor(orden, valor);
                break;
            case "name":
                listaFiltrada = ordenConValor(orden, valor);
                break;
            case "phone":
                listaFiltrada = ordenConValor(orden, valor);
                break;
            case "tax_id":
                listaFiltrada = ordenConValor(orden, valor);
                break;
            default:
                throw new AssertionError();
        }
        return listaFiltrada;

    }
    
    public Result add(Usuario usuario){
        Result result = new Result();
        try {
            
            Usuario nuevoUsuario = new Usuario(usuario.getId(), usuario.getEmail(), usuario.getName(), usuario.getEmail(), usuario.getEmail(), usuario.getEmail(), usuario.getCreated_ad());
            
            lista.add(nuevoUsuario);
            result.correct = true;
            
            
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        
        
        return result;
        
    }

    private void agregarUsuarios() {

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

    private List<Usuario> ordenConValor(String orden, String valor) {

        if (orden.equals("sw")) {
            return lista.stream()
                    .filter(s -> s.getName().startsWith(valor))
                    .collect(Collectors.toList());
        }
        if (orden.equals("co")) {
            return lista.stream()
                    .filter(s -> s.getName().contains(valor))
                    .collect(Collectors.toList());
        }
        if (orden.equals("eq")) {
            return lista.stream()
                    .filter(s -> s.getName().equals(valor))
                    .collect(Collectors.toList());
        }
        if (orden.equals("ew")) {
            return lista.stream()
                    .filter(s -> s.getName().endsWith(valor))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
