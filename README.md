<div align="center">

# Tierhaven

**Тир-листы по общим шаблонам и сравнение вкусов**

Целевой стек

![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Next.js](https://img.shields.io/badge/Next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white)
![TypeScript](https://img.shields.io/badge/TypeScript-3178C6?style=for-the-badge&logo=typescript&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)

</div>

---

## О проекте

Tierhaven — учебное веб-приложение: пользователь создаёт шаблон с набором объектов, распределяет их по тирам и делится своим списком. Compare сопоставляет два списка одного шаблона: показывает процент совпадения, различия и пять самых спорных объектов.

В репозитории подготовлены структура проекта и локальная MongoDB в Docker Compose. В `app/backend` инициализировано приложение Spring Boot; `app/frontend` пока содержит заготовку для frontend.

## Архитектура

Целевая схема: браузер обращается к Next.js, который проксирует API-запросы в Spring Boot. Бизнес-логика и доступ к данным принадлежат backend.

```mermaid
flowchart LR
    subgraph Client["Браузер"]
        UI["Интерфейс Tierhaven"]
    end
    subgraph Frontend["app/frontend/"]
        Next["Next.js · React · TypeScript<br/>Tailwind CSS"]
    end
    subgraph Backend["app/backend/"]
        API["Kotlin · Spring Boot"]
    end
    subgraph Storage["Хранилище"]
        Mongo[("MongoDB")]
    end
    UI --> Next
    Next -->|"/api/*"| API
    API --> Mongo
```

Требования, контракты и план реализации — в [спецификации](docs/SPEC.md).

## Локальная разработка

Для локальной разработки доступна MongoDB 8.0 в Docker Compose. Требования к окружению, запуск, проверка состояния и остановка описаны в [руководстве по локальной разработке](docs/local-development.md).
