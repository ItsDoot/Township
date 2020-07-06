package com.expansemc.township.plugin.util.extension

import com.google.common.reflect.TypeToken

inline fun <reified T> typeToken(): TypeToken<T> = object : TypeToken<T>() {}