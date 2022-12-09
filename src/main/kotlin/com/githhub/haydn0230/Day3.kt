package com.githhub.haydn0230

import java.io.File

//enum class TobogganResult { TREE, NO_TREE, END_OF_MAP, UNKNOWN }
//
//class Toboggan(val data: List<String>) {
//    fun chartCourse(down: Int, along: Int): Int {
//
//
//
//
////        for (y in data.indices) {
////            val x = y * 3 % width
////            if (data[y][x] == '#') trees++
////        }
////
////        return trees
//        val width = data[0].length
//        var currentDown = down
//        var currentAlong = along
//        var treeCounter = 0
//        while (data.size >= currentDown) {
//            when (checkIfTree(currentAlong, currentDown)) {
//                TobogganResult.TREE -> treeCounter++
//                TobogganResult.END_OF_MAP -> break
//                TobogganResult.UNKNOWN -> break
//            }
//            currentDown += down
//            currentAlong += along
//            currentAlong = if (currentAlong >= width) currentAlong - 31 else currentAlong
//            println(currentAlong)
//        }
//        return treeCounter
//    }
//
//    private fun checkIfTree(along: Int, down: Int): TobogganResult {
//        if (down >= data.size) return TobogganResult.END_OF_MAP
//        if (data[down] != null) {
//            val line = data[down]
//                ?.trim()
//                ?.split("")
//                ?.filter { it.isNotEmpty() }
//            if (line == null) return TobogganResult.UNKNOWN
//            if (line.size <= along) return TobogganResult.UNKNOWN
//            val character = line.get(along)
//
//            var newMap = data
////            val line = newMap[down]?.substring(0, along) + "O" + newMap[down]?.substring(along +1 )
//            return when (character) {
//                "#" -> {
//                    println(
//                        "${data[down]} -> ${
//                            newMap[down]?.substring(
//                                0,
//                                along
//                            ) + "X" + newMap[down]?.substring(along + 1)
//                        }"
//                    )
//                    TobogganResult.TREE
//                }
//
//                "." -> {
//                    println(
//                        "${data[down]} -> ${
//                            newMap[down]?.substring(
//                                0,
//                                along
//                            ) + "O" + newMap[down]?.substring(along + 1)
//                        }"
//                    )
//                    TobogganResult.NO_TREE
//                }
//
//                else -> TobogganResult.UNKNOWN
//            }
//        }
//        return TobogganResult.UNKNOWN
//    }
//}


fun main(args: Array<String>) {
    val fileName = System.getenv("DATA_FILE")
    val currentPath = System.getenv("DIRECTORY")

    var data = mutableListOf<String>()
    File("$currentPath/$fileName").bufferedReader().use { br ->
        br.forEachLine { line ->
            data.add(line)
        }
    }
    val slopes = arrayListOf(
        Pair(1) { y -> y },
        Pair(1) { y -> y * 3 },
        Pair(1) { y -> y * 5 },
        Pair(1) { y -> y * 7 },
        Pair(2) { y -> y / 2 },
    )

//    println(TobogganSlope(data).PartOne())
    val trees = mutableListOf<Long>()
    for (coord in slopes) {
        trees.add(TobogganSlope(data).PartTwo(coord).toLong())
    }

    println(trees.reduce { acc, v -> acc * v })
}


class TobogganSlope(val fields: List<String>) {
    fun PartOne(): Int {
        val width = fields[0].length
        return fields.indices.count { y ->
            val x = y * 3 % width
            val character = fields[y][y * 3 % width]
            if (character == '#') {
                println(fields[y]?.substring(0, x) + "X" + fields[y]?.substring(x + 1))
                true
            } else {
                println(fields[y]?.substring(0, x) + "O" + fields[y]?.substring(x + 1))
                false
            }
        }
    }

    fun PartTwo(pair: Pair): Int {
        val width = fields[0].length
        return fields.indices.count { y ->
            // if y is 2 down then skip every other y
            if (y % pair.y == 0) fields[y][pair.calculateX(y) % width] == '#' else false
        }
    }
}

data class Pair(
    val y: Int,
    val calculateX: (y: Int) -> Int
)