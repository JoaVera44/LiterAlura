package com.literalura.LiterAlura.service;

import com.literalura.LiterAlura.models.Autor;
import com.literalura.LiterAlura.models.DatosLibro;
import com.literalura.LiterAlura.models.Libro;
import com.literalura.LiterAlura.repositorio.AutorRepository;
import com.literalura.LiterAlura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    LibroRepository libroRepository;

    @Autowired
    AutorRepository autorRepository;

    Libro libro;

    public void guardarLibro(Optional<DatosLibro> datosLibro){

        if (datosLibro.isPresent()){
            libro = new Libro(datosLibro.get());

            List<Autor> autores= verificarAutor(libro);
            libro.setAutor(autores);

            boolean libroRepetido = verificarLibro(libro);
            if (!libroRepetido) {
                libroRepository.save(libro);
            }
            else{
                System.out.println("libro ya cargado!");
            }
        }

    }



    public List <Autor> verificarAutor(Libro libro) {
        List<Autor> autoresPersistidos = new ArrayList<>();

        List<String> nombres = libro.getAutor().stream()
                .map(Autor::getNombre)
                .toList();

        //Aca ya llega null

       nombres.forEach(n-> {
            Optional<Autor> autorExistente = autorRepository.findByNombre(n);
            if (autorExistente.isEmpty()){

                List<Autor> datosAutores = libro.getAutor().stream()
                        .map(a -> new Autor(a.getId(),a.getNombre(), a.getFechaNacimiento(), a.getFechaDefuncion(), a.getLibros())).toList();

                datosAutores.forEach(autor -> autorRepository.save(autor));
                autoresPersistidos.addAll(datosAutores);

            }else{
                autoresPersistidos.add(autorExistente.get());
            }

        });

        return autoresPersistidos;

    }

    private boolean verificarLibro(Libro libro) {
        Optional<Libro> libroExistente = libroRepository.findByNombre(libro.getTitulo());
        if (libroExistente.isPresent()){
            return true;
        }
        else return false;
    }




}

