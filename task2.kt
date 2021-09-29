fun main(args: Array<String>) {
    args.asList().asSequence().sortedWith{
            s1, s2 -> s1.compareTo(s2)
        }.forEach{s -> println(s)}
}
