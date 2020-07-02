package com.expansemc.township.plugin.registry

import com.expansemc.township.api.permission.Role
import com.expansemc.township.api.registry.type.RoleRegistry

class RoleRegistryImpl<R : Role>(private val visitorRole: R) : AbstractNamedIdentifiableRegistry<R>(), RoleRegistry.Mutable<R> {

    override fun getVisitorRole(): R = this.visitorRole
}