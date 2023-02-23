# Auto-life project

## `“Автолайф”`- сервис для отслеживания штрафов и возможностью делать обслуживание автомобиля.

_**Запус проекта локально**_:
+ Сначала запускаем `eureka-server`
+ Далее запускаем остальные сервисы
+ Самым последнем запускаем `api-gateway`

## Сервисы:
### 1) `auth-service` сервис для авторизации/аудентификации:
+ ссылка на swagger: http://localhost:8082/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
### 2) `fine-car-service` Сервис для отслеживания и оплаты штрафов на автомобиль:
+ ссылка на swagger: http://localhost:8084/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/