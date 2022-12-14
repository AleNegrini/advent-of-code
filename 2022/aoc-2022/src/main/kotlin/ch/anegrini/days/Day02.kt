package ch.anegrini.days

import Day
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {}

enum class Move {
    A, B, C
}

enum class Strategy {
    X, Y, Z
}

object Day02: Day(2, "Paper Rock Scissors") {

    private val completeDay = if(this.day < 10) "0${this.day}" else "${this.day}"
    private val input = File("src/main/resources/inputs/day02/input")
        .inputStream()
        .readBytes()
        .toString(Charsets.UTF_8)

    private val inputPairs = input
        .split("\n")
        .map { pair -> pair.split(" ")[0] to pair.split(" ")[1] }

    private val strategiesPartOne: Map<Strategy, Move> = mapOf(
        Strategy.X to Move.A,
        Strategy.Y to Move.B,
        Strategy.Z to Move.C,
    )

    private val points: Map<Move, Int> = mapOf(
        Move.A to 1,
        Move.B to 2,
        Move.C to 3,
    )

    private val losingMatches: Map<Move, Move> = mapOf(
        Move.A to Move.C,
        Move.B to Move.A,
        Move.C to Move.B,
    )

    private val winningMatches: Map<Move, Move> = mapOf(
        Move.A to Move.B,
        Move.B to Move.C,
        Move.C to Move.A,
    )

    /**
     * Given a list of moves (pair of strings), it computes the total score
     */
    private fun computeTotalScore(moves: List<Pair<Move, Move>>) = moves
        .sumOf {
            when(it.second) {
                winningMatches[it.first] -> 6
                losingMatches[it.first] -> 0
                else -> 3
            } as Int
        } +
        moves.sumOf { points[it.second]!! }

    override fun partOne() {
        logger.info { "Starting challenge $description - advent day $completeDay!" }
        val plays = inputPairs
            .map {
                    play -> Move.valueOf(play.first) to strategiesPartOne[Strategy.valueOf(play.second)]!!
            }
        val result = computeTotalScore(plays)
        logger.info { "Solution for challenge ${this.description} - PART TWO - is $result" }
    }

    override fun partTwo() {
        logger.info { "Starting challenge $description - advent day $completeDay!" }
        val plays = inputPairs
            .map { play -> Move.valueOf(play.first) to (
                    when (play.second) {
                        "Y" -> Move.valueOf(play.first)
                        "X" -> losingMatches[Move.valueOf(play.first)]
                        else -> winningMatches[Move.valueOf(play.first)]
                    })!!
            }
        val result = computeTotalScore(plays)
        logger.info { "Solution for challenge ${this.description} - PART TWO - is $result" }
    }
}