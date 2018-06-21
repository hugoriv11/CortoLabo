/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import interfaces.Metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Pelicula;

/**
 *
 * @author HugoJr. <Hugo Rivera at 00161417@uca.edu.sv>
 */
public class PeliculaDao implements Metodos<Pelicula>{
    private static final String SQL_INSERT = "INSERT INTO movie (idMovie, nombre, director, pais, clasificacion, anio, en_proyeccion) VALUES(?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE movie SET estado = ? WHERE nombre = ?";
    private static final String SQL_DELETE = "DELETE FROM movie WHERE nombre = ?";
    private static final String SQL_READ = "SELECT * FROM movie WHERE nombre = ?";
    private static final String SQL_READALL = "SELECT * FROM movie";
    private static final Conexion con = Conexion.conectar();

    @Override
    public boolean create(Pelicula g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setInt(1, g.getIdMovie());
            ps.setString(2, g.getNombre());
            ps.setString(3, g.getDirector());
            ps.setString(4, g.getPais());
            ps.setString(5, g.getClasificacion());
            ps.setInt(6, g.getAnio());
            ps.setBoolean(7, true);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            con.cerrarConexion();
        }
        return false;    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Pelicula c) {
        PreparedStatement ps;
        try {
            System.out.println(c.getNombre());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setBoolean(1, c.getEn_proyeccion());
            ps.setString(2, c.getNombre());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public Pelicula read(Object key) {
        Pelicula p = null;
        PreparedStatement ps;
        ResultSet rs;

        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                //idMovie, nombre, director, pais, clasificacion, anio, en_proyeccion
                p = new Pelicula(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getBoolean(7));//constructor
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return p;
    }

    @Override
    public ArrayList<Pelicula> readAll() {
        ArrayList<Pelicula> all = new ArrayList();
        Statement s;
        ResultSet rs;

        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);

            while (rs.next()) {
                //idMovie, nombre, director, pais, clasificacion, anio, en_proyeccion
                all.add(new Pelicula(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getBoolean(7)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }

    
    
}
