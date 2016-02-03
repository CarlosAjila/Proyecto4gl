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
public class materia {
    private Integer idMateria;
    private String materia;
    private String estado;

    public materia() {
    }

    public materia(Integer idMateria, String materia, String estado) {
        this.idMateria = idMateria;
        this.materia = materia;
        this.estado = estado;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

   
  

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
