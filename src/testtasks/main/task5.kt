package testtasks.main

fun main(args: Array<String>) {
    args.sorted().groupingBy { it }.eachCount().toList().sortedByDescending { it.second }.map { "${it.first} ${it.second}" }.forEach(::println)
}