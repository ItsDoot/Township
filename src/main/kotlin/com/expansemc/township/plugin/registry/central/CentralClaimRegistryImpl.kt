package com.expansemc.township.plugin.registry.central

import com.expansemc.township.api.registry.central.CentralClaimRegistry
import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.town.Town
import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import org.spongepowered.api.world.ServerLocation
import org.spongepowered.api.world.server.ServerWorld
import org.spongepowered.math.vector.Vector3i
import java.util.*

class CentralClaimRegistryImpl : CentralClaimRegistry {

    private val claimTable: Table<UUID, Vector3i, Claim> =
        HashBasedTable.create()

    override fun getAll(): Collection<Claim> =
        this.claimTable.values().toSet()

    override fun getAllFor(world: ServerWorld): Collection<Claim> =
        this.claimTable.row(world.uniqueId).values.toList()

    override fun getAllFor(town: Town): Collection<Claim> =
        this.claimTable.values().filter { it.town == town }

    override fun getAllFor(town: Town, world: ServerWorld): Collection<Claim> =
        this.claimTable.row(world.uniqueId).values.filter { it.town == town }

    override fun get(location: ServerLocation): Optional<Claim> =
        Optional.ofNullable(this.claimTable[location.world.uniqueId, location.chunkPosition])

    override fun contains(claim: Claim): Boolean =
        this.claimTable.contains(claim.world.uniqueId, claim.chunkPosition)

    override fun add(claim: Claim): Boolean {
        if (claim in this) return false

        // TODO event

        this.claimTable.put(claim.world.uniqueId, claim.chunkPosition, claim)
        return true
    }

    override fun remove(claim: Claim): Boolean {
        if (claim !in this) return false

        // TODO event

        this.claimTable.remove(claim.world.uniqueId, claim.chunkPosition)
        return true
    }
}