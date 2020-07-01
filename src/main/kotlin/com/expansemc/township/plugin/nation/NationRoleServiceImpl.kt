package com.expansemc.township.plugin.nation

import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.nation.NationRole
import com.expansemc.township.api.nation.NationRoleService
import java.util.*

class NationRoleServiceImpl : NationRoleService {

    override fun getRoles(): MutableCollection<NationRole> {
        TODO("Not yet implemented")
    }

    override fun getRole(uniqueId: UUID?): Optional<NationRole> {
        TODO("Not yet implemented")
    }

    override fun getRolesFor(nation: Nation?): MutableCollection<NationRole> {
        TODO("Not yet implemented")
    }

    override fun getRole(nation: Nation?, name: String?): Optional<NationRole> {
        TODO("Not yet implemented")
    }

    override fun contains(role: NationRole?): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(role: NationRole?): Boolean {
        TODO("Not yet implemented")
    }

    override fun unregister(role: NationRole?): Boolean {
        TODO("Not yet implemented")
    }
}