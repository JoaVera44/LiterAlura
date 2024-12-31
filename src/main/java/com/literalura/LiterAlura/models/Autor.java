package com.literalura.LiterAlura.models;

import ch.qos.logback.core.util.StringUtil;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity

/*
Clase Libro: Representa cada libro, con una lista de autores relacionados.

Clase Autor: Representa a los autores, con una lista de libros relacionados.

Tabla intermedia libro_autor: Relaciona libros y autores con sus respectivas claves primarias.
 */

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaDefuncion;

    //Un autor puede tener varios libros.

    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();


    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        if (StringUtil.isNullOrEmpty(String.valueOf(datosAutor.fechaDefuncion()))){
            this.fechaDefuncion = 0;
        }
        else{
            this.fechaDefuncion = datosAutor.fechaDefuncion();
        }
    }


    public Autor(Long id, String nombre, Integer fechaNacimiento, Integer fechaDefuncion, List<Libro> libros) {
    this.id = id;
    this.nombre = nombre;
    this.fechaNacimiento = fechaNacimiento;
        if (StringUtil.isNullOrEmpty(String.valueOf(fechaDefuncion))){
            this.fechaDefuncion = 0;
        }
        else{
            this.fechaDefuncion = fechaDefuncion;
        }
    this.libros = libros;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(Integer fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }


    @Override
    public String toString() {
        return nombre;
    }

    public String mostrarDatos(){
        return "\n *************** AUTOR ************** \n" +
                "Nombre= " + nombre + '\n' +
                "fecha de Nacimiento= " + fechaNacimiento + '\n' +
                "fecha de Defuncion= " + fechaDefuncion + '\n' +
                "******************************************* \n";
    }


}
