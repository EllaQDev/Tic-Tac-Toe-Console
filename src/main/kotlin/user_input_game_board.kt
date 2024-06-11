const val TOP_BOTTOM_BORDER = "---------"
const val SIDE_BORDER_CHAR = "|"

fun main() {
    val userInput = readln()
    println(TOP_BOTTOM_BORDER)
    val (first, second, third) = userInput.chunked(3)
    println("$SIDE_BORDER_CHAR ${first[0]} ${first[1]} ${first[2]} $SIDE_BORDER_CHAR")
    println("$SIDE_BORDER_CHAR ${second[0]} ${second[1]} ${second[2]} $SIDE_BORDER_CHAR")
    println("$SIDE_BORDER_CHAR ${third[0]} ${third[1]} ${third[2]} $SIDE_BORDER_CHAR")
    println(TOP_BOTTOM_BORDER)
}