package main.kotlin

fun main(args: Array<String>) {

    val list = if (args.isEmpty())
        readLine()!!.split(" ")
    else
        args.toList()

    if (list[0] != "")
        echo(list)
}

fun echo(array: List<String>) {
    array.sorted().groupingBy { it }.eachCount().toList().sortedByDescending { it.second }.map { "${it.first} ${it.second}" }.forEach(::println)
}