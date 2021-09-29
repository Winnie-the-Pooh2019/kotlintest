fun main(args: Array<String>) {

    val list = args.asList()

    val argsPairs = list.toHashSet().mapTo(ArrayList()) { Pair(it, list.count { s -> s == it }) }

    argsPairs.sortedWith { l, r -> l.first.compareTo(r.first) }.forEach { println("${it.first} ${it.second}") }
}
