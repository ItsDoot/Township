package com.expansemc.township.plugin.resident

import com.expansemc.township.api.resident.ResidentRegistry
import org.spongepowered.api.text.channel.MessageChannel
import org.spongepowered.api.text.channel.MessageReceiver

data class ResidentCollectionMessageChannel(private val collection: ResidentRegistry) : MessageChannel {

    override fun getMembers(): Collection<MessageReceiver> =
        this.collection.residents
}