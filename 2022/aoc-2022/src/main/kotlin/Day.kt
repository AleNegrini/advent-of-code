abstract class Day(
    val day: Int,
    val description: String
){
    /**
     * This method is intended to contain the implementation of the first part of the daily challenge
     */
    abstract fun partOne()

    /**
     * This method is intended to contain the implementation of the second part of the daily challenge
     */
    abstract fun partTwo()

}