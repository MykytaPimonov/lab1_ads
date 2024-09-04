package org.umsf.labs.lab1

internal fun sort(arr: MutableList<Int>): SortResult {
    val result = SortResult()
    result.add(arr)
    var d = arr.size / 2
    while (d > 0) {
        for (i in 0..<(arr.size - d)) {
            for (j in i downTo 0 step d) {
                if (arr[j] > arr[j + d]) {
                    arr.swap(j, j + d)
                    result.comparisonsCount++
                } else {
                    break
                }
            }
        }
        result.add(arr)
        d /= 2
    }
    return result
}

private fun MutableList<Int>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

internal class SortResult {

    private val steps: MutableList<List<Int>> = mutableListOf()
    
    internal var comparisonsCount: Int = 0

    internal val unsorted: List<Int>
        get() = steps[0]

    internal val sorted: List<Int>
        get() = steps[steps.size - 1]

    internal val intermediate: Iterable<List<Int>>
        get() = steps.subList(1, steps.size - 1)
    
    internal fun add(step: List<Int>) {
        steps.add(step.toList())
    }
}
