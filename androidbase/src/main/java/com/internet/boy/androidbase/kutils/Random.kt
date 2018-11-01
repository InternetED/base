package com.internet.boy.androidbase.kutils

import java.util.*


val random: Random by lazy { Random() }

fun List<Any>.randomElement():String = if (this.isEmpty()) "" else this[random.nextInt(this.size)].toString()

fun Array<Any>.randomElement():String = if (this.isEmpty()) "" else this[random.nextInt(this.size)].toString()



