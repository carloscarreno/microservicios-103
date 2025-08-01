# microservicios-103

En este curso aprenderas a integrar la seguridad de los microservicios desarrollados con Spring Boot y Red Hat SSO.

## Módulo 1: Introducción a Microservicios (1 hora)
* Qué son los microservicios
* Comparación con arquitecturas monolíticas
* Ventajas y desafíos
* Patrones comunes en microservicios
* Introducción a Spring Boot para microservicios

## Módulo 2: Creando un microservicio con Spring Boot (2 horas)
* Estructura de un proyecto Spring Boot
* Controladores REST
* Uso de DTOs y manejo de respuestas JSON
* Validaciones y manejo de errores
* Ejercicio práctico: Primer microservicio RESTful

## Módulo 3: Comunicación entre microservicios (1 hora)
* RestTemplate vs WebClient
* Introducción a OpenFeign
* Manejo de tiempo de espera y resiliencia básica
* Ejercicio: Consumiendo otro microservicio

## Módulo 4: Introducción a Red Hat SSO (Keycloak) (1 hora)
* Qué es Red Hat SSO
* Arquitectura y componentes clave
* Protocolos soportados: OAuth2 / OIDC / SAML
* Instalación básica y acceso a la consola de administración

## Módulo 5: Configuración de Realms, Clients y Roles (1 hora)
* Crear un realm específico para microservicios
* Configuración de clientes confidenciales y públicos
* Definición de roles y grupos
* Práctica: Configuración completa para un cliente Spring Boot

## Módulo 6: Integración de Spring Boot con Red Hat SSO (2 horas)
* Introducción a Spring Security con OAuth2
* Configuración del application.properties para OIDC
* Uso de Spring Boot Starter OAuth2 Resource Server
* Verificación de tokens JWT automáticamente
* Ejercicio práctico: Microservicio protegido por Red Hat SSO

## Módulo 7: Gestión de usuarios y autenticación (2 hora)
* Administración de usuarios en RH-SSO
* Login interactivo con OAuth2 Authorization Code Flow
* Uso de Postman y CURL para obtener tokens
* Validación manual de JWT

## Módulo 8: Autorización y control de acceso por roles (2 hora)
* Extracción de claims desde el token JWT
* Uso de anotaciones @PreAuthorize y @RolesAllowed
* Manejo de autorizaciones a nivel de endpoint y método
* Práctica: Creación de endpoints con control por rol

## Módulo 9: Protección de microservicios en cadena (1 hora)
* Propagación de tokens entre servicios
* Transmisión segura de encabezados de autenticación
* Ejercicio: Microservicio consumidor que reenvía el token

## Módulo 10: Manejo de sesiones, expiraciones y refresh tokens (2 hora)
* Configuración del tiempo de vida de tokens en RH-SSO
* Uso de refresh tokens desde aplicaciones cliente
* Manejo de errores por expiración en Spring Boot

## Módulo 11: Seguridad avanzada (2 hora)
* Configuración de HTTPS en Spring Boot y Red Hat SSO
* MFA y flujos de autenticación condicional
* Buenas prácticas para producción

## Módulo 12: Laboratorio final (3 horas)
* Desarrollo de un sistema compuesto por:
+ Un microservicio de autenticación integrado con RH-SSO
+ Un microservicio consumidor protegido
+ Una aplicación cliente que solicita tokens y consume servicios
* Pruebas con Postman y validación de flujos
* Diagnóstico de errores y logs
* Preguntas y conclusiones

## Duración:
* 20 horas

## Instructor:
![instructor](images/instructor.png)
* Carlos Carreño, Email: ccarrenovi@gmail.com



