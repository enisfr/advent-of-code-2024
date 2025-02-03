package com.enisfr.adventofcode.days

import java.io.File
import kotlin.math.abs

class Day1(private val filePath: String = "src/main/resources/inputs/1/input.txt") {
    private val leftPoints = mutableListOf<Int>()
    private val rightPoints = mutableListOf<Int>()

    private fun parseFile() {
        leftPoints.clear()
        rightPoints.clear()

        File(filePath).useLines { lines ->
            lines.forEach { line ->
                val (left, right) = line.trim().split("\\s+".toRegex()).map { it.toInt() }
                leftPoints.add(left)
                rightPoints.add(right)
            }
        }
    }

    fun part1() {
        parseFile()
        leftPoints.sort()
        rightPoints.sort()

        val distance = leftPoints.indices.sumOf { i ->
            abs(rightPoints[i] - leftPoints[i])
        }

        println("Part 1: $distance")
    }

    fun part2() {
        parseFile()

        val total = leftPoints.sumOf { left ->
            left * rightPoints.count { it == left }
        }

        println("Part 2: $total")
    }
}