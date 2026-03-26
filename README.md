**Nombres:** Katherin Juliana Moreno Carvajal, Mariana Salas Gutiérrez

# Patrones Trabajo 1 - Corte 2

## 1. Arquitectura Hexagonal

Mi empresa es de: **Turismo**

Las reglas de negocio importantes son:

* Una reserva confirmada debe garantizar disponibilidad en el tour.
* Un usuario no puede crear una reserva si el tour no tiene disponibilidad de cupos.
* Una reserva debe tener una fecha válida y no puede ser en el pasado.
* Un tour tiene una capacidad máxima de personas, la cual no puede ser superada por las reservas.
* Un guía turístico no puede estar asignado a más de un tour en la misma fecha y hora.
* Una reserva debe estar asociada a un usuario registrado en el sistema.
* Una reserva puede estar confirmada, cancelada o pendiente.
* No se puede confirmar una reserva sin validar previamente el pago.
* No se puede modificar ni cancelar una reserva que ya esté confirmada.
* La cantidad de personas en una reserva debe ser mayor a cero.

Los casos de uso que se ven en esta empresa son:

* Registrar usuario
* Actualizar usuario
* Eliminar usuario
* Listar usuarios existentes
* Buscar usuario específico
* Crear tours
* Consultar tours disponibles
* Listar todos los tours
* Modificar tour
* Cancelar tour
* Buscar tour específico
* Crear reserva
* Cancelar reserva
* Confirmar reserva
* Consultar historial de reservas

Los submódulos que vemos en esta empresa son:

* Gestión de tours
* Gestión de reservas
* Gestión de usuarios

La infraestructura que voy a utilizar:

* Se divide en dos subcarpetas / adaptadores:
    * Primarios o in: Se encargan de recibir solicitudes externas y enviarlas a la capa de aplicación.
        * controllers:
            * ReservaController
            * TourController
            * UsuarioController
        * advice:
            * GlobalExceptionHandler
    * Secundarios u out: Se encargan de interactuar con sistemas externos.
        * persistence:
            * ReservaMongoAdapter
            * TourMongoAdapter
            * UsuarioMongoAdapter
            * SpringDataReservaRepository
            * SpringDataTourRepository
            * SpringDataUsuarioRepository
        * http:
            * ToutHttpAdapter
            * UsuarioHttpAdapter
        * external:
            * PagoGatewayAdapter
* Tiene como base de datos no relacional a MongoDB.
* Usa API REST (Spring Boot) para la comunicación de los módulos.
* Maneja fechas en UTC 0.
* Tiene el manejo global de errores implementado mediante *@ControllerAdvice*.

## 2. Estructura del proyecto

