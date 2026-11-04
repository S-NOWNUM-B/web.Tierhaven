# Локальная разработка

![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)

Окружение: macOS, zsh. Команды выполняются из корня проекта. Docker Engine запущен, Docker Compose установлен

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

## Переменные окружения

Переменная `MONGO_URI` заготовка для будущего backend, текущий Compose её не использует. Находится в файле `.env.example`, в ней хранится строка подключения с адресом сервера, порт на котором находится база данных и её имя
