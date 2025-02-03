package com.enisfr.days

import java.io.File
import java.math.BigInteger

class Day3(private val filePath: String = "src/main/resources/inputs/3/input.txt") {
    private val text: String = File(filePath).readLines().joinToString("")
    private val regex = Regex("mul\\((\\d+),(\\d+)\\)")

    fun part1() {
        println("Part 1: ${multSum(text)}")
    }

    fun part2() {
        val filtered = text.split("do()")
            .map { it.substringBefore("don't()") }
            .joinToString(" ")

        println("Part 2: ${multSum(filtered)}")
    }

    private fun multSum(text: String): BigInteger =
        regex.findAll(text)
            .map { matchResult -> matchResult.groupValues.drop(1)
                    .map(String::toBigInteger)
                    .reduce(BigInteger::times) }
            .sumOf { it }
}