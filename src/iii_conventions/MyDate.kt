package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.compareTo(date2: MyDate): Int = compareTo(date2)

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = addTimeIntervals(timeInterval, 1)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate
        = addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.times)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(times: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, times)

data class RepeatedTimeInterval(val timeInterval: TimeInterval, val times: Int)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {

    var currentDate = start

    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            override fun hasNext(): Boolean {
                return currentDate <= endInclusive
            }

            override fun next(): MyDate {
                val result = currentDate
                currentDate = currentDate.nextDay()
                return result
            }
        }
    }

}
