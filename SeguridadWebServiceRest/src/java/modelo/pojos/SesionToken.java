/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.pojos;

/**
 *
 * @author Panther
 */
public class SesionToken {

    private Integer id;
    private String nombre;
    private String email;
    private String tokenacceso;

    public SesionToken() {
    }

    public SesionToken(Integer id, String nombre, String email, String tokenacceso) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.tokenacceso = tokenacceso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenacceso() {
        return tokenacceso;
    }

    public void setTokenacceso(String tokenacceso) {
        this.tokenacceso = tokenacceso;
    }
}
