package com.expansemc.township.plugin.registry

import com.expansemc.township.api.permission.Role
import com.expansemc.township.api.permission.RoleArchetype
import com.expansemc.township.api.registry.type.RoleRegistry
import org.spongepowered.api.util.Identifiable

class RoleRegistryImpl<Owner : Identifiable>(private val visitorRole: Role<Owner>) :
    AbstractNamedIdentifiableRegistry.Archetypal<Role<Owner>, RoleArchetype>(),
    RoleRegistry.ArchetypeMutable<Owner> {

    override fun fromArchetype(archetype: RoleArchetype): Role<Owner> {
        TODO()
    }

    override fun getVisitorRole(): Role<Owner> = this.visitorRole
}