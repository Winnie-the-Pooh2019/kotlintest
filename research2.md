## Логер

[-> план](./Roadmap3.md)

- [X] как добавить log4j2 к зависимостям проекта (далее будет грустная история)
- [X] как создать логер
- [X] как настроить вывод в файл

---
### Ход исследования

[1)](#логер) Как бы то ни было странно, перед использованием какой-либо сторонней библиотеки нужно ее скачать и добавить к проекту. Иногда и здесь бывают свои трудности.  
#### Мой опыт
Сначала я пошел на [сайт](https://logging.apache.org/log4j/2.x/download.html), не очень долго думая, скачал полный архив и добавил его к проекту. Вроде бы все должно работать,
но я постоянно получал ошибку:
```
ERROR StatusLogger LogManager returned an instance of org.apache.logging.slf4j.SLF4JLoggerContextFactory which does not implement org.apache.logging.log4j.core.impl.Log4jContextFactory.
Unable to initialize Log4j.
Exception in thread "main" java.lang.NoClassDefFoundError: org/slf4j/Logger
```
Первые мысли - странная ошибка: как так нет определения класса, и оказывается этот LogManager какой-то совсем неправильный, и он, судя по стектрейсу, делает неправильные Logger-ы.  
В интернете я нашел много чего, но зацепила меня [статья](https://urvanov.ru/2019/07/08/%d0%bb%d0%be%d0%b3%d0%b8%d1%80%d0%be%d0%b2%d0%b0%d0%bd%d0%b8%d0%b5-%d1%81-slf4j-%d0%b8-logback/).
Тут я узнаю, что стандартов логирования много, да еще и Slf4j пытается всех заменить. Вот он корень бед! Я решил внимательнее присмотреться к содержимому скачанного архива, 
которое я так опрометчиво добавил целиком в проект. Помимо нужных мне log4j-api-2.15.0.jar и log4j-core-2.15.0.jar там была куча других реализаций log4j, 
конфликтовавших между собой, из-за чего закономерно ничего не работало. Вычленив нужные мне .jar-ки, я добавил файл конфигурации и написал пару строчек кода, логирующего 
"hello world". И все заработало :)!

---
#### А теперь серьезно
* Сначала скачайте архив с [сайта](https://logging.apache.org/log4j/2.x/download.html)
* Распакуйте его
* Выберите log4j-api-2.15.0.jar и log4j-core-2.15.0.jar (или любой другой версии)
* Добавьте их к зависимостям проекта
* Пишите код :)

[2)](#логер) Перед началом работы можно (можно потому что иначе будет использоваться конфигурация по умолчанию) создать файл конфигурации логера, содержащий описание логеров, 
уровни логеров (debug/info/error и тд), разметку и способ вывода (файл/консоль):
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="info" strict="true">

    <Appenders>

        <Console name="stdout" target="SYSTEM_OUT">
            <PatternLayout type="PatternLayout" pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
        </Console>

        </Appenders>

    <Loggers>
        <Root level="info">
            <AppenderRef ref="stdout" />
        </Root>
    </Loggers>

</Configuration>
```
Затем считать в программе этот файл и инициализировать логер.

[3)](#логер) Чтобы логироваться в файл, нужно в раздел Appenders файла конфигурации добавить
```xml
<File name="rollingFile" fileName="./logs/log4j2.log" append="false">
    <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
</File>
```
Теперь логи будут выводиться и в файл.

---
PS: возможна еще более подробная декомпозиция шагов в случае большого объема информации