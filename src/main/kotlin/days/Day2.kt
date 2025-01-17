package com.enisfr.days

import java.io.File

class Day2(private val filePath: String = "src/main/resources/inputs/2/input.txt") {
    private val numbers: List<List<Int>> = File(filePath).readLines().map {
        it.trim().split("\\s+".toRegex()).map(String::toInt)
    }

    fun part1() {
        val safeCount = numbers.count(::isSafe)
        println("Part 1: $safeCount")
    }

    private fun isSafe(level: List<Int>): Boolean {
        if (level.size <= 1) return true

        val increasing = level[0] < level[1]
        val allowedDiffs = if (increasing) setOf(-1, -2, -3) else setOf(1, 2, 3)

        return level.zipWithNext().all { (left, right) -> (left - right) in allowedDiffs }
    }

    fun part2() {
        val unsafeList = numbers.filterNot(::isSafe)
        var safeCount = numbers.size - unsafeList.size

        unsafeList.forEach { level ->
            if (level.indices.any { i -> isSafe(level.filterIndexed { index, _ -> index != i }) }) {
                safeCount++
            }
        }

        println("Part 2: $safeCount")
    }
}