```
turismo-app/
│
├── pom.xml
├── .gitignore
├── src/
│   ├── main/
│   │   └── java/com/turismo/turismo_app/
│   │       ├── TurismoAppApplication.java
│   │       ├── usuarios/
│   │       │   ├── dominio/
│   │       │   │   ├── entities/
│   │       │   │   │   └── Usuario.java
│   │       │   │   ├── ports/
│   │       │   │   │   └── UsuarioRepositoryPort.java
│   │       │   │   └── exceptions/
│   │       │   ├── aplicacion/
│   │       │   │   └── casos_uso/
│   │       │   │       ├── EliminarUsuario.java
│   │       │   │       ├── ModificarUsuario.java
│   │       │   │       ├── RegistrarUsuario.java
│   │       │   │       ├── ObtenerTodosUsuarios.java
│   │       │   │       └── ObtenerUsuario.java
│   │       │   └── infraestructura/
│   │       │       ├── in/
│   │       │       │   ├── controllers/
│   │       │       │   │   └── UsuarioController.java
│   │       │       │   └── advice/
│   │       │       │       └── GlobalExceptionHandler.java 
│   │       │       └── out/
│   │       │           └── persistence/
│   │       │               ├── SpringDataUsuarioRepository.java
│   │       │               └── UsuarioMongoAdapter.java
│   │       ├── tours/
│   │       │   ├── dominio/
│   │       │   │   ├── entities/
│   │       │   │   │   └── Tour.java
│   │       │   │   ├── ports/
│   │       │   │   │   └── TourRepositoryPort.java
│   │       │   │   └── exceptions/
│   │       │   ├── aplicacion/
│   │       │   │   └── casos_uso/
│   │       │   │       ├── CrearTour.java
│   │       │   │       ├── ModificarTour.java
│   │       │   │       ├── ConsultarDisponibilidad.java
│   │       │   │       ├── ObtenerTour.java
│   │       │   │       ├── ObtenerTodosTours.java
│   │       │   │       └── CancelarTour.java
│   │       │   └── infraestructura/
│   │       │       ├── in/
│   │       │       │   ├── controllers/
│   │       │       │   │   └── TourController.java
│   │       │       │   └── advice/
│   │       │       │       └── GlobalExceptionHandler.java 
│   │       │       └── out/
│   │       │           └── persistence/
│   │       │               ├── SpringDataTourRepository.java
│   │       │               └── TourMongoAdapter.java
│   │       └── reservas/
│   │           ├── dominio/
│   │           │   ├── entities/
│   │           │   │   └── Reserva.java
│   │           │   ├── ports/
│   │           │   │   ├── ReservaRepositoryPort.java
│   │           │   │   ├── TourClientPort.java
│   │           │   │   ├── UsuarioClientPort.java
│   │           │   │   └── PagoPort.java
│   │           │   └── exceptions/
│   │           ├── aplicacion/
│   │           │   └── casos_uso/
│   │           │       ├── CrearReserva.java
│   │           │       ├── CancelarReserva.java
│   │           │       ├── ConfirmarReserva.java
│   │           │       ├── ObtenerReserva.java
│   │           │       ├── ObtenerTodasReservas.java
│   │           │       └── ModificarReserva.java
│   │           └── infraestructura/
│   │               ├── in/
│   │               │   ├── controllers/
│   │               │   │   └── ReservaController.java
│   │               │   └── advice/
│   │               │       └── GlobalExceptionHandler.java
│   │               └── out/
│   │                   ├── persistence/
│   │                   |   ├── SpringDataReservaRepository.java
│   │                   │   └── ReservaMongoAdapter.java
│   │                   ├── http/
│   │                   │   ├── TourHttpAdapter.java
│   │                   │   └── UsuarioHttpAdapter.java
│   │                   └── external/
│   │                       └── PagoGatewayAdapter.java
│   └── test/
│       └── java/com/turismo/turismo_app/
│           ├── unitarias/
│           │   ├── reservas/
│           │   ├── tours/
│           │   └── usuarios/
│           └── integracion/
|               ├── reservas/
│               ├── tours/
│               └── usuarios/
└── README.md
```

## 3. Video de funcionamiento

