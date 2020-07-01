package com.expansemc.township.plugin.util

import org.spongepowered.api.data.Key
import org.spongepowered.api.data.value.SetValue
import org.spongepowered.api.data.value.ValueContainer

fun <E> ValueContainer.getSetOrEmpty(key: Key<out SetValue<E>>) =
    this.getOrElse(key, emptySet())