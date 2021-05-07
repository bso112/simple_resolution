package com.manta.oneline

fun <T> T.ifNotNull(onNotNull : ()->Unit) : T {
    if(this != null)
        onNotNull()
    return this
}

fun <T> T.ifNull(onNull : ()->Unit) : T {
    if(this == null)
        onNull()
    return this
}