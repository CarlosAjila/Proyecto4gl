/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logica;

import conexion.clsDatos;
import java.sql.ResultSet;
import java.util.Vector;
/**
 *
 * @author Toshiba
 */
public class listaEstudiante {
    
    public void guardar(estudiante e) {
        try {
            //conexion a la base de datos
            String sql = ("INSERT INTO ESTUDIANTE(CEDULA,NOMBRES,APELLIDOS,SEXO,EDAD,DIRECCION,TELEFONO,"
                    + "ID_PARROQUIA,ESTADO,ID_INSTITUCION) values('"+e.getCedula()+"','"+e.getNombres()+"','"
                    +e.getApellidos()+"','"+e.getSexo()+"','"+e.getEdad()+"','"+e.getDireccion()+"','"
                    +e.getTelefono()+"','"+e.getId_parroquia()+"','"+e.getEstado()+"','"+e.getId_institucion()+"')");
            clsDatos.Ejecutar_sql_parametro(sql);//inserta en la base de datos
            System.out.print("Registro de estudiante exitoso.");
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    
}
