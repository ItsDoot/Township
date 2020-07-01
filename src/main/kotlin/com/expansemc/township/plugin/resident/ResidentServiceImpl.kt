package com.expansemc.township.plugin.resident

import com.expansemc.township.api.resident.ResidentService
import com.expansemc.township.api.resident.UserResident
import com.expansemc.township.api.resident.VirtualResident
import org.spongepowered.api.entity.living.player.User
import java.util.*

class ResidentServiceImpl : ResidentService {

    override fun getUserResidents(): MutableCollection<UserResident> {
        TODO("Not yet implemented")
    }

    override fun getUserResident(uniqueId: UUID?): Optional<UserResident> {
        TODO("Not yet implemented")
    }

    override fun getUserResident(name: String?): Optional<UserResident> {
        TODO("Not yet implemented")
    }

    override fun getOrCreateUserResident(user: User?): UserResident {
        TODO("Not yet implemented")
    }

    override fun getVirtualResidents(): MutableCollection<VirtualResident> {
        TODO("Not yet implemented")
    }

    override fun getVirtualResident(uniqueId: UUID?): Optional<VirtualResident> {
        TODO("Not yet implemented")
    }

    override fun getVirtualResident(name: String?): Optional<VirtualResident> {
        TODO("Not yet implemented")
    }

    override fun getOrCreateVirtualResident(name: String?): VirtualResident {
        TODO("Not yet implemented")
    }
}