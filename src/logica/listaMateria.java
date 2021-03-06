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
 * @author CarlosAjila
 */
public class listaMateria {

    Vector<materia> lista;
    public static int posicion_usuario = 0;

    public listaMateria() {
        lista = new Vector<materia>(1);
    }

    public void insert(materia p) {
        lista.addElement(p);
    }

    //busca un usuario por codigo
    public String buscar(int cod) {
        for (int i = 0; i < Materia(); i++) {
            if (cod == get(i).getIdMateria()) {
                return get(i).getMateria();
            }
        }
        return null;
    }

    //retorna el n�mero de objetos almacenados en el vector
    public int Materia() {
        return lista.size();
    }

    //retorna un objeto persona dada la posici�n en el vector
    public materia get(int pos) {
        return lista.get(pos);
    }

    public void cargar_lista(ResultSet rs) {
        try {
            this.lista.removeAllElements();//limpia la lista
            while (rs.next()) {
                materia m = new materia();
                m.setMateria(rs.getString("MATERIA"));
                m.setEstado(rs.getString("ESTADO"));
                insert(m);
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    //Los objetos del vector los guarda en el archivo

    public void guardar(materia m) {
        try {
            //conexion a la base de datos
            String sql = ("INSERT INTO MATERIAS(MATERIA,ESTADO) values('"+m.getMateria()+"','"+m.getEstado()+"')");
            clsDatos.Ejecutar_sql_parametro(sql);//inserta en la base de datos
            System.out.print("inserto usuario correctamente");
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    public void modificar(materia m) {
        try {
            //conexion a la base de datos
            String sql = ("UPDATE MATERIAS SET MATERIA = '"+m.getMateria()+"' WHERE ID_MATERIAS = "+m.getIdMateria()+";");
            System.out.println(sql);
            clsDatos.Ejecutar_sql_parametro(sql);//inserta en la base de datos
            System.out.print("inserto usuario correctamente");
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    
    
     public static void main(String args[]) {
         materia materia = new materia(42, "LEGUAJE 4GL", "A");    
         listaMateria listaMateria = new listaMateria();
         //listaMateria.modificar(materia);
         listaMateria.guardar(materia);
    }
}
