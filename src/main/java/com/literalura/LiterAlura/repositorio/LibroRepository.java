package com.literalura.LiterAlura.repositorio;

import com.literalura.LiterAlura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    Optional<Libro> findByNombre(@Param("titulo") String titulo);

    @Query("SELECT l FROM Libro l WHERE l.idiomas LIKE %:idiomas%")
    List<Libro> findByIdioma(@Param("idiomas") String idiomas);

}
