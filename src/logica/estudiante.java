/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

/**
 *
 * @author Toshiba
 */
public class estudiante {
    private String cedula;
    private String nombres;
    private String apellidos;
    private String sexo;
    private String edad;
    private String direccion;
    private Integer telefono;
    private Integer id_parroquia;
    private String estado;
    private Integer id_institucion;

    public estudiante() {
    }

    public estudiante(String cedula, String nombres, String apellidos, String sexo, String edad, String direccion, Integer telefono, Integer id_parroquia, String estado, Integer id_institucion) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.id_parroquia = id_parroquia;
        this.estado = estado;
        this.id_institucion = id_institucion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getId_parroquia() {
        return id_parroquia;
    }

    public void setId_parroquia(Integer id_parroquia) {
        this.id_parroquia = id_parroquia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getId_institucion() {
        return id_institucion;
    }

    public void setId_institucion(Integer id_institucion) {
        this.id_institucion = id_institucion;
    }
}
