/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.driver.OracleDriver;
import proyecto4gl.ConexionOracle;

/**
 *
 * @author CarlosAjila
 */
public class clsDatos {

    public static boolean iscon = false;
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

    public void registrarDriver() throws SQLException {
        OracleDriver oracleDriver = new oracle.jdbc.driver.OracleDriver();
        DriverManager.registerDriver(oracleDriver);
    }

    public void conectar() throws SQLException {
        //System.out.println(connection);
        if (connection == null || connection.isClosed() == true) {
            String cadenaConexion = "jdbc:oracle:thin:@" + HOST + ":" + PUERTO + ":" + SID;
            registrarDriver();
            connection = DriverManager.getConnection(cadenaConexion, USUARIO, PASS);
        }
    }

    public void cerrar() throws SQLException {
        if (connection != null && connection.isClosed() == false) {
            connection.close();
        }
    }

    //Ejecutar sentencias Select
    public static ResultSet Consulta(String sql) {
        Statement stmt_consul = null;
        ResultSet rs = null;
        try {
            clsDatos conexionOracle = new clsDatos();
            conexionOracle.conectar();
            Connection conn = conexionOracle.getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            stmt.close();
            conexionOracle.cerrar();
        } catch (Exception ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public static void Ejecutar_sql_parametro(String sql) {
        try {
            clsDatos conexionOracle = new clsDatos();
            conexionOracle.conectar();
            Connection conn = conexionOracle.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery(sql);
//            while (rset.next()) {
//                System.out.println(rset.getString(1));   // Print col 1
//            }
            stmt.close();
            conexionOracle.cerrar();
        } catch (Exception ex) {
            Logger.getLogger(ConexionOracle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
