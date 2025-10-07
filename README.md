# GestorProductos

Proyecto Spring Boot (Java 17) simple para gestionar productos (CRUD) usando Spring Data JPA y Thymeleaf.

## Qué contiene

- Controlador MVC: `src/main/java/org/cibertec/edu/pe/controler/Controlador.java` — expone las rutas para listar, crear, editar y eliminar productos.
- Entidad: `src/main/java/org/cibertec/edu/pe/modelo/Producto.java` — modelo JPA con campos `id`, `nombre` y `precio`.
- Repositorio: `src/main/java/org/cibertec/edu/pe/interfaces/IProducto.java` — extiende `JpaRepository`.
- Servicio: `src/main/java/org/cibertec/edu/pe/service/ProductoService.java` — implementación de la interfaz `IProductoService` y lógica simple de persistencia.
- Plantillas Thymeleaf: `src/main/resources/templates/index.html` y `form.html` — interfaz mínima para listar y editar productos.
- `pom.xml` — dependencias: Spring Boot Starter Web, Data JPA, Thymeleaf y MySQL Connector.

> Nota: `src/main/resources/application.properties` está vacío en este repositorio. Más abajo incluyo ejemplos de configuración para desarrollo.

## Requisitos

- Java 17 instalado y configurado (`JAVA_HOME`).
- En Windows se puede usar el wrapper incluido: `mvnw.cmd` (no se necesita Maven instalado globalmente).
- Base de datos: MySQL (o usar H2 en memoria si prefieres, ver sección Opciones).

## Estructura resumida

```text
src/main/
  java/org/cibertec/edu/pe/
    controler/Controlador.java        # Rutas y flujo MVC
    modelo/Producto.java              # Entidad JPA
    interfaces/IProducto.java         # Repositorio (JpaRepository)
    interfaceService/IProductoService.java
    service/ProductoService.java      # Implementación del servicio
  resources/
    templates/index.html
    templates/form.html
    application.properties            # Configuración (vacío en repo)
pom.xml
mvnw.cmd
```

## Rutas disponibles (HTML)

- GET /listar -> Lista todos los productos (muestra `index.html`).
- GET /new -> Muestra formulario vacío para crear un producto (`form.html`).
- POST /save -> Guarda un producto nuevo o modificado.
- GET /editar/{id} -> Carga un producto en el formulario para editarlo.
- GET /eliminar/{id} -> Elimina el producto y redirige a /listar.

Estos endpoints son manejados por `Controlador.java`.

## Configuración de base de datos (ejemplo MySQL)

Edita `src/main/resources/application.properties` y añade algo como:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestorproductos?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8080
spring.thymeleaf.cache=false
```

Reemplaza `TU_USUARIO` y `TU_PASSWORD` por tus credenciales. Crea la base de datos `gestorproductos` en MySQL antes de arrancar la app:

```sql
CREATE DATABASE gestorproductos;
```

### Opción rápida: H2 en memoria (solo para pruebas)

Si quieres ejecutar sin MySQL, puedes usar H2. Añade la dependencia en `pom.xml`:

```xml
<!-- para pruebas rápidas en memoria -->
<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>
```

y en `application.properties`:

```properties
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:gestor;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

Con H2 la app arrancará sin necesidad de crear una base externa. (Recuerda: H2 en memoria borra datos al detener la app.)

## Cómo compilar y ejecutar (Windows)

Desde la raíz del proyecto en Windows (usa `mvnw.cmd` incluido):

```powershell
# Compilar y ejecutar tests
mvnw.cmd clean test

# Empaquetar
mvnw.cmd clean package

# Ejecutar con Spring Boot (modo desarrollo)
mvnw.cmd spring-boot:run

# O ejecutar el JAR creado
java -jar target/GestorProductos-0.0.1-SNAPSHOT.jar
```

Si usas PowerShell o cmd, los comandos anteriores funcionan igual (usa `mvnw.cmd`).

La aplicación por defecto escucha en `http://localhost:8080` (a menos que cambies `server.port`). Abre `http://localhost:8080/listar` para ver la lista de productos.

## Ejecutar tests

```powershell
mvnw.cmd test
```

El proyecto incluye una clase de test `java/org/cibertec/edu/pe/GestorProductosApplicationTests.java` con un test de contexto simple.

## Troubleshooting rápido

- Error: JAVA_HOME no encontrado -> asegúrate de que `JAVA_HOME` apunta a JDK 17.
- Error de conexión a BD -> revisa `spring.datasource.url`, credenciales y que MySQL esté escuchando en el puerto esperado.
- Puerto 8080 ocupado -> cambia `server.port` en `application.properties`.

## Qué mejorar / próximos pasos

- Validaciones y manejo de errores en el formulario.
- Paginación y búsqueda en la lista de productos.
- Agregar DTOs y mapeo (MapStruct) si crece la lógica de negocio.
- Añadir pruebas unitarias y de integración para `ProductoService` y `Controlador`.

---

Si quieres, puedo:

- Añadir un ejemplo funcional de `application.properties` con las credenciales en un archivo de ejemplo (por ejemplo `application-dev.properties`).
- Añadir dependencia H2 y una configuración para `spring.profiles.active=dev` para pruebas rápidas.
- Escribir pruebas unitarias básicas para `ProductoService`.

¿Qué prefieres que haga ahora?
