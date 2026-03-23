**Nombres:** Katherin Juliana Moreno Carvajal, Mariana Salas Gutiérrez

# Patrones Trabajo 1 - Corte 2

Mi empresa es de: Turismo

Las reglas de negocio importantes son:

* Un usuario no puede reservar un servicio turístico sin disponibilidad.
* Una reserva debe tener fecha válida y no en el pasado.
* Un tour tiene una capacidad máxima de personas.
* No se puede confirmar una reserva sin pago registrado.
* Los precios pueden variar según la temporada.
* Un guía turístico no puede estar asignado a dos tours al mismo tiempo.
* Un tour debe estar asociado a una ubicación válida.
* No se puede registrar un tour sin ubicación, guía, precio y disponibilidad.
* Una reserva puede estar pendiente, confirmada o cancelada.

Los casos de uso que se ven en esta empresa son:

* Registrar usuario.
* Consultar tours disponibles.
* Crear reserva.
* Cancelar reserva.
* Confirmar reserva.
* Procesar pago.
* Consultar historial de reservas.
* Crear tours.
* Modificar tours.
* Borrar tours.

Los submódulos que vemos en esta empresa son:

* Gestión de tours
* Gestión de reservas
* Gestión de pagos

La infraestructura que voy a utilizar es:

* La base de datos tiene que ser NO relacional.
  

Se tienen que comuniciar los dos modulos a través de rest
NO ES NECESARIO QUE HAGA FRONT

Manejo de excepciones.
Control de excepciones no esperadas.
@ControllerAdvise.
Debe tener Unit test (Mock) e integration test (no hay mocks o muy pocos mock)
