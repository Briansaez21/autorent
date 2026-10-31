# AutoRent - Sistema de Alquiler de Autos

Arquitectura Hexagonal con Java, Spring Boot y H2.

## Tecnologías
- Java 17
- Spring Boot 3.2.5
- Spring Data JPA
- H2 (memoria)
- Maven

## Ejecutar
```
mvn spring-boot:run
```
H2 console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:autorent, user sa)

## Estructura
```
com.autorent
├── domain/model          # Auto, Cliente, Alquiler, enums
├── domain/exception      # excepciones de negocio
├── application/port/input   # casos de uso (interfaces)
├── application/port/output  # repositorios (interfaces)
├── application/service      # AutoService, ClienteService, AlquilerService
├── infrastructure/adapter/input/rest  # controllers
├── infrastructure/adapter/output/persistence # JPA entities, repos, adapters
└── infrastructure/config    # BeanConfiguration, GlobalExceptionHandler
```

## Endpoints
- POST /api/autos
- GET /api/autos/{id}
- GET /api/autos/disponibles
- POST /api/clientes
- GET /api/clientes/{id}
- POST /api/alquileres
- GET /api/alquileres/{id}
- PUT /api/alquileres/{id}/finalizar

## Reglas de negocio
- Solo autos DISPONIBLES se alquilan.
- Al alquilar, auto pasa a ALQUILADO.
- Al finalizar, auto vuelve a DISPONIBLE.
- No finalizar dos veces.
- cantidadDias > 0.
- valorTotal = precioPorDia * cantidadDias (calculado por el sistema).

## Ejemplo crear auto
```json
{"placa":"ABC123","marca":"Toyota","modelo":"Corolla","anio":2025,"precioPorDia":150000}
```

## Ejemplo crear cliente
```json
{"nombre":"Carlos Pérez","documento":"123456789","telefono":"3001234567","email":"carlos@email.com"}
```

## Ejemplo crear alquiler
```json
{"clienteId":1,"autoId":1,"cantidadDias":5}
```
Valor calculado: 150000 x 5 = 750000.
