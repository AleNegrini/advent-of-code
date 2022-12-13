package ch.anegrini.days

import Day
import mu.KotlinLogging
import java.io.File

private val logger = KotlinLogging.logger {}

object Day01: Day(1, "Calories Counting"){

    private val completeDay = if(this.day < 10) "0${this.day}" else "${this.day}"
    private val input = File("src/main/resources/inputs/day01/partOne")
        .inputStream()
        .readBytes()
        .toString(Charsets.UTF_8)

    private val caloriesList = input
        .split("\n\n")
        .map { s -> s.split("\n").sumOf(String::toInt) }

    override fun partOne() {
        logger.info { "Starting challenge ${this.description} - advent day ${completeDay}!" }
        val maxCalories = caloriesList.max()
        logger.info { "Solution for challenge ${this.description} is $maxCalories" }
    }

    override fun partTwo() {
        logger.info { "Starting challenge ${this.description} - advent day ${completeDay}!" }
        val topThreeCaloriesSum = caloriesList.sortedDescending().take(3).sum()
        logger.info { "Solution for challenge ${this.description} is $topThreeCaloriesSum" }
    }

}