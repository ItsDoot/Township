package com.expansemc.township.plugin.util.extension

import com.expansemc.township.api.TownshipAPI
import com.expansemc.township.api.resident.Resident
import org.spongepowered.api.entity.living.player.User

fun User.toResident(): Resident =
    TownshipAPI.getInstance().residentRegistry.getOrCreateUser(this)