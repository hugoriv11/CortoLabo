/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author HugoJr. <Hugo Rivera at 00161417@uca.edu.sv>
 */
public class Pelicula {
    private int idMovie;
    private String nombre;
    private String director;
    private String pais;
    private String clasificacion;
    private int anio;
    private boolean en_proyeccion;
    
    public Pelicula(){}

    public Pelicula(int idMovie, String nombre, String director, String pais, String clasificacion, int anio, boolean en_proyeccion) {
        this.idMovie = idMovie;
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.anio = anio;
        this.en_proyeccion = en_proyeccion;
    }

    public Pelicula(String nombre, String director, String pais, String clasificacion, int anio, boolean en_proyeccion) {
        this.nombre = nombre;
        this.director = director;
        this.pais = pais;
        this.clasificacion = clasificacion;
        this.anio = anio;
        this.en_proyeccion = en_proyeccion;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public boolean getEn_proyeccion() {
        return en_proyeccion;
    }

    public void setEn_proyeccion(boolean en_proyeccion) {
        this.en_proyeccion = en_proyeccion;
    }
    
    
}

