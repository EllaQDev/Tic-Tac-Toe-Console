
fun printBoard(first: List<Char>, second: List<Char>, third: List<Char>) {
    println(TOP_BOTTOM_BORDER)
    println("$SIDE_BORDER_CHAR ${first.joinToString(separator = " ")} $SIDE_BORDER_CHAR")
    println("$SIDE_BORDER_CHAR ${second.joinToString(separator = " ")} $SIDE_BORDER_CHAR")
    println("$SIDE_BORDER_CHAR ${third.joinToString(separator = " ")} $SIDE_BORDER_CHAR")
    println(TOP_BOTTOM_BORDER)
}
class OutOfBoundException(override val message: String): Exception(message)
class IllegalMoveException(override val message: String): Exception(message)

fun main() {
    val userInput = readln().toCharArray()

    val first = userInput.slice(0..2).toMutableList()
    val second = userInput.slice(3..5).toMutableList()
    val third = userInput.slice(6..8).toMutableList()

    val gameBoard = listOf(first, second, third)

    printBoard(first,second, third)

    //accept user input
    while(true) {

    try {
            val playCoords = readln().split(" ")
            val row = playCoords[0].toInt()
            val col = playCoords[1].toInt()
            if (row < 1 || row > 3 || col < 1 || col > 3) throw OutOfBoundException("Coordinates should be from 1 to 3!")
            val current = when (row) {
                1 -> gameBoard[0][col - 1]
                2 -> gameBoard[1][col - 1]
                else -> gameBoard[2][col - 1]
            }
            if (current == 'X' || current == 'O') throw IllegalMoveException("This cell is occupied! Choose another one!") else {
                gameBoard[row - 1][col - 1] = 'X'
                printBoard(first, second, third)
                break
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
        } catch (e: OutOfBoundException) {
            println(e.message)
        } catch (e: IllegalMoveException) {
            println(e.message)
        }
    }
}