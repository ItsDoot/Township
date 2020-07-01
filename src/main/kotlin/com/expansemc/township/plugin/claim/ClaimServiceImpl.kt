package com.expansemc.township.plugin.claim

import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.claim.ClaimService
import com.expansemc.township.api.town.Town
import com.google.common.collect.HashBasedTable
import com.google.common.collect.Table
import org.spongepowered.api.world.ServerLocation
import org.spongepowered.api.world.server.ServerWorld
import org.spongepowered.math.vector.Vector3i
import java.util.*

class ClaimServiceImpl : ClaimService {

    private val claimTable: Table<ServerWorld, Vector3i, Claim> =
        HashBasedTable.create()

    override fun getClaimAt(location: ServerLocation): Optional<Claim> =
        Optional.ofNullable(this.claimTable[location.world, location.chunkPosition])

    override fun getClaimsByWorld(world: ServerWorld): Collection<Claim> =
        this.claimTable.row(world).values.toList()

    override fun getClaimsByTown(town: Town): Collection<Claim> =
        this.claimTable.values().filter { it.town == town }

    override fun contains(claim: Claim): Boolean =
        this.claimTable.contains(claim.world, claim.chunkPosition)

    override fun register(claim: Claim): Boolean {
        if (claim in this) {
            return false
        }

        this.claimTable.put(claim.world, claim.chunkPosition, claim)
        return true
    }

    override fun unregister(claim: Claim): Boolean {
        if (claim !in this) {
            return false
        }

        this.claimTable.remove(claim.world, claim.chunkPosition)
        return true
    }
}