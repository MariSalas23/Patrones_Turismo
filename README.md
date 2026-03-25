**Nombres:** Katherin Juliana Moreno Carvajal, Mariana Salas Gutiérrez

# Patrones Trabajo 1 - Corte 2

## Arquitectura Hexagonal

Mi empresa es de: **Turismo**

Las reglas de negocio importantes son:

* Un usuario no puede reservar un servicio turístico sin disponibilidad.
* Una reserva debe tener fecha válida y no en el pasado.
* Un tour tiene una capacidad máxima de personas.
* Los precios pueden variar según la temporada.
* Un guía turístico no puede estar asignado a dos tours al mismo tiempo.
* Un tour debe estar asociado a una ubicación válida.
* No se puede registrar un tour sin ubicación, guía, precio y disponibilidad.
* Una reserva puede estar pendiente, confirmada o cancelada.
* No se puede confirmar una reserva sin validación de pago.

Los casos de uso que se ven en esta empresa son:

* Registrar usuario
* Obtener usuario
* Consultar tours disponibles
* Crear reserva
* Cancelar reserva
* Confirmar reserva
* Consultar historial de reservas
* Crear tours
* Modificar tours
* Cancelar tours

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
        * http:
            * ToutHttpAdapter
            * UsuarioHttpAdapter
        * external:
            * PagoGatewayAdapter
* Tiene como base de datos no relacional a MongoDB.
* Usa API REST (Spring Boot) para la comunicación de los módulos.
* Maneja fechas en UTC 0.
* Tiene el manejo global de errores implementado mediante *@ControllerAdvice*.

## Estructura del proyecto

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
│   │       │   │       └── UsuarioException.java  
│   │       │   ├── aplicacion/
│   │       │   │   └── casos_uso/
│   │       │   │       ├── RegistrarUsuario.java
│   │       │   │       └── ObtenerUsuario.java
│   │       │   └── infraestructura/
│   │       │       ├── in/
│   │       │       │   ├── controllers/
│   │       │       │   │   └── UsuarioController.java
│   │       │       │   └── advice/
│   │       │       │       └── GlobalExceptionHandler.java 
│   │       │       └── out/
│   │       │           └── persistence/
│   │       │               └── UsuarioMongoAdapter.java
│   │       ├── tours/
│   │       │   ├── dominio/
│   │       │   │   ├── entities/
│   │       │   │   │   └── Tour.java
│   │       │   │   ├── ports/
│   │       │   │   │   └── TourRepositoryPort.java
│   │       │   │   └── exceptions/
│   │       │   │       └── TourException.java 
│   │       │   ├── aplicacion/
│   │       │   │   └── casos_uso/
│   │       │   │       ├── CrearTour.java
│   │       │   │       ├── ModificarTour.java
│   │       │   │       └── CancelarTour.java
│   │       │   └── infraestructura/
│   │       │       ├── in/
│   │       │       │   ├── controllers/
│   │       │       │   │   └── TourController.java
│   │       │       │   └── advice/
│   │       │       │       └── GlobalExceptionHandler.java 
│   │       │       └── out/
│   │       │           └── persistence/
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
│   │           │       └── ReservaException.java
│   │           ├── aplicacion/
│   │           │   └── casos_uso/
│   │           │       ├── CrearReserva.java
│   │           │       ├── CancelarReserva.java
│   │           │       ├── ConfirmarReserva.java
│   │           │       └── HistorialReservas.java
│   │           └── infraestructura/
│   │               ├── in/
│   │               │   ├── controllers/
│   │               │   │   └── ReservaController.java
│   │               │   └── advice/
│   │               │       └── GlobalExceptionHandler.java
│   │               └── out/
│   │                   ├── persistence/
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
│           │   │   ├── CrearReservaTest.java
│           │   │   ├── ConfirmarReservaTest.java
│           │   │   └── CancelarReservaTest.java
│           │   ├── tours/
│           │   │   ├── CrearTourTest.java
│           │   │   ├── ModificarTourTest.java
│           │   │   └── CancelarTourTest.java
│           │   └── usuarios/
|           |       ├── ObtenerUsuarioTest.java
│           │       └── RegistrarUsuarioTest.java
│           └── integracion/
|               ├── reservas/
│               │   └── ReservaIntegrationTest.java
│               ├── tours/
│               │   └── TourIntegrationTest.java
│               └── usuarios/
│                   └── UsuarioIntegrationTest.java
└── README.md
```

## Video de funcionamiento

## Pasos para la instalación

### Requisitos

* Java 17+
* Maven 3.8+

### Clonar el repositorio

git clone https://github.com/MariSalas23/Patrones_Turismo.git 

cd turismo-app

### Compilar el proyecto

mvn clean install

### Ejecutar la aplicación

mvn spring-boot:run

### Postman

#### Usuarios

**POST**

http://localhost:8080/usuarios

**Body (JSON):**

{
  "nombre": "Prueba",
  "correo": "prueba@gmail.com",
  "tipo": "CLIENTE"
}

**GET**

http://localhost:8080/usuarios

## Excepciones

## Pruebas

Se implementó y ejecutó una suite de pruebas automatizadas para validar el comportamiento principal del proyecto en los módulos de **usuarios**, **tours** y **reservas**, así como la carga general del contexto de Spring Boot.

### Resultado general

La ejecución completa de pruebas finalizó satisfactoriamente con el siguiente resultado:

- **Tests ejecutados:** 28
- **Fallos:** 0
- **Errores:** 0
- **Pruebas omitidas:** 0
- **Resultado final:** `BUILD SUCCESS`

### Tipos de pruebas realizadas

Se trabajó con dos tipos principales de pruebas:

#### 1. Pruebas unitarias
Estas pruebas validan componentes aislados del sistema, verificando reglas de negocio, validaciones y comportamiento interno sin depender del flujo completo de la aplicación.

Se cubrieron principalmente los siguientes aspectos:

- creación y validación de usuarios;
- conversión y normalización del tipo de usuario;
- consulta de usuarios;
- comportamiento del adaptador de persistencia de usuarios;
- creación de tours;
- validación de reglas básicas de negocio en tours.

#### 2. Pruebas de integración
Estas pruebas verifican la interacción entre varias capas del sistema, especialmente controladores HTTP, contexto Spring Boot y endpoints expuestos por la aplicación.

Se validaron principalmente:

- endpoints del módulo de usuarios;
- endpoints del módulo de reservas;
- carga del contexto general de la aplicación;
- funcionamiento del entorno de pruebas con Spring Boot.

### Clases de prueba ejecutadas

Durante la corrida exitosa se ejecutaron las siguientes clases de prueba:

- `ReservaIntegrationTest`
- `UsuarioIntegrationTest`
- `TurismoAppApplicationTests`
- `CrearTourTest`
- `ObtenerUsuarioTest`
- `RegistrarUsuarioTest`
- `TipoUsuarioTest`
- `UsuarioMongoAdapterTest`

### Cobertura funcional alcanzada

Con esta suite se comprobó que el sistema:

- crea usuarios correctamente;
- lista usuarios correctamente;
- interpreta y valida adecuadamente el tipo de usuario;
- integra correctamente el controlador de usuarios con la capa HTTP;
- carga correctamente el contexto de Spring Boot;
- permite validar el flujo principal del módulo de reservas desde integración;
- permite validar la creación de tours desde pruebas unitarias.

### Entorno de ejecución

La corrida final exitosa se realizó con **Java 17**, lo que permitió evitar los problemas previos de compatibilidad detectados al ejecutar algunas pruebas con Java 23.

### Comando utilizado

Para ejecutar la suite completa se utilizó:

```bash
./mvnw test