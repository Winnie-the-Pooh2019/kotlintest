# План функциональный
---
1) в main() получив аргументы командной строки, распределим их по парам (флаг-значение)
2) если параметров нет или есть -h, то выводим справку по командам
3) проверяем на корректность логин, даты и объем, если есть, иначе -> _7_ соответственно в кодами **2** / **7**
4) производим аутентификацию пользователя, если неудачно, то -> _7_ с кодом **2** (если формат логина неверный) / **3** (если неверный логин) / **4** (если неверный пароль)
5) если введена роль, производим авторизацию, если неудачно, то -> _7_ с кодом **5** (роль неизвестна) / **6** (нет доступа)
6) -> _7_ с кодом **0**
7) выходим с соответсвующим exit-кодом
