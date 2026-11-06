# Локальная разработка

![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)

Окружение: macOS, zsh. Команды выполняются из корня проекта, если не указан другой каталог. Docker Engine запущен, Docker Compose установлен

## 1. Запустить MongoDB

```sh
# Запустить контейнер в фоне и дождаться готовности MongoDB

docker compose up -d --wait
```

Ожидаемый результат: контейнер получает статус Healthy

## 2. Проверка состояния

```sh
# Показать состояние сервисов проекта

docker compose ps
```

Ожидаемый результат: MongoDB - healthy, порт - 127.0.0.1:27017

## 3. Остановить окружение

```sh
# Удалить контейнер и сеть, сохранив данные в volume

docker compose down
```

Предупреждение: флаг `-v` удаляет volumes вместе с данными

## Запустить backend

Приложение Spring Boot находится в `app/backend`, заготовка frontend — в `app/frontend`. Для backend нужен JDK 25; MongoDB должна быть запущена через Compose из корня репозитория.

Версии закреплены в `app/backend/build.gradle.kts` и `app/backend/gradle/wrapper/gradle-wrapper.properties`:

| Инструмент | Версия |
| --- | --- |
| Spring Boot | 4.1.2 |
| Kotlin | 2.3.21 |
| JDK | 25 |
| Gradle Wrapper | 9.8.2 |
| MockK | 1.14.11 |

```sh
# Перейти из корня репозитория в каталог backend и запустить профиль local
cd app/backend
./gradlew bootRun --args='--spring.profiles.active=local'
```

В другом терминале выполнить команду для проверки готовности приложения:

```sh
# Проверить состояние backend
curl --fail http://localhost:8080/actuator/health
```

Ожидаемый результат: ответ содержит `"status":"UP"`. Остановить backend можно через `Ctrl+C`. Команды Docker Compose выполняй из корня репозитория.

## Тесты backend

Для тестов нужен работающий Docker Engine: Testcontainers самостоятельно запускает отдельную MongoDB. Поднимать MongoDB через Compose для тестов не требуется.

Команду выполняй из `app/backend`:

```sh
# Запустить тесты backend, включая проверку контекста с Testcontainers
./gradlew test
```

Ожидаемый результат: `BUILD SUCCESSFUL`.

## Проблемы запуска

При проверке локального окружения с ядром `7.0.11-orbstack` MongoDB не запускалась: в логах появлялось сообщение `MongoDB cannot start: Linux kernel versions 6.19 and newer…`. Контейнер не доходил до состояния `healthy`.

Версию ядра Docker Engine можно проверить с хоста:

```sh
# Показать версию ядра Linux, на котором работает Docker Engine
docker info --format '{{.KernelVersion}}'
```

В проверенном окружении запуск заработал после перехода на Docker Desktop с ядром `7.0.14-linuxkit`: команда `docker compose up -d --wait` завершилась успешно, MongoDB получила статус `healthy`. Это результат конкретной проверки, а не общий вывод о поддержке OrbStack.

## Переменные окружения

Переменная `MONGO_URI` задаёт строку подключения backend к MongoDB в профиле `local` (`app/backend/src/main/resources/application-local.yaml`). Значение по умолчанию — `mongodb://localhost:27017/tierhaven`; образец находится в корневом `.env.example`. Текущий Compose эту переменную не использует. При запуске через Gradle файл `.env` автоматически не загружается: для другого адреса задай `MONGO_URI` в окружении процесса.
