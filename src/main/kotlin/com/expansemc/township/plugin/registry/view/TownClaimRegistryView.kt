package com.expansemc.township.plugin.registry.view

import com.expansemc.township.api.registry.central.CentralClaimRegistry
import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.registry.type.ClaimRegistry
import com.expansemc.township.api.town.Town
import org.spongepowered.api.world.ServerLocation
import org.spongepowered.api.world.server.ServerWorld
import java.util.*

data class TownClaimRegistryView(
    private val central: CentralClaimRegistry,
    private val town: Town
) : ClaimRegistry {

    override fun getAll(): Collection<Claim> =
        this.central.getAllFor(this.town)

    override fun contains(element: Claim): Boolean =
        element.town == this.town && element in central

    override fun getAllFor(world: ServerWorld): Collection<Claim> =
        this.central.getAllFor(this.town, world)

    override fun get(location: ServerLocation): Optional<Claim> =
        central.get(location).filter { it.town == this.town }
}