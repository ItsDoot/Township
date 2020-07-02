package com.expansemc.township.plugin.registry.view

import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.registry.type.WarpRegistry
import com.expansemc.township.api.warp.Warp
import java.util.*

data class ClaimWarpRegistryView<W : Warp>(
    private val claim: Claim,
    private val warpRegistry: WarpRegistry<W>
) : WarpRegistry<W> {

    override fun getAll(): Collection<W> =
        this.warpRegistry.all.filter { it.location.chunkPosition == claim.chunkPosition }

    override fun contains(element: W): Boolean =
        element.location.chunkPosition == claim.chunkPosition

    override fun get(uniqueId: UUID): Optional<W> =
        this.warpRegistry.get(uniqueId).filter { it.location.chunkPosition == claim.chunkPosition }

    override fun get(name: String): Optional<W> =
        this.warpRegistry.get(name).filter { it.location.chunkPosition == claim.chunkPosition }
}