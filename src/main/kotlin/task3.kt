package main.kotlin

fun main(args: Array<String>) {
    args.distinct().sorted().forEach(::println)
}
