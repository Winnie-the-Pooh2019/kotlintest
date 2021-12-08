## Логер

[-> план](./Roadmap3.md)

- [X] как создать логер
- [X] как настроить вывод в файл

---
### Ход исследования

[1)](#логер) Перед началом работы можно создать файл конфигурации логера, содержащий описание логеров, уровни логеров (debug/info/error и тд), разметку и способ вывода (файл/консоль):
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
Затем считать в программе этот файл и инициализировать логер. Если такого файла не будет создано, логер будет создан с конфигурацией по умолчанию.

[2)](#логер) Чтобы логироваться в файл, нужно в раздел Appenders файла конфигурации добавить
```xml
<File name="rollingFile" fileName="./logs/log4j2.log" append="false">
    <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
</File>
```
Теперь логи будут выводиться и в файл.

---
PS: возможна еще более подробная декомпозиция шагов в случае большого объема информации