package com.expansemc.township.plugin.town

import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.claim.ClaimService
import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.town.Town
import com.expansemc.township.api.town.TownService
import java.util.*
import kotlin.collections.HashMap

class TownServiceImpl : TownService {

    private val townsById = HashMap<UUID, Town>()
    private val townsByName = HashMap<String, Town>()

    override fun getTowns(): Collection<Town> =
        this.townsById.values.toSet()

    override fun getTown(uniqueId: UUID): Optional<Town> =
        Optional.ofNullable(this.townsById[uniqueId])

    override fun getTown(name: String): Optional<Town> =
        Optional.ofNullable(this.townsByName[name])

    override fun contains(town: Town): Boolean =
        town.uniqueId in this.townsById

    override fun register(town: Town): Boolean {
        if (town.uniqueId in this.townsById) {
            return false
        }

        this.townsById[town.uniqueId] = town
        this.townsByName[town.name] = town

        for (resident: Resident in town.residents) {
            resident.setTown(town)
        }

        return true
    }

    override fun unregister(town: Town): Boolean {
        if (town.uniqueId !in this.townsById) {
            return false
        }

        this.townsById.remove(town.uniqueId)
        this.townsByName.remove(town.name)

        for (resident: Resident in town.residents) {
            town.removeResident(resident)
        }

        for (claim: Claim in town.claims) {
            ClaimService.getInstance().unregister(claim)
        }

        return true
    }
}