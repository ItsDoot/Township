package com.expansemc.township.plugin.util.registry

import com.expansemc.township.api.registry.Registry
import org.spongepowered.api.text.channel.MessageChannel
import org.spongepowered.api.text.channel.MessageReceiver

data class RegistryMessageChannel<E : MessageReceiver>(private val registry: Registry<E>): MessageChannel {
    override fun getMembers(): Collection<MessageReceiver> = registry.all
}