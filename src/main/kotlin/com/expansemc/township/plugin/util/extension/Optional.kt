package com.expansemc.township.plugin.util.extension

import java.util.*

fun <T> Optional<T>.unwrap(): T? = this.orElse(null)

fun <T> T?.wrap(): Optional<T> = Optional.ofNullable(this)

inline fun <T : Any> Optional<T>.getOrElse(value: () -> T): T =
    if (this.isPresent) this.get() else value()

@Suppress("UNCHECKED_CAST")
inline fun <reified R> Optional<Any?>.filterInstance(): Optional<R> =
    this.filter { it is R } as Optional<R>