fun main(args: Array<String>) {
    args.asList().asSequence().sortedWith{
            s1, s2 -> s1.compareTo(s2)
    }.toSet().forEach{s -> println(s)}
}
