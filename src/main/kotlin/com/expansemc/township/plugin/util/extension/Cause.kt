package com.expansemc.township.plugin.util.extension

import org.spongepowered.api.event.cause.Cause

inline fun <reified T : Any> Cause.first(): T? =
    this.first(T::class.java).unwrap()

inline fun <reified T : Any> Cause.last(): T? =
    this.last(T::class.java).unwrap()