package com.expansemc.township.plugin.registry.view

import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.registry.type.ResidentRegistry
import com.expansemc.township.api.resident.UserResident
import com.expansemc.township.api.resident.VirtualResident
import com.expansemc.township.plugin.util.extension.unwrap
import com.expansemc.township.plugin.util.extension.wrap
import java.util.*
import kotlin.collections.HashSet

data class NationResidentRegistryView(private val nation: Nation) : ResidentRegistry {

    override fun size(): Int = this.nation.townRegistry.all.fold(0) { acc, town -> acc + town.residentRegistry.size() }

    override fun getAll(): Collection<Resident> =
        nation.townRegistry.all.flatMapTo(HashSet()) { it.residentRegistry.all }

    override fun contains(element: Resident): Boolean =
        nation.townRegistry.all.any { element in it.residentRegistry }

    override fun contains(uniqueId: UUID): Boolean =
        nation.townRegistry.all.any { uniqueId in it.residentRegistry }

    override fun contains(name: String): Boolean =
        nation.townRegistry.all.any { name in it.residentRegistry }

    override fun get(uniqueId: UUID): Optional<Resident> =
        nation.townRegistry.all.asSequence()
            .mapNotNull { it.residentRegistry[uniqueId].unwrap() }
            .firstOrNull()
            .wrap()

    override fun get(name: String): Optional<Resident> =
        nation.townRegistry.all.asSequence()
            .mapNotNull { it.residentRegistry[name].unwrap() }
            .firstOrNull()
            .wrap()

    override fun getAllUsers(): Collection<UserResident> {
        TODO()
    }

    override fun getUser(uniqueId: UUID?): Optional<UserResident> {
        TODO()
    }

    override fun getUser(name: String?): Optional<UserResident> {
        TODO()
    }

    override fun getAllVirtuals(): Collection<VirtualResident> {
        TODO()
    }

    override fun getVirtual(uniqueId: UUID?): Optional<VirtualResident> {
        TODO()
    }

    override fun getVirtual(name: String?): Optional<VirtualResident> {
        TODO()
    }
}