package com.expansemc.township.plugin.util.extension

import org.spongepowered.api.text.LiteralText
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColor
import org.spongepowered.api.text.serializer.TextSerializers
import java.util.function.Supplier

fun String.fromAmpersand(): Text =
    TextSerializers.FORMATTING_CODE.get().deserialize(this)

fun String.toText(): LiteralText = Text.of(this)

infix fun String.toText(color: TextColor): LiteralText =
    Text.builder(this).color(color).build()

infix fun String.toText(color: Supplier<TextColor>): LiteralText =
    Text.builder(this).color(color).build()

operator fun Text.plus(other: Text): Text = this.concat(other)

fun Iterable<Text>.joinToText(): Text = Text.join(this)

fun Iterable<Text>.joinToText(separator: Text): Text = Text.joinWith(separator, this)

fun Sequence<Text>.joinToText(): Text = Text.join(Iterable { this.iterator() })

fun Sequence<Text>.joinToText(separator: Text): Text = Text.joinWith(separator, Iterable { this.iterator() })