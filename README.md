# Gestión de Cartera

Aplicación Spring Boot basada en arquitectura hexagonal para apoyar a la unidad de cobranza en la gestión de cartera de una caja de compensación familiar. El módulo permite registrar empresas en mora, asignar gestores, registrar seguimientos y registrar pagos de acuerdo con la Resolución 205 de 2024.

## Requisitos

- Java 17
- Maven 3.9+
- Base de datos DB2 (o contenedor compatible) accesible a través del driver JDBC `com.ibm.db2.jcc.DB2Driver`

## Configuración

Las propiedades de conexión se pueden definir mediante variables de entorno o directamente en `src/main/resources/application.yml`.

```properties
DB2_URL=jdbc:db2://localhost:50000/CARTERA
DB2_USERNAME=db2inst1
DB2_PASSWORD=password
```

## Ejecución

```bash
mvn spring-boot:run
```

## Endpoints principales

| Método | Ruta | Descripción |
| --- | --- | --- |
| `POST` | `/api/companies` | Registrar empresa o actualizar información básica. |
| `POST` | `/api/delinquencies/manual` | Registrar manualmente una obligación en mora. |
| `POST` | `/api/delinquencies/selection` | Registrar la selección mensual de empresas en mora (fuente AS/400). |
| `POST` | `/api/assignments` | Asignar un caso de cartera a un gestor. |
| `POST` | `/api/assignments/{id}/logs` | Registrar contactos, compromisos de pago y comentarios del gestor. |

Todos los recursos cuentan con operaciones de consulta complementarias para soportar la gestión mensual (listado por estado, consulta por gestor, etc.).

## Estructura del proyecto

- `domain`: modelos, puertos y reglas de negocio.
- `application`: casos de uso que orquestan los puertos.
- `infrastructure`: adaptadores web, persistencia JPA/DB2 y conectores externos (AS/400).

## Tablas

Las entidades JPA se encuentran en `com.cartera.cobranza.infrastructure.persistence.entity` y cubren:

- `CO_COMPANY`: Empresas afiliadas.
- `CO_DELINQUENCY_RECORD`: Registros mensuales de mora.
- `CO_MANAGER`: Gestores de cobranza.
- `CO_COLLECTION_ASSIGNMENT`: Asignaciones de cartera a gestores.
- `CO_COLLECTION_ACTION_LOG`: Bitácora de llamadas, correos y pagos.

Las estructuras soportan la trazabilidad solicitada para la gestión de cobranza.
