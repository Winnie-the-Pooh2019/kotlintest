#!/bin/bash

# enter filename without extension as script argument

#kotlinc "$1".kt -include-runtime -d "$1".jar

kotlinc ./src/testtasks/main/task1.kt -include-runtime -d ./task1.jar
kotlinc ./src/testtasks/main/task2.kt -include-runtime -d ./task2.jar
kotlinc ./src/testtasks/main/task3.kt -include-runtime -d ./task3.jar
kotlinc ./src/testtasks/main/task4.kt -include-runtime -d ./task4.jar
kotlinc ./src/testtasks/main/task5.kt -include-runtime -d ./task5.jar
kotlinc ./src/testtasks/main/task6.kt -include-runtime -d ./task6.jar