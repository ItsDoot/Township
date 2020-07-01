package com.expansemc.township.plugin.town

import com.expansemc.township.api.town.Town
import com.expansemc.township.api.town.TownRole
import com.expansemc.township.api.town.TownRoleService
import java.util.*

class TownRoleServiceImpl : TownRoleService {

    override fun getRoles(): MutableCollection<TownRole> {
        TODO("Not yet implemented")
    }

    override fun getRole(uniqueId: UUID?): Optional<TownRole> {
        TODO("Not yet implemented")
    }

    override fun getRolesByTown(town: Town?): MutableCollection<TownRole> {
        TODO("Not yet implemented")
    }

    override fun getRole(town: Town?, name: String?): Optional<TownRole> {
        TODO("Not yet implemented")
    }

    override fun contains(role: TownRole?): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(role: TownRole?): Boolean {
        TODO("Not yet implemented")
    }

    override fun unregister(role: TownRole?): Boolean {
        TODO("Not yet implemented")
    }
}