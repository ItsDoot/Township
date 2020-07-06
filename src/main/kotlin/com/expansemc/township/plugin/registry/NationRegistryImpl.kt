package com.expansemc.township.plugin.registry

import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.nation.NationArchetype
import com.expansemc.township.api.registry.type.NationRegistry

open class NationRegistryImpl :
    AbstractNamedIdentifiableRegistry.Archetypal<Nation, NationArchetype>(),
    NationRegistry.ArchetypeMutable {

    override fun fromArchetype(archetype: NationArchetype): Nation {
        TODO()
    }
}