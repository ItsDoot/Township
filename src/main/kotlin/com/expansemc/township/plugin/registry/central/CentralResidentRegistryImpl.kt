package com.expansemc.township.plugin.registry.central

import com.expansemc.township.api.registry.central.CentralResidentRegistry
import com.expansemc.township.api.resident.*
import com.expansemc.township.plugin.registry.ResidentRegistryImpl
import org.spongepowered.api.entity.living.player.User

class CentralResidentRegistryImpl : ResidentRegistryImpl(), CentralResidentRegistry {

    override fun getSystemResident(): VirtualResident {
        TODO()
    }

    override fun getOrCreateUser(user: User?): UserResident {
        TODO()
    }

    override fun getOrCreateVirtual(name: String?): VirtualResident {
        TODO()
    }
}