package com.githhub.haydn0230

import java.io.File

class Day4 {


}

fun main(args:Array<String>) {
    val fileName = System.getenv("DATA_FILE")
    val currentPath = System.getenv("DIRECTORY")

    val passportList = mutableListOf(mutableMapOf<String, String>())
    File("$currentPath/$fileName").bufferedReader().use { br -> br.forEachLine { line ->
        if (line.isEmpty()) {
            passportList.add(mutableMapOf<String, String>())
        } else {
            line.split(" ").forEach { pair ->
                val keyValueList  = pair.split(":")
                passportList.last()[keyValueList.first()] = keyValueList.last()
            }
        }
    }
    }

    println("Number of valid passports: ${passportList.count { it.Parse().checkValid() }}")
}

data class Passport(
    val byr: String?,
    val hcl: String?,
    val eyr: String?,
    val pid: String?,
    val iyr: String?,
    val ecl: String?,
    val hgt: String?,
    val cid: String?,
    ) {
    fun checkValid() =
        when {
            this.hcl.isNullOrEmpty() -> false
            this.byr.isNullOrEmpty() -> false
            this.eyr.isNullOrEmpty() -> false
            this.pid.isNullOrEmpty() -> false
            this.iyr.isNullOrEmpty() -> false
            this.ecl.isNullOrEmpty() -> false
            this.hgt.isNullOrEmpty() -> false
//            this.cid.isNullOrEmpty() && this.mustConditions() -> true
            else -> true
        }

}

fun Map<String, String>.Parse(): Passport {
    return Passport(
        byr = this.get("byr"),
        hcl = this.get("hcl"),
        eyr = this.get("eyr"),
        pid = this.get("pid"),
        iyr = this.get("iyr"),
        ecl = this.get("ecl"),
        hgt = this.get("hgt"),
        cid = this.get("cid")
        )
}




