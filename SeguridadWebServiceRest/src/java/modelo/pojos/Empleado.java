/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.pojos;

/**
 *
 * @author Panther
 */
public class Empleado {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String celular;
    private String email;
    private String direccion;
    private String contrasena;

    public Empleado() {
    }

    public Empleado(Integer id, String nombres, String apellidos, String celular, String email, String direccion, String contrasena) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.email = email;
        this.direccion = direccion;
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Empleado{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", celular="
                + celular + ", email=" + email + ", direccion=" + direccion + ", contrasena=" + contrasena + '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
