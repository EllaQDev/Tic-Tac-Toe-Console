import kotlin.math.absoluteValue

const val TOP_BOTTOM_BORDER = "---------"
const val SIDE_BORDER_CHAR = "|"

enum class GameState(val msg: String) {
    GAME_UNFINISHED("Game not finished"),
    DRAW("Draw"),
    X_WINS("X wins"),
    O_WINS("O wins"),
    IMP("Impossible")
}

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
    // next phase:
    // In this stage, your program should:
    //
    //Take a string entered by the user and print the game grid as in the previous stage.
    //Analyze the game state and print the result. Possible states:
    //Game not finished when neither side has three in a row but the grid still has empty cells.
    //Draw when no side has a three in a row and the grid has no empty cells.
    //X wins when the grid has three X’s in a row (including diagonals).
    //O wins when the grid has three O’s in a row (including diagonals).
    //Impossible when the grid has three X’s in a row as well as three O’s in a row, or there are a lot more X's than O's or vice versa (the difference should be 1 or 0; if the difference is 2 or more, then the game state is impossible).
    //In this stage, we will assume that either X or O can start the game.
    //
    //You can choose whether to use a space or underscore _ to print empty cells.
    var winner_X = false
    var winner_O = false
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
        if (userInput.count { it == '_'} > 0) {
            unfinished = true
        }
    }

    var countDivergence = false
    if ((userInput.count { it == 'X'} - userInput.count{ it == 'O'}).absoluteValue >= 2) {
        countDivergence = true
    }
    val gameState = when {
        winner_O && winner_X -> GameState.IMP
        countDivergence -> GameState.IMP
        winner_X -> GameState.X_WINS
        winner_O -> GameState.O_WINS
        unfinished -> GameState.GAME_UNFINISHED
        else -> GameState.DRAW
    }
    println(gameState.msg)
}