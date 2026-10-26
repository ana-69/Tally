package com.ana.tally

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform