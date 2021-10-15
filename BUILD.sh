#!/bin/bash

# enter filename without extension as script argument

#kotlinc "$1".kt -include-runtime -d "$1".jar

kotlinc task1.kt -include-runtime -d task1.jar
kotlinc task2.kt -include-runtime -d task2.jar
kotlinc task3.kt -include-runtime -d task3.jar
kotlinc task4.kt -include-runtime -d task4.jar
kotlinc task5.kt -include-runtime -d task5.jar
kotlinc task6.kt -include-runtime -d task6.jar