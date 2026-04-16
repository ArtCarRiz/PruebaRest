/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ACardenasPrueba.Prueba.ML;

import java.util.Date;
import java.util.List;

/**
 *
 * @author digis
 */
public class Usuario {

    private int id;
    private String email;
    private String name;
    private String phone;
    private String password;
    private String tax_id;
    private Date created_ad;
    
    public List<Direcciones> direccion;

    public Usuario() {
    }

    public Usuario(int id, String email, String name, String phone, String password, String tax_id, Date created_ad) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.tax_id = tax_id;
        this.created_ad = created_ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTax_id() {
        return tax_id;
    }

    public void setTax_id(String tax_id) {
        this.tax_id = tax_id;
    }

    public Date getCreated_ad() {
        return created_ad;
    }

    public void setCreated_ad(Date created_ad) {
        this.created_ad = created_ad;
    }

    @Override
    public String toString() {
        return "Usuario{id=" + id + ", email='" + email + "'}";
    }

}
