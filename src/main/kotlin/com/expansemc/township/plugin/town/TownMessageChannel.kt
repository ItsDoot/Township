package com.expansemc.township.plugin.town

import com.expansemc.township.api.town.Town
import org.spongepowered.api.text.channel.MessageChannel
import org.spongepowered.api.text.channel.MessageReceiver

data class TownMessageChannel(private val town: Town) : MessageChannel {
    override fun getMembers(): Collection<MessageReceiver> = this.town.residents
}