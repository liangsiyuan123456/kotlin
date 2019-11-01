import test.Sub

@Suppress("UNUSED_PARAMETER")
fun usage(arg: Sub): Sub {
    return Sub()
}

fun test() {
    usage(Sub())
}