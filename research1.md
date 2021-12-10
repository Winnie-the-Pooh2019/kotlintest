## Как готовить водород? 
### (h2 database) :egg:

[-> план](./Roadmap3.md)

- [X] как подключить бд
- [X] как создать бд
- [X] выполнение ddl запросов (ddl - data definition language) (DDL служит для создания и модификации структуры БД, т.е. для создания/изменения/удаления таблиц и связей)
- [ ] выполнение dml(data manipulation language)/crud(create read update delete) запросов, те как добавлять, извлекать, обновлять данные и тд

---
### Ход исследования

[1)](#Как-готовить-водород?)
В нашем проекте будем использовать встраиваемую версию h2.  
Чтобы подключить ее к проекту достаточно лишь добавить драйвер к зависимостям своего проекта:
* вручную:  
    необходимо [скачать](http://www.h2database.com/html/main.html) архив драйвера
* при помощи gradle:
```kotlin
dependencies {
    implementation(group = "com.h2database", name = "h2", version =  "1.4.200")
}
```
* при помощи maven:

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <version>1.4.200</version>
    <scope>runtime</scope>
</dependency>
```
Далее необходимо подключить базу данных в приложении и в IntelliJ IDEA для простоты редактирования:
* в коде программы:
```kotlin
val connection: Connection = JdbcDataSource().also {
        it.setURL("jdbc:h2:<путь до файла базы>")
    }.getConnection("логин пользователя", "пароль пользователя")
```
* в IntelliJ IDEA:  
```View -> Tool Windows -> Database -> New``` в появившемся окне указать путь, данные пользователя и драйвер

---

[2)](#Как-готовить-водород?)
Если к моменту подключения база не была создана, то указанные выше действия приведут к ее созданию

---

[3)](#Как-готовить-водород?)
DDL или же Data Definition Language - набор sql команд, позволяющих управлять структурой базы данных.
Ниже представлены dll команды:
* CREATE - создает таблицу:
```sql
CREATE TABLE table_name (
    Column_1 data_type null_or_not is_unique is_primary_key etc,
    Column_2 data_type null_or_not is_unique is_primary_key etc
    ...
);
```
* DROP - уничтожает таблицу:
```sql
DROP TABLE database_name;
```
* ALTER - добавляет столбец, изменяет столбец, удаляет столбец, переименовывает столбец или переименовывает таблицу
```sql
ALTER TABLE table_name
  ADD column_name column_definition;
```
```sql
ALTER TABLE table_name
    MODIFY column_name column_type;
```
```sql
ALTER TABLE table_name
    DROP COLUMN column_name;
```
```sql
ALTER TABLE table_name
    ALTER COLUMN old_name RENAME TO new_name;
```
```sql
ALTER TABLE table_name
    RENAME TO new_table_name;
```
* TRUNCATE - используется для удаления всех записей из таблицы
```sql
TRUNCATE TABLE table_name;
```
* RENAME - переименовывает таблицу/колонку и тд

Ссылка на более [подробный материал](https://habr.com/en/post/255361/)

---

[4)](#Как-готовить-водород?)
DML - это группа операторов для манипуляции данными. С помощью этих операторов 
мы можем добавлять, изменять, удалять и выгружать данные из базы, т.е. манипулировать ими.
DML содержит следующие команды:
* select - выборка данных, базовый синтаксис:
```sql
SELECT [DISTINCT] список_столбцов или * FROM источник WHERE фильтр ORDER BY выражение_сортировки
```
Для таблиц в случае сложных запросов можно задавать псевдонимы:
```sql
SELECT T.ID, T.NAME FROM TABLES AS T
```
Также выбранные данные можно сортировать по каким либо колонкам:
```sql
SELECT LastName, FirstName, Salary FROM Employees ORDER BY LastName, Salary <DESC>
```
Для того чтобы ограничить количество возвращаемых значений, используется команда TOP:
```sql
SELECT TOP 2 * FROM Employees
```
Можно фильтровать записи:
```sql
SELECT ID,LastName,FirstName,Salary FROM Employees WHERE DepartmentID=3 ORDER BY LastName,FirstName
```
Булевых операторов в языке SQL всего 3 – AND, OR и NOT
Вместе с where могут использоваться дополнительные операторы:  
between - проверяет на вхождение в диапазон
```sql
SELECT ID,Name,Salary FROM Employees WHERE Salary BETWEEN 2000 AND 3000
```
In - проверяет на вхождение в перечень
```sql
SELECT ID,Name,Salary FROM Employees WHERE PositionID IN(3,4)
```
Like - проверяет строки на соответствие шаблону
спецсимволы для шаблонов: "_" (на его месте должен быть один символ) 
и "%" (на его месте может стоять любое количество символов)
```sql
SELECT ID,LastName FROM Employees WHERE LastName LIKE '_етр%'
```
* insert - добавление новых данных
* update - обновление данных
* delete - удаление данных
* merge - слияние данных

---

PS: возможна еще более подробная декомпозиция шагов в случае большого объема информации