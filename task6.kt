fun main(args: Array<String>) {

    if (args.isEmpty()) {
        val str = readLine()
        if (str != "")
            echo(str!!.split(" "))
    }
    else
        echo(args.toList())
}

fun echo(array: List<String>) {
    array.sorted().groupingBy { it }.eachCount().toList().sortedByDescending { it.second }.map { "${it.first} ${it.second}" }.forEach(::println)
}