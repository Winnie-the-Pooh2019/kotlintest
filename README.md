---
![example workflow](https://github.com/Winnie-the-Pooh2019/kotlintest/actions/workflows/main.yml/badge.svg)
---
### [Первый план](./src/application/PLAN2.md)
### [Второй план](./Roadmap2.md)
---
## Инструкция по сборке:
Чтобы собрать проект, необходимо собрать его через терминал в unix-подобной операционной системе
```
   > ./BUILD.sh 
```
---
### Инструкция по 

## task1
### _Напишите приложение, которое на вход через параметры командной строки получит текст и выдаст список слов, разделенных пробельными символами._
### Пример вывода:
> $ java -jar task1.jar the quick brown fox jumps over the lazy dog  
> the  
> quick  
> brown  
> fox  
> jumps  
> over  
> the  
> lazy  
> dog

## task2
### _Слова из предыдущего задания должны быть отсортированы по алфавиту в возрастающем порядке_
### Пример вывода:
> $ java -jar task2.jar the quick brown fox jumps over the lazy dog  
> brown  
> dog  
> fox  
> jumps  
> lazy  
> over  
> quick  
> the  
> the

## task3
### _Слова из предыдущего задания должны быть уникальные_
### Пример вывода:
> $ java -jar task3.jar the quick brown fox jumps over the lazy dog  
> brown  
> dog  
> fox  
> jumps  
> lazy  
> over  
> quick  
> the

## task4
### _После каждого слова выведите количество повторений слова_
### Пример вывода:
> $ java -jar task4.jar the quick brown fox jumps over the lazy dog  
> brown 1  
> dog 1  
> fox 1  
> jumps 1  
> lazy 1  
> over 1  
> quick 1  
> the 2

## task5
### _Список должен быть отсортирован сначала по количеству повторений в убывающем порядке, в случае одинакового количества – по алфавиту_
### Пример вывода:
> $ java -jar task5.jar the quick brown fox jumps over the lazy dog  
> the 2  
> brown 1  
> dog 1  
> fox 1  
> jumps 1  
> lazy 1  
> over 1  
> quick 1  


## task6
### _Если вашему приложению из задания 5 не передано ни одного параметра, то считайте список слов для сортировки из стандартного потока ввода (stdin), чтобы ваше приложение дополнительно могло запускаться вот так:_
> echo "the quick brown fox jumps over the lazy dog" | java -jar yourapp.jar
### Пример вывода:
> $ echo "the quick brown fox jumps over the lazy dog" | java -jar task6.jar  
> the 2  
> brown 1  
> dog 1  
> fox 1  
> jumps 1  
> lazy 1  
> over 1  
> quick 1

> $ java -jar task6.jar the quick brown fox jumps over the lazy dog  
> the 2  
> brown 1  
> dog 1  
> fox 1  
> jumps 1  
> lazy 1  
> over 1  
> quick 1  
