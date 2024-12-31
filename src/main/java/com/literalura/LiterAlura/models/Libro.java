package com.literalura.LiterAlura.models;

import jakarta.persistence.*;

/*
Clase Libro: Representa cada libro, con una lista de autores relacionados.

Clase Autor: Representa a los autores, con una lista de libros relacionados.

Tabla intermedia libro_autor: Relaciona libros y autores con sus respectivas claves primarias.
 */


import java.util.List;
@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idiomas;
    private Double numeroDescargas;

    //Un libro puede tener diversos autores participando en el.

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores;

    public Libro() {
    }

    public Libro(DatosLibro datosLibro) {
        setTitulo(datosLibro.titulo());
        this.idiomas = datosLibro.idiomas().get(0);
        setNumeroDescargas(datosLibro.numeroDescargas());
        this.autores = datosLibro.autor().stream().map(a-> new Autor(a)).toList();

    }

    public List<Autor> getAutor() {
        return autores;
    }

    public void setAutor(List<Autor> autor) {
        this.autores = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        return "\n ********** LIBRO ************ \n" +
                "titulo= " + titulo + '\n' +
                "autor= " + autores + "\n" +
                "idiomas= " + idiomas + "\n" +
                "numero de Descargas= " + numeroDescargas + "\n" +
                "*********************************** \n";
    }
}
