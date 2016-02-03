/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author CarlosAjila
 */
public class matricula {

    private int idMatricula;
    private int numFolio;
    private int numeroMatricula;
    private String fechaMatricula;
    private int idInstitucion;
    private String cedula;
    private String idEspecialidad;
    private String idCurso;
    private String paralelo;
    private int idPeriodo;
    private String estado;

    public matricula() {
    }

    public matricula(int idMatricula, int numFolio, int numeroMatricula, String fechaMatricula, int idInstitucion, String cedula, String idEspecialidad, String idCurso, String paralelo, int idPeriodo, String estado) {
        this.idMatricula = idMatricula;
        this.numFolio = numFolio;
        this.numeroMatricula = numeroMatricula;
        this.fechaMatricula = fechaMatricula;
        this.idInstitucion = idInstitucion;
        this.cedula = cedula;
        this.idEspecialidad = idEspecialidad;
        this.idCurso = idCurso;
        this.paralelo = paralelo;
        this.idPeriodo = idPeriodo;
        this.estado = estado;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getNumFolio() {
        return numFolio;
    }

    public void setNumFolio(int numFolio) {
        this.numFolio = numFolio;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(String fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(String idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
