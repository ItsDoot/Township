package com.expansemc.township.plugin.registry

import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.registry.type.ResidentRegistry
import com.expansemc.township.api.resident.ResidentArchetype
import com.expansemc.township.api.resident.UserResident
import com.expansemc.township.api.resident.VirtualResident
import com.expansemc.township.plugin.util.Constants.VIRTUAL_RESIDENT_NAME_PREFIX
import java.util.*

open class ResidentRegistryImpl :
    AbstractNamedIdentifiableRegistry.Archetypal<Resident, ResidentArchetype>(),
    ResidentRegistry.Mutable, ResidentRegistry.ArchetypeMutable {

    override fun fromArchetype(archetype: ResidentArchetype): Resident {
        TODO()
    }

    override fun getAllUsers(): Collection<UserResident> =
        this.elementsById.values.filterIsInstance<UserResident>()

    override fun getUser(uniqueId: UUID): Optional<UserResident> =
        Optional.ofNullable(this.elementsById[uniqueId] as? UserResident)

    override fun getUser(name: String): Optional<UserResident> =
        Optional.ofNullable(this.elementsByName[name] as? UserResident)

    override fun getAllVirtuals(): Collection<VirtualResident> =
        this.elementsById.values.filterIsInstance<VirtualResident>()

    override fun getVirtual(uniqueId: UUID): Optional<VirtualResident> =
        Optional.ofNullable(this.elementsById[uniqueId] as? VirtualResident)

    override fun getVirtual(name: String): Optional<VirtualResident> {
        val prefixedName: String = if (name.startsWith(VIRTUAL_RESIDENT_NAME_PREFIX)) name else VIRTUAL_RESIDENT_NAME_PREFIX + name
        return Optional.ofNullable(this.elementsByName[prefixedName] as? VirtualResident)
    }
}