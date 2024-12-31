package com.literalura.LiterAlura.repositorio;

import com.literalura.LiterAlura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    Optional<Autor> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Autor a WHERE CAST(a.fechaNacimiento AS integer) <= :fecha AND CAST(a.fechaDefuncion AS integer) >= :fecha")
    List<Autor> findByYear(@Param("fecha") int fecha);
}
