fun main(args: Array<String>) {
    args.sorted().groupingBy { it }.eachCount().forEach { println("${it.key} ${it.value}") }
}
