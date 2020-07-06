package com.expansemc.township.plugin.registry.view

import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.registry.type.WarpRegistry
import com.expansemc.township.api.warp.Warp
import org.spongepowered.api.util.Identifiable
import java.util.*

data class ClaimWarpRegistryView<Owner : Identifiable>(
    private val claim: Claim,
    private val warpRegistry: WarpRegistry<Owner>
) : WarpRegistry<Owner> {

    override fun size(): Int =
        this.warpRegistry.all.asSequence().filter(::sameChunk).count()

    override fun getAll(): Collection<Warp<Owner>> =
        this.warpRegistry.all.filter(::sameChunk)

    override fun contains(element: Warp<Owner>): Boolean =
        element.location.chunkPosition == claim.chunkPosition

    override fun contains(uniqueId: UUID): Boolean =
        this.warpRegistry.get(uniqueId).filter(::sameChunk).isPresent

    override fun contains(name: String): Boolean =
        this.warpRegistry.get(name).filter(::sameChunk).isPresent

    override fun get(uniqueId: UUID): Optional<Warp<Owner>> =
        this.warpRegistry.get(uniqueId).filter(::sameChunk)

    override fun get(name: String): Optional<Warp<Owner>> =
        this.warpRegistry.get(name).filter(::sameChunk)

    private fun sameChunk(warp: Warp<Owner>): Boolean =
        warp.location.chunkPosition == this.claim.chunkPosition
}