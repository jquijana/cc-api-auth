# cc-api-auth

Demo de reactividad con H2 y pruebas unitarias

## Tecnologias :
>- Spring Boot 2.5.4
>- Java 11
>- Unit Test

## Librerias
Librerias usadas en de desarrollo.

| Plugin |
| ------ |
| spring-boot-starter-data-r2dbc |
| spring-boot-starter-data-jpa |
| spring-boot-starter-webflux | 

## Instalación
```sh
build and run 
```

## Curl Example
```sh
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "jquijanac@dominio.cl",
    "password": "Abc12345",
    "phones": [
        {
            "number": "1234567",
            "cityCode": "1",
            "countryCode": "57"
        },
          {
            "number": "abc",
            "cityCode": "78954",
            "countryCode": "111"
        }
    ]
}'
```

## License
Creative commons : **Jhosef Q**
