# job4j_accidents

## Описание проекта
В блоке Spring мы будем разрабатывать проект - Автонарушители.
В системе существуют две роли. Обычные пользователи и автоинспекторы.
Пользователь добавляет описание автонарушение.
В заявлении указывает: адрес, номер машины, описание нарушения и фотографию нарушения.
У заявки есть статус. Принята. Отклонена. Завершена.
Вид системы. Главная страница - это поиск с таблицей.

## Стек технологий
* Java 17
* PostgreSQL 14
* Apache Maven 3.8.5
* Spring Boot 2.7.3
* Liquibase 4.15.0
* Log4j 1.2.17
* Lombok 1.18.24
* JUnit 5.8.2
* Mockito 4.8.0
* Checkstyle 8.29
* Thymeleaf 3.0.15

## Требуемое окружение для запуска проекта
* Браузер
* JDK 17
* Apache Maven 3.8
* PostgreSQL 14

## Инструкция по запуску проекта
1) Скачать и разархивировать проект
2) В PostgreSQL создать базу данных cinema (url = `jdbc:postgresql://127.0.0.1:5432/accidents`)
3) Открыть командную строку и перейти в папку с проектом, например `cd c:\projects\job4j_accidents`
4) Выполнить команду `mvn install`
5) Перейти в папку target командой `cd target`
6) Выполнить команду `java -jar job4j_accidents-1.0.jar`
7) Перейти по ссылке `http://localhost:8081/accidents`

## Взаимодействие с приложением

## Контакты для связи
[![alt-text](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/kalchenko_denis)&nbsp;&nbsp;
[![alt-text](https://img.shields.io/badge/@%20email-005FED?style=flat&logo=mail&logoColor=white)](mailto:denfort50@yandex.ru)&nbsp;&nbsp;
