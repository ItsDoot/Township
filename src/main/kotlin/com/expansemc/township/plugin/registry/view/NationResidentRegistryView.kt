package com.expansemc.township.plugin.registry.view

import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.registry.type.ResidentRegistry
import com.expansemc.township.api.resident.UserResident
import com.expansemc.township.api.resident.VirtualResident
import com.expansemc.township.plugin.util.unwrap
import com.expansemc.township.plugin.util.wrap
import java.util.*
import kotlin.collections.HashSet

data class NationResidentRegistryView(private val nation: Nation) : ResidentRegistry {

    override fun getAll(): Collection<Resident> =
        nation.townRegistry.all.flatMapTo(HashSet()) { it.residentRegistry.all }

    override fun contains(element: Resident): Boolean =
        nation.townRegistry.all.any { element in it.residentRegistry }

    override fun get(uniqueId: UUID): Optional<Resident> =
        nation.townRegistry.all.asSequence()
            .mapNotNull { it.residentRegistry[uniqueId].unwrap() }
            .firstOrNull()
            .wrap()

    override fun getAllUsers(): MutableCollection<UserResident> {
        TODO()
    }

    override fun getUser(uniqueId: UUID?): Optional<UserResident> {
        TODO()
    }

    override fun getUser(name: String?): Optional<UserResident> {
        TODO()
    }

    override fun getAllVirtuals(): MutableCollection<VirtualResident> {
        TODO()
    }

    override fun getVirtual(uniqueId: UUID?): Optional<VirtualResident> {
        TODO()
    }

    override fun getVirtual(name: String?): Optional<VirtualResident> {
        TODO()
    }
}