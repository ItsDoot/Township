package com.expansemc.township.plugin.registry

import com.expansemc.township.api.town.Town
import com.expansemc.township.api.registry.type.TownRegistry
import com.expansemc.township.api.town.TownArchetype

open class TownRegistryImpl :
    AbstractNamedIdentifiableRegistry.Archetypal<Town, TownArchetype>(),
    TownRegistry.ArchetypeMutable {

    override fun fromArchetype(archetype: TownArchetype): Town {
        TODO()
    }
}