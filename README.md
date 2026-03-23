**Nombres:** Katherin Juliana Moreno Carvajal, Mariana Salas Gutiérrez

# Patrones Trabajo 1 - Corte 2

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

* Se divide en dos subcarpetas:
    * *Adaptadores de entrada (IN / primarios):* Se encargan de recibir solicitudes externas y enviarlas a la capa de aplicación.
        * Controllers:
            * ReservaController
            * TourController
            * UsuarioController
        * Advice:
            * GlobalExceptionHandler
    * *Adaptadores de salida (OUT / secundarios):* Se encargan de interactuar con sistemas externos.
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
* Tiene el manejo global de errores implementado mediante `@ControllerAdvice`.