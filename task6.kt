fun main(args: Array<String>) {

    val list: List<String> = if (args.isEmpty())
        readLine()!!.split(" ")
    else
        args.toList()

    val argsPairs = list.toHashSet().mapTo(ArrayList()) { Pair(it, list.count { s -> s == it }) }

    argsPairs.sortedWith { l, r ->
        if (l.second == r.second)
            l.first.compareTo(r.first)
        else
            r.second.compareTo(l.second)
    }.forEach { println("${it.first} ${it.second}") }
}