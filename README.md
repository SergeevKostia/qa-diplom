# Дипломный проект по модулю «Автоматизация тестирования» для профессии «Инженер по тестированию»
# Описание проекта
Наше приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:
- Обычная оплата по дебетовой карте.
- Уникальная технология: выдача кредита по данным банковской карты.

![service.png](Documents%2Fservice.png)


# Документация

1. [План](https://github.com/SergeevKostia/qa-diplom/blob/main/Documents/Plan.md)
2. [Отчет по итогам тестирования](https://github.com/SergeevKostia/qa-diplom/blob/main/Documents/Report.md)
3. [Отчет по итогам автоматизации](https://github.com/SergeevKostia/qa-diplom/blob/main/Documents/Summary.md)



# Начало работы
1. Клонировать [репозиторий](https://github.com/SergeevKostia/qa-diplom) командой `git clone`;
## Prerequisites
1. Установить Docker Desktop;
2. Установить IntelliJ IDEA;
3. GIT;
4. Google Chrome;
## Установка и запуск
1. Открыть проект в IDEA;
2. Запустить БД командой `docker-compose up`;
3. Запустить SUT, открыв новую вкладку терминала:
   * для СУБД MySQL использоватеь команду:
   _`java -jar artifacts/aqa-shop.jar --spring.datasource.url=jdbc:mysql://localhost:3306/app`_
   * для СУБД PostgreSQL использовать команду:
   _`java -jar artifacts/aqa-shop.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/app`_
4. Открыть еще одну вкладку терминала
5. Запустить автотесты с построением отчета в Allure:
   * для запуска автотестов с СУБД MySQL использоватеь команду:
     `.\gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app" allureReport`
   * СУБД PostgreSQL использовать команду:
     `.\gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app" allureReport`
6. Открыть браузер и ввести в адресную строку `http://localhost:8080/`.
## Генерация и открытие отчетов
1. Для генерации отчета ввести в терминал команду: `./gradlew allureServe`;
2. После генерации отчет появится в окне браузера.