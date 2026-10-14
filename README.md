# Информационная система «Библиотека»
 
Веб-приложение для учёта выдачи книг студентам. **вариант У–Я**.

Хранит данные о книгах (ID, название, издательство, дата выдачи, ФИО студента, дата сдачи) и позволяет добавлять, удалять, изменять и искать записи, а также анализировать выдачи по дням.

## Структура проекта
 
```
library/src/main/
├── java/com/example/library/
│   ├── controller/BookController.java   // обработка запросов /books
│   ├── entity/Book.java                 // JPA-сущность
│   ├── repository/BookRepository.java   // запросы к БД
│   └── LibraryApplication.java          // точка входа
└── resources/
    ├── templates/index.html             // главная страница
    ├── templates/edit.html              // редактирование книги
    ├── application.properties           // настройки БД и JPA
    └── data.sql                         // начальные данные
```
