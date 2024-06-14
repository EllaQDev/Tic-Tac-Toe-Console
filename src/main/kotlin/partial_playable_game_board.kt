import kotlin.math.absoluteValue

fun printBoard(first: List<Char>, second: List<Char>, third: List<Char>) {
    println(TOP_BOTTOM_BORDER)
    println("$SIDE_BORDER_CHAR ${first.joinToString(separator = " ")} $SIDE_BORDER_CHAR")
    println("$SIDE_BORDER_CHAR ${second.joinToString(separator = " ")} $SIDE_BORDER_CHAR")
    println("$SIDE_BORDER_CHAR ${third.joinToString(separator = " ")} $SIDE_BORDER_CHAR")
    println(TOP_BOTTOM_BORDER)
}
fun assessGameState(gameBoard: List<MutableList<Char>>): GameState {
    var winner_X = false
    var winner_O = false
    val first = gameBoard[0]
    val second = gameBoard[1]
    val third = gameBoard[2]
    //matching columns
    for (i in 0..2){
        if (first[i] == second[i] && first[i] == third[i]) {
            if (first[i] == 'X') winner_X = true
            if (first[i] == 'O') winner_O = true
        }
    }
    //matching rows
    if (first.all { it == 'X'} || second.all { it == 'X'} || third.all { it == 'X' }) {
        winner_X = true
    }
    if (first.all { it == 'O'} || second.all { it == 'O'} || third.all { it == 'O' }) {
        winner_O = true
    }
    //checking diagonals (two possible)
    if (first.first() == second[1] && first.first() == third[2] &&
        (first.first() == 'X' || first.first() == 'O')) {
        if (first.first() == 'X') winner_X = true
        if (first.first() == 'O') winner_O = true
    }
    if (first.last() == second[1] && first.last() == third.first()) {
        if (first.last() == 'X') winner_X = true
        if (first.last() == 'O') winner_O = true
    }

    //checking if empty cells (when no winner found)
    var unfinished = false
    if (!winner_X && !winner_O) {
        if (gameBoard.flatten().count { it == '_'} > 0) {
            unfinished = true
        }
    }

//    var countDivergence = false
//    if ((userInput.count { it == 'X'} - userInput.count{ it == 'O'}).absoluteValue >= 2) {
//        countDivergence = true
//    }
    val gameState = when {
        winner_X -> GameState.X_WINS
        winner_O -> GameState.O_WINS
        unfinished -> GameState.GAME_UNFINISHED
        else -> GameState.DRAW
    }
    return gameState
}
class OutOfBoundException(override val message: String): Exception(message)
class IllegalMoveException(override val message: String): Exception(message)

fun main() {
    // val userInput = readln().toCharArray()
    // changed to empty grid to start
    val userInput = "_________"
    val first = userInput.slice(0..2).toMutableList()
    val second = userInput.slice(3..5).toMutableList()
    val third = userInput.slice(6..8).toMutableList()

    val gameBoard = listOf(first, second, third)
    var gameCondition = GameState.GAME_UNFINISHED
    printBoard(first,second, third)

    var currPlayer = 'X'
    while(gameCondition != GameState.O_WINS || gameCondition != GameState.X_WINS ||
    gameCondition != GameState.DRAW) {

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
                    gameBoard[row - 1][col - 1] = currPlayer
                    currPlayer = if (currPlayer == 'X') 'O' else 'X'
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
        gameCondition = assessGameState(gameBoard)
        if(gameCondition != GameState.GAME_UNFINISHED) {
            println(gameCondition.msg)
            break
        }
    }
}
