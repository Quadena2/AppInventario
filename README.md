# Inventario API - Backend (Spring Boot)

Este es el componente backend del Sistema de Gestión de Inventario, diseñado para administrar productos y sus respectivas categorías. La arquitectura está desarrollada en **Java** utilizando **Spring Boot** y expone tres capas de comunicación distintas para adaptarse a diversas necesidades del cliente móvil: **REST**, **GraphQL** y **WebSockets**.

## 🚀 Tecnologías Utilizadas

* **Java 11 / 17**
* **Spring Boot** (Spring Web, Spring Data JPA)
* **Spring GraphQL**
* **Spring WebSocket** (Protocolo STOMP)
* **Base de Datos:** PostgreSQL / MariaDB (configurable)
* **Gestor de Dependencias:** Maven / Gradle
* **Despliegue:** Railway (Nixpacks)

---

## 🛠️ Configuración e Instalación Local

### 1. Clonar el repositorio
```bash
git clone git@github.com:Quadena2/tu-repositorio-backend.git
cd tu-repositorio-backend

```
## 2. Configurar las propiedades del sistema

El proyecto está configurado para leer variables de entorno (ideal para producción) o utilizar valores por defecto para desarrollo local. Revisa el archivo src/main/resources/application.properties:
Properties
```bash
# Usa el puerto dinámico si existe (Railway), sino usa el 8080 (Local)
server.port=${PORT:8080}

# Credenciales dinámicas para BD
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5123/inventariodb}
spring.datasource.username=${DB_USER:tu_usuario_local}
spring.datasource.password=${DB_PASSWORD:tu_clave_local}

spring.jpa.hibernate.ddl-auto=update
spring.graphql.path=/graphql

```
## 3. Ejecutar la aplicación (Local)

Si utilizas Maven:
```bash
./mvnw spring-boot:run

```
### El servidor iniciará localmente en el puerto 8080