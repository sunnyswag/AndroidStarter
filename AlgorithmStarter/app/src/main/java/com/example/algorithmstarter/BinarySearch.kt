package com.example.algorithmstarter

object BinarySearch {
    fun basicBinarySearch(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.size - 1

        while (left <= right) {
            val mid = left + (right - left) / 2
            if (nums[mid] > target) {
                right = mid - 1
            } else if (nums[mid] < target) {
                left = mid + 1
            } else {
                return mid
            }
        }

        return -1
    }
}