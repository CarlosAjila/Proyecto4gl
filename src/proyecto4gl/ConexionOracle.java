/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto4gl;

/**
 *
 * @author CarlosAjila
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.OracleDriver;

public class ConexionOracle {

    //Usuario de la base de datos
    private final String USUARIO = "DBA_DINEPP";
    //Contraseña del usuario de la base de datos
    private final String PASS = "proyecto";
    //SID de la base de datos, este lo registramos en la instalacion
    private final String SID = "orclA";
    //Host donde se encuentra la base de datos, para nuesto caso como es local
    //se indica que esta en localhost
    private final String HOST = "192.168.3.127";
    //El puerto 1521 es el estandar para este tipo de instalaciones a menos que
    //se indicque lo contrario
    private final int PUERTO = 1539;
    //Objeto donde se almacenara nuestra conexion
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    /*
     * Instanciamos un objeto de tipo OracleDriver para regitrarlo y posterior uso
     * este objeto lo provee el driver que agregamos al principio
     */
    public void registrarDriver() throws SQLException {
        OracleDriver oracleDriver = new oracle.jdbc.driver.OracleDriver();
        DriverManager.registerDriver(oracleDriver);
    }

    /*
     * Procedemos a realizar nuestra conexion a la base datos, para esto nos
     * aseguramos que el objeto este null o que este cerrada la conexion.
     * 
     * cadanaConexion: es un string que se contruye a partir de los atributos
     * definidos.
     * 
     * Finalmente obtenemos la conexion.  El metodo "getConnection"
     * lanza una excepcion la cual propagamos "throws SQLException".
     */
    public void conectar() throws SQLException {
        //System.out.println(connection);
        if (connection == null || connection.isClosed() == true) {
            String cadenaConexion = "jdbc:oracle:thin:@" + HOST + ":" + PUERTO + ":" + SID;
            registrarDriver();
            connection = DriverManager.getConnection(cadenaConexion, USUARIO, PASS);
        }
    }

    /*
     * Con este metodo cerramos la conexion una vez hayamos terminado de usar la
     * base de datos
     */
    public void cerrar() throws SQLException {
        if (connection != null && connection.isClosed() == false) {
            connection.close();
        }
    }

    /*
     * Main para comprobar que funciona, aqui hacemos un select a una tabla del
     * sistema para obtener la version.
     */
    public static void main(String args[]) {

        ConexionOracle conexionOracle = new ConexionOracle();
        try {
            conexionOracle.conectar(); 
            Connection conn = conexionOracle.getConnection();
            // driver@machineName:port:SID           ,  userid,  password
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("UPDATE MATERIAS SET MATERIA = 'vvvvvv' WHERE ID_MATERIAS = 42");
            while (rset.next()) {
                System.out.println(rset.getString(1));   // Print col 1
            }
            stmt.close();
            conexionOracle.cerrar();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

