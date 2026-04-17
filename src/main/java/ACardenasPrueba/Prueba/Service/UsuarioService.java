/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACardenasPrueba.Prueba.Service;

import ACardenasPrueba.Prueba.Component.ObjetosCreados;
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
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 *
 * @author digis
 */
@Service
public class UsuarioService {

    @Autowired
    ObjetosCreados objetosCreados;

    private List<Usuario> lista;

    public List<Usuario> getAllUsuarios(String sortedBy) {

        lista = obtenerLista();

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

    public Result add(Usuario usuario) {
        Result result = new Result();
        try {
            
            Usuario nuevoUsuario = new Usuario(usuario.getId(), usuario.getEmail(), usuario.getName(), usuario.getEmail(), usuario.getEmail(), usuario.getEmail(), usuario.getCreated_ad());
            lista = obtenerLista();
            lista.add(nuevoUsuario);
            result.correct = true;

        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }

        return result;
    }
    
    public Result getById(int identificador){
        Result result = new Result();
        
        lista = obtenerLista();
        
        result.object = lista.stream()
                .filter(u ->u.getId() == identificador)
                .collect(Collectors.toList());
        
        return result;
    }
    
    public Result update(int identificador, Usuario usuario){
        Result result = new Result();
        lista = obtenerLista();
        try {
            result = getById(identificador);
            Usuario usuarioActual = (Usuario) result.object;
            
            if (usuario.getName() != null) {
                usuarioActual.setName(usuario.getName());
            }
            if (usuario.getEmail() != null) {
                usuarioActual.setEmail(usuario.getEmail());
            }
            if (usuario.getPassword() != null) {
                usuarioActual.setPassword(usuario.getPassword());
            }
            if (usuario.getPhone() != null) {
                usuarioActual.setPhone(usuario.getPhone());
            }
            
            result.object = usuarioActual;
            
            
        } catch (Exception e) {
            result.correct = false;
            result.errorMessage = e.getLocalizedMessage();
            result.ex = e;
        }
        
        return result;
    }
    

    private List<Usuario> obtenerLista() {
        return objetosCreados.getLista();
    }

    private List<Usuario> ordenConValor(String orden, String valor) {
        lista = obtenerLista();

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