[Click aquí para visualizar](https://canva.link/za21y1wjv40q1qz)

## 4. Pasos para la instalación

### 4.1. Requisitos

* Java 17+
* Maven 3.8+

### 4.2. Clonar el repositorio

```
git clone https://github.com/MariSalas23/Patrones_Turismo.git 
cd turismo-app
```

### 4.3. Compilar el proyecto

```
mvn clean install
```

### 4.4. Ejecutar la aplicación

```
mvn spring-boot:run
```

## 5. Postman

### 5.1. Usuarios

#### 5.1.1. POST

```
http://localhost:8080/usuarios
```

**Body (JSON):**

```
{
  "nombre": "Prueba",
  "correo": "pruebas@gmail.com",
  "tipo": "CLIENTE"
}
```

#### 5.1.2. GET

```
http://localhost:8080/usuarios
http://localhost:8080/usuarios/{id}
```

#### 5.1.3. PUT

```
http://localhost:8080/usuarios/{id}
```

**Body (JSON):**

```
{
  "nombre": "Modificado",
  "apellido": "Perez",
  "correo": "pruebas@gmail.com",
  "tipo": "CLIENTE"
}
```

#### 5.1.4. DELETE

```
http://localhost:8080/usuarios/{id}
```

### 5.2. Tour

#### 5.2.1. POST

```
http://localhost:8080/tours
```

**Body (JSON):**

```
{
  "nombre": "Tour Villavicencio,
  "ubicacion": "Villavicencio",
  "guiaId": "{id}",
  "capacidadMaxima": "21",
  "precio": "58000",
  "fechaInicio": "2026-02-10T09:00:00",
  "fechaFin": "2026-02-10T17:00:00"
}
```

#### 5.2.2. GET

```
http://localhost:8080/tours
http://localhost:8080/tours/{id}
http://localhost:8080/tours/disponibles
```

#### 5.2.3. PUT

```
http://localhost:8080/tours/{id}/cancelar
http://localhost:8080/tours/{id}
```

**Body (JSON):**

```
{
  "nombre": "Tour Villavicencio,
  "ubicacion": "Villavicencio",
  "guiaId": "{id}",
  "capacidadMaxima": "24",
  "precio": "55000",
  "fechaInicio": "2026-02-10T09:00:00",
  "fechaFin": "2026-02-10T17:00:00"
}
```

### 5.3. Reservas

#### 5.3.1. POST

```
http://localhost:8080/reservas
http://localhost:8080/reservas/{id}/confirmar
http://localhost:8080/reservas{id}/cancelar
```

**Body (JSON):**

```
{
  "usuarioId": "{id}",
  "tourId": "{id}",
  "cantidadPersonas": 2,
  "fecha": "2026-12-15T10:00:00"
}
```

#### 5.3.2. GET

```
http://localhost:8080/reservas
http://localhost:8080/reservas/{id}
```

**Body (JSON):**

```
{
  "usuarioId": "{id}",
  "tourId": "{id}",
  "cantidadPersonas": 2,
  "fecha": "2026-12-15T10:00:00"
}
```

#### 5.3.3. PUT

```
http://localhost:8080/reservas/{id}
```

**Body (JSON):**

```
{
  "cantidadPersonas": 4,
  "fecha": "2026-11-20T09:00:00"
}
```

## 6. Excepciones

### 6.1. Manejo de excepciones en el dominio

El sistema implementa un manejo de excepciones basado en el enfoque de Domain-Driven Design (DDD), donde cada submódulo define sus propias excepciones específicas para representar violaciones de reglas de negocio.

#### 6.1.1. Usuarios

El módulo de usuarios cuenta con excepciones orientadas a validar la integridad de los datos del usuario:

* **CampoVacioException:** Se lanza cuando un campo obligatorio no tiene valor.
* **CorreoInvalidoException:** Valida el formato del correo electrónico.
* **CorreoDuplicadoException:** Evita registrar usuarios con correos repetidos.
* **NombreInvalidoException:** Valida nombres incorrectos.
* **UsuarioNoEncontradoException:** Se lanza cuando no existe un usuario.
* **UsuarioException:** Excepción base del módulo.

#### 6.1.2. Tours

El módulo de tours maneja reglas relacionadas con la planificación y operación de los recorridos turísticos:

* **CampoInvalidoTourException:** Valida campos incorrectos en el tour.
* **CapacidadInvalidaException:** Asegura que la capacidad sea válida.
* **FechaInvalidaException:** Valida fechas incorrectas.
* **RangoFechasInvalidoException:** Evita rangos de fechas inconsistentes.
* **GuiaNoDisponibleException:** Impide asignar un guía ocupado.
* **GuiaNoExisteException:** Valida la existencia del guía.
* **TourException:** Excepción base del módulo.

#### 6.1.3. Reservas

El módulo de reservas controla las reglas relacionadas con la asignación de cupos y estados:

* **CapacidadExcedidaException:** Evita reservas sin disponibilidad.
* **FechaInvalidaReservaException:** Valida fechas inválidas o en el pasado.
* **EstadoReservaInvalidoException:** Controla transiciones incorrectas de estado.
* **ReservaNoEncontradaException:** Se lanza cuando la reserva no existe.
* **ReservaException:** Excepción base del módulo.

### 6.2 Control de excepciones no esperadas

Además de las excepciones de dominio, el sistema maneja errores técnicos o no controlados mediante excepciones genéricas de Spring Boot.

Se contemplan principalmente:

* **HttpMessageNotReadableException:** Se produce cuando el cuerpo de la solicitud contiene errores de formato, como JSON mal estructurado o fechas inválidas.
* **MethodArgumentTypeMismatchException:** Ocurre cuando un parámetro recibido no coincide con el tipo esperado (por ejemplo, enviar texto en lugar de un número).
* **Exception:** Captura cualquier error inesperado del sistema, evitando que se expongan detalles sensibles al cliente.

### 6.3 Manejo global con @ControllerAdvice

El sistema utiliza la anotación *@RestControllerAdvice* de Spring Boot para centralizar el manejo de excepciones a través de la clase *GlobalExceptionHandler*. Esto permite interceptar todas las excepciones lanzadas en la aplicación y devolver respuestas estructuradas al cliente.

Se encuentra en el siguiente paquete: *package com.turismo.turismo_app.reservas.infraestructura.in.advice;*

## 7. Pruebas

Se implementó y ejecutó una suite de pruebas automatizadas para validar el comportamiento principal del proyecto en los módulos de **usuarios**, **tours** y **reservas**, así como la carga general del contexto de Spring Boot.

### 7.1. Resultado general

La ejecución completa de pruebas finalizó satisfactoriamente con el siguiente resultado:

- **Tests ejecutados:** 28
- **Fallos:** 0
- **Errores:** 0
- **Pruebas omitidas:** 0
- **Resultado final:** `BUILD SUCCESS`

### 7.2. Tipos de pruebas realizadas

Se trabajó con dos tipos principales de pruebas:

#### 7.2.1. Pruebas unitarias
Estas pruebas validan componentes aislados del sistema, verificando reglas de negocio, validaciones y comportamiento interno sin depender del flujo completo de la aplicación.

Se cubrieron principalmente los siguientes aspectos:

- creación y validación de usuarios;
- conversión y normalización del tipo de usuario;
- consulta de usuarios;
- comportamiento del adaptador de persistencia de usuarios;
- creación de tours;
- validación de reglas básicas de negocio en tours.

#### 7.2.2. Pruebas de integración

Estas pruebas verifican la interacción entre varias capas del sistema, especialmente controladores HTTP, contexto Spring Boot y endpoints expuestos por la aplicación.

Se validaron principalmente:

- endpoints del módulo de usuarios;
- endpoints del módulo de reservas;
- carga del contexto general de la aplicación;
- funcionamiento del entorno de pruebas con Spring Boot.

### 7.3. Clases de prueba ejecutadas

Durante la corrida exitosa se ejecutaron las siguientes clases de prueba:

- `ReservaIntegrationTest`
- `UsuarioIntegrationTest`
- `TurismoAppApplicationTests`
- `CrearTourTest`
- `ObtenerUsuarioTest`
- `RegistrarUsuarioTest`
- `TipoUsuarioTest`
- `UsuarioMongoAdapterTest`

### 7.4. Cobertura funcional alcanzada

Con esta suite se comprobó que el sistema:

- crea usuarios correctamente;
- lista usuarios correctamente;
- interpreta y valida adecuadamente el tipo de usuario;
- integra correctamente el controlador de usuarios con la capa HTTP;
- carga correctamente el contexto de Spring Boot;
- permite validar el flujo principal del módulo de reservas desde integración;
- permite validar la creación de tours desde pruebas unitarias.

### 7.5. Entorno de ejecución

La corrida final exitosa se realizó con **Java 17**, lo que permitió evitar los problemas previos de compatibilidad detectados al ejecutar algunas pruebas con Java 23.

### 7.6. Comando utilizado

Para ejecutar la suite completa se utilizó:

```bash
./mvnw test