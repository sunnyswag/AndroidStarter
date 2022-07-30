package com.example.algorithmstarter

object Sort {

    fun bubbleSort(numbs: IntArray) {
        for (i in 1 until numbs.size) {
            for (j in 1..(numbs.size - i)) {
                if (numbs[j - 1] > numbs[j]) {
                    val tmp = numbs[j]
                    numbs[j] = numbs[j - 1]
                    numbs[j - 1] = tmp
                }
            }
        }
    }

    fun quickSort(numbs: IntArray, leftIndex: Int, rightIndex: Int) {
        if (leftIndex > rightIndex) {
            return
        }

        var left = leftIndex
        var right = rightIndex
        var key = numbs[left]

        while (left < right) {

            // 从右往左，找到小于 key 的元素
            while (left < right && numbs[right] >= key) {
                right--
            }
            numbs[left] = numbs[right]

            // 从左往右，找到大于 key 的元素
            while (left < right && numbs[left] <= key) {
                left++
            }
            numbs[right] = numbs[left]
        }

        numbs[left] = key
        quickSort(numbs, leftIndex, left - 1)
        quickSort(numbs, right + 1, rightIndex)
    }
}