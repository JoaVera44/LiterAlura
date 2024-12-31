# Gestión de Biblioteca con Gutendex API

Este programa permite gestionar una base de datos de libros y autores utilizando la API de **Gutendex**. Ofrece funcionalidades clave para registrar, consultar y analizar libros y autores en una base de datos PostgreSQL. El código está desarrollado en **Java** utilizando **JPA** para la persistencia de datos.

---

## **Funcionalidades Principales**

### 1. **Buscar libros por título**
   - Puedes buscar libros mediante una palabra clave del título.
   - El programa utiliza la API de Gutendex para encontrar libros relacionados.
   - Si no se encuentra un libro o autor previamente en la base de datos, se registra automáticamente.
   - En caso de intentar agregar un libro o autor ya existente, el programa indica al usuario que ese libro ya esta registrado en la base de datos.
   - Si se fuese a cargar otro libro del mismo autor (Por ejemplo Hamlet y Romeo y Julieta de William Shakespeare) el programa automaticamente asocia ambos libros al mismo autor.

---

### 2. **Registrar libros en la base de datos**
   - El programa verifica que tanto el libro como el autor no estén previamente registrados.
   - Los libros y autores se guardan en tablas relacionadas en PostgreSQL.
   - Los libros poseen datos sobre su titulo, idioma, numero de descargas y autor.
   - Los autores poseen informacion de su nombre completo, fecha de nacimiento y fecha de fallecimiento si fuese pertinente.

---

### 3. **Listar todos los libros**
   - Muestra todos los libros registrados en la base de datos, junto con la información relevante.

---

### 4. **Listar todos los autores**
   - Permite visualizar una lista completa de autores registrados en la base de datos.

---

### 5. **Consultar autores vivos en un año específico**
   - Introduce un año y el programa devolverá una lista de autores que estaban vivos en ese período.
   - Funcionalidad útil para análisis históricos o consultas específicas.

---

### 6. **Buscar libros por idioma**
   - Filtra los libros registrados por idioma.
   - Actualmente soporta los siguientes idiomas:
     - **Inglés**
     - **Español**
     - **Portugués**
     - **Francés**
     - **Finlandés**

---

## **Tecnologías Utilizadas**

- **Lenguaje:** Java
- **Persistencia:** JPA (Java Persistence API)
- **Base de datos:** PostgreSQL
- **API externa:** Gutendex (para búsqueda de libros)
- **Frameworks y librerías adicionales:**
  - Hibernate (para implementación de JPA)
  - Spring Data JPA (para interacción con la base de datos)

---

## **Cómo Configurar el Proyecto**

1. **Instalar PostgreSQL:**
   - Configura una base de datos y actualiza las credenciales en el archivo `application.properties` o `application.yml`.

2. **Configurar Dependencias:**
   - Usa **Maven** para descargar e integrar las dependencias necesarias. Estas incluyen:
     - `spring-boot-starter-data-jpa`
     - `spring-boot-starter-web`
     - `postgresql`

3. **Configurar la conexión a la API de Gutendex:**
   - No es necesaria configuración adicional, ya que el programa interactúa directamente con la API pública.

4. **Ejecutar la aplicación:**
   - Compila y ejecuta el programa desde un IDE o línea de comandos.

---

## **Próximas Funcionalidades**

- **Búsqueda avanzada:** Permitir filtros por múltiples criterios (año, género, etc.).
- **Soporte para más idiomas:** Agregar más opciones a la búsqueda por idioma.
- **Integración con herramientas de migración:** Usar Flyway o Liquibase para gestionar la base de datos.

---

## **Contribuciones**
Si deseas contribuir al proyecto o reportar errores, abre un ticket en el repositorio o envía una solicitud de cambio.

**Autor:** Proyecto desarrollado por Joaquin Vera.

---
