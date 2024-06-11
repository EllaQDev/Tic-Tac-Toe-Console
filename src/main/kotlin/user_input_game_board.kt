const val TOP_BOTTOM_BORDER = "---------"
const val SIDE_BORDER_CHAR = "|"

fun main() {
    val userInput = readln().toCharArray()
    println(TOP_BOTTOM_BORDER)
    val first = userInput.slice(0..2)
    val second = userInput.slice(3..5)
    val third = userInput.slice(6..8)

    println("$SIDE_BORDER_CHAR ${first.joinToString(separator = " ")} $SIDE_BORDER_CHAR")
    println("$SIDE_BORDER_CHAR ${second.joinToString(separator = " ")} $SIDE_BORDER_CHAR")
    println("$SIDE_BORDER_CHAR ${third.joinToString(separator = " ")} $SIDE_BORDER_CHAR")
    println(TOP_BOTTOM_BORDER)
}