package com.expansemc.township.plugin.listener

import com.expansemc.township.api.resident.ResidentService
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.network.ClientConnectionEvent

class ConnectionListener {

    @Listener
    fun onJoin(event: ClientConnectionEvent.Join) {
        ResidentService.getInstance().getOrCreateUserResident(event.player.user)
    }
}