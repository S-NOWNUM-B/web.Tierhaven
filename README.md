<div align="center">

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="docs/branding/tierhaven-mark-paper.svg">
  <img src="docs/branding/tierhaven-mark-ink.svg" width="64" height="64" alt="Логотип Tierhaven">
</picture>

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

<div align="center">

## О проекте

</div>

Tierhaven — учебное веб-приложение: пользователь создаёт шаблон с набором объектов, распределяет их по тирам и делится своим списком. Compare сопоставляет два списка одного шаблона: показывает процент совпадения, различия и пять самых спорных объектов.

Сейчас в репозитории подготовлен скелет: `backend/`, `frontend/` и `docs/`. Приложения и зависимости ещё не инициализированы.

<div align="center">

## Архитектура

</div>

Целевая схема: браузер обращается к Next.js, который проксирует API-запросы в Spring Boot. Бизнес-логика и доступ к данным принадлежат backend.

```mermaid
flowchart LR
    subgraph Client["Браузер"]
        UI["Интерфейс Tierhaven"]
    end
    subgraph Frontend["frontend/"]
        Next["Next.js · React · TypeScript<br/>Tailwind CSS"]
    end
    subgraph Backend["backend/"]
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

<div align="center">

## Локальная разработка

</div>

Команд запуска пока нет. Backend планируется на Kotlin и Spring Boot, frontend — на Next.js с Bun и Biome, локальная MongoDB — в Docker Compose. Версии и команды будут зафиксированы в конфигурации приложений при их инициализации.
