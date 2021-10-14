fun main(args: Array<String>) {
    args.sorted().groupingBy { it }.eachCount().toList().sortedByDescending { it.second }.forEach { println("${it.first} ${it.second}") }
}