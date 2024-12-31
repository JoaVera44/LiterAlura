package com.literalura.LiterAlura.principal;

import com.literalura.LiterAlura.models.Autor;
import com.literalura.LiterAlura.models.Datos;
import com.literalura.LiterAlura.models.DatosLibro;

import com.literalura.LiterAlura.models.Libro;
import com.literalura.LiterAlura.repositorio.AutorRepository;
import com.literalura.LiterAlura.repositorio.LibroRepository;
import com.literalura.LiterAlura.service.ConsumoApi;
import com.literalura.LiterAlura.service.ConvierteDatos;
import com.literalura.LiterAlura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private Integer opcion = -1;

    @Autowired
    private LibroService libroService;

    LibroRepository libroRepo;
    AutorRepository autorRepo;




    public Principal(LibroRepository libroRepository, AutorRepository autorRepository, LibroService libroService) {
    this.libroRepo = libroRepository;
    this.autorRepo = autorRepository;
    this.libroService = libroService;
    }

    public void muestraMenu(){

    while (opcion != 0){
        System.out.print("""
                ***************************************************
                Bienvenido a LiterAlura, elija una de las opciones
                1-Buscar libro por titulo
                2-listar libros registrados
                3-listar autores registrados
                4-listar autores vivos en un determinado año
                5-listar libros por idioma
                0-salir
                """);
        opcion = teclado.nextInt();
        teclado.nextLine();

        switch (opcion){
            case 1:
                buscarLibroPorTitulo();
                break;
            case 2:
                listarLibrosRegistrados();
                break;
            case 3:
                listarAutoresRegistrados();
                break;
            case 4:
                listarAutoresPorFecha();
                break;
            case 5:
                listarLibrosPorIdioma();
                break;
            case 0:
                System.out.println("Cerrando el programa");
                break;

            default:
                break;
        }
    }

    }




    private void buscarLibroPorTitulo() {
    //Busca el libro en la api y lo registra en la base de datos
        System.out.println("Ingrese el nombre del libro: ");
        var nombreLibro = teclado.nextLine();

        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datosBusqueda);

        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l ->l.titulo().toUpperCase().contains(l.titulo().toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()){
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado.get());
            libroService.guardarLibro(libroBuscado);

        }else{
            System.out.println("libro no encontrado :c");
        }
        


    }

    private void listarLibrosRegistrados() {
        List<Libro> librosRegistrados = libroRepo.findAll();

        librosRegistrados.forEach(l -> System.out.println(l.toString()));

    }

    private void listarAutoresRegistrados() {
        List<Autor> autoresRegistrados = autorRepo.findAll();

        autoresRegistrados.forEach(a -> System.out.println(a.mostrarDatos()));
    }

    private void listarAutoresPorFecha() {
        System.out.println("Ingrese el año que quiere revisar: ");
        var fecha = teclado.nextInt();
        teclado.nextLine();
        System.out.println(fecha);
        List<Autor> autoresPorFecha = autorRepo.findByYear(fecha);

        autoresPorFecha.forEach(a-> System.out.println(a.mostrarDatos()));

    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma que desea buscar
                1-Ingles
                2-Español
                3-Portugues
                4-Frances
                5-Finlandes
                """);
        var seleccionIdioma = teclado.nextInt();
        teclado.nextLine();

        if(seleccionIdioma >= 6){
            System.out.println("numero incorrecto");
        }

        List<Libro> librosPorIdioma;

        switch (seleccionIdioma){
            case 1:
                librosPorIdioma = libroRepo.findByIdioma("en");
                librosPorIdioma.forEach(l-> System.out.println(l.toString()));
                break;
            case 2:
                librosPorIdioma = libroRepo.findByIdioma("es");
                librosPorIdioma.forEach(l-> System.out.println(l.toString()));
                break;
            case 3:
                librosPorIdioma = libroRepo.findByIdioma("pr");
                librosPorIdioma.forEach(l-> System.out.println(l.toString()));
                break;
            case 4:
                librosPorIdioma = libroRepo.findByIdioma("fr");
                librosPorIdioma.forEach(l-> System.out.println(l.toString()));
                break;
            case 5:
                librosPorIdioma = libroRepo.findByIdioma("fi");
                librosPorIdioma.forEach(l-> System.out.println(l.toString()));
                break;
            default:
                break;
        }


    }



}
