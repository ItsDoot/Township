package com.expansemc.township.plugin.registry

import com.expansemc.township.api.registry.NamedIdentifiableRegistry
import com.expansemc.township.api.registry.Registry
import org.spongepowered.api.util.Identifiable
import org.spongepowered.api.util.Nameable
import java.util.*

@Suppress("UNCHECKED_CAST")
fun <E, R : Registry<E>> emptyRegistry(): R where E : Identifiable, E : Nameable =
    EmptyNamedIdentifiableRegistry as R

private object EmptyNamedIdentifiableRegistry : NamedIdentifiableRegistry<Nothing> {
    override fun getAll(): Collection<Nothing> = emptyList()
    override fun contains(element: Nothing): Boolean = false
    override fun get(uniqueId: UUID): Optional<Nothing> = Optional.empty()
    override fun get(name: String): Optional<Nothing> = Optional.empty()
}