# 🎉 CoolEvents

**CoolEvents** — это веб-приложение для создания, поиска и управления мероприятиями и активностями. Проект разработан с использованием **Spring Boot**, **Thymeleaf**, **PostgreSQL**, аутентификации и авторизации через Spring Security.

## 🚀 Возможности

- 🔍 Просмотр всех мероприятий и активностей  
- 👤 Регистрация и авторизация пользователей  
- 🗓️ Создание и редактирование собственных мероприятий  
- ✅ Добавление активностей к мероприятиям  
- 🔐 Защита действий с помощью прав доступа  
- 🧾 Поиск мероприятий по названию

## 🛠️ Стек технологий

- **Backend**: Spring Boot, Spring Security, Hibernate (JPA)
- **Frontend**: Thymeleaf + Bootstrap
- **База данных**: PostgreSQL
- **Сборка**: Maven

## ⚙️ Настройка проекта

1. Клонируйте репозиторий:

```bash
git clone https://github.com/Bormitus/CoolEventsWeb.git
cd coolevents
```

2. Создайте файл `src/main/resources/application.properties` (на основе `application-sample.properties`) и укажите свои настройки БД:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/cooleventsweb
spring.datasource.username=postgres
spring.datasource.password=your_password
```

3. Запустите приложение:

```bash
./mvnw spring-boot:run
```

4. Перейдите в браузере по адресу [http://localhost:8080](http://localhost:8080)

## 💡 Скриншоты

![image](https://github.com/user-attachments/assets/9db9ae8d-c18d-42ab-9229-5579596984c7)
![image](https://github.com/user-attachments/assets/5dd2859d-c1a5-43b8-a748-216dfaf90a6d)

