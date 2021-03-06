## Как готовить водород? 
### (h2 database) :egg:

[-> план](./Roadmap3.md)

- [X] как подключить бд
- [X] как создать бд
- [X] выполнение ddl запросов (ddl - data definition language) (DDL служит для создания и модификации структуры БД, т.е. для создания/изменения/удаления таблиц и связей)
- [x] выполнение dml(data manipulation language)/crud(create read update delete) запросов, те как добавлять, извлекать, обновлять данные и тд

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
Горизонтальное слияние:  
1) INNER JOIN - Из строк левой таблицы и правой таблицы объединяются и возвращаются только те строки, по которым выполняются условия соединения.  
2) LEFT JOIN - Возвращаются все строки левой_таблицы (ключевое слово LEFT). Данными правой таблицы дополняются только те строки левой таблицы, для которых выполняются условия соединения. Для недостающих данных вместо строк правой таблицы вставляются NULL-значения.  
3) RIGHT JOIN - аналогично LEFT JOIN  
4) FULL JOIN - Возвращаются все строки левой таблицы и правой таблицы. Если для строк левой таблицы и правой таблицы выполняются условия соединения, то они объединяются в одну строку. Для строк, для которых не выполняются условия соединения, NULL-значения вставляются на место левой таблицы, либо на место правой таблицы, в зависимости от того данных какой таблицы в строке не имеется.  
5) CROSS JOIN - Объединение каждой строки левой таблицы со всеми строками правой таблицы. Этот вид соединения иногда называют декартовым произведением.  
Вертикальное объединение (union):  
1. UNION ALL В результат включаются все строки из обоих наборов. (A+B)
2. UNION - В результат включаются только уникальные строки двух наборов. DISTINCT(A+B)
3. EXCEPT - В результат попадают уникальные строки верхнего набора, которые отсутствуют в нижнем наборе. Разница 2-х множеств. DISTINCT(A-B)
4. INTERSECT - В результат включаются только уникальные строки, присутствующие в обоих наборах. Пересечение 2-х множеств. DISTINCT(A&B)
Бонус: конструкция WITH для разгрузки основного запроса:
```sql
WITH имя AS(
    SELECT T1 x1,T2 y1
    FROM одна_таблица
    EXCEPT
    SELECT B1,B2
    FROM другая_таблица
)
```
* insert - добавление новых данных
```sql
INSERT INTO таблица(перечень_полей) VALUES(перечень_значений)
//или же
INSERT INTO таблица(перечень_полей) SELECT перечень_значений FROM ...
```
Первый вариант записи вставляет новую строку исходя из перечисленных значений, второй же формирует новую запись на основе подзапроса.
* update - обновление данных
```sql
UPDATE таблица SET ... WHERE условие_выборки
// или же
UPDATE псевдоним SET ... FROM ...
```
* delete - удаление данных
```sql
DELETE таблица WHERE условие_выборки
// или
DELETE таблица FROM подзапрос
```
* merge - слияние данных
```sql
MERGE таблица_приемник
USING таблица_источник
ON условия_слияния

    // СТРОКА ЕСТЬ В ОДНОЙ, НО НЕТ В ДРУГОЙ ТАБЛИЦЕ
WHEN MATCHED BY  THEN

    // ЕСТЬ СООТВЕТСТВИЕ И ТАМ, И ТАМ
WHEN MATCHED THEN
```

---

#### Заключение
Были рассмотрены основы sql-запросов. В kotlin/java программах их можно (и нужно во избежание инъекций)
использовать посредством ```PreparedStatement``` (как открывать соединение см 1-й блок)
```kotlin
val query = "выражение, который может содержать параметры"

val statement = connection.prepareStatement(query)
statement.setString(1, параметр) // перечисление параметров начинается с 1

statement.execute()
/*
помимо execute, который просто выполняет скрипт, можно использовать другие методы:
ResultSet executeQuery() - для запросов select
int executeUpdate() - для ddl запросов или для dml запросов INSERT, UPDATE, DELETE
 */
```

---

PS: возможна еще более подробная декомпозиция шагов в случае большого объема информации