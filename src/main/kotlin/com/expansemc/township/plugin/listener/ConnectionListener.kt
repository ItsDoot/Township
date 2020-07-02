package com.expansemc.township.plugin.listener

import com.expansemc.township.api.TownshipAPI
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.network.ClientConnectionEvent

class ConnectionListener {

    @Listener
    fun onJoin(event: ClientConnectionEvent.Join) {
        TownshipAPI.getInstance().residentRegistry.getOrCreateUser(event.player.user)
    }
}