package com.expansemc.township.plugin.registry

import com.expansemc.township.api.registry.type.WarpRegistry
import com.expansemc.township.api.warp.Warp
import com.expansemc.township.api.warp.WarpArchetype
import org.spongepowered.api.util.Identifiable

class WarpRegistryImpl<Owner : Identifiable> :
    AbstractNamedIdentifiableRegistry.Archetypal<Warp<Owner>, WarpArchetype>(),
    WarpRegistry.ArchetypeMutable<Owner> {

    override fun fromArchetype(archetype: WarpArchetype): Warp<Owner> {
        TODO()
    }
}