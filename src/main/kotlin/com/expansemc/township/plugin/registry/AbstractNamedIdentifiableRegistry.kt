package com.expansemc.township.plugin.registry

import com.expansemc.township.api.registry.NamedIdentifiableRegistry
import org.spongepowered.api.util.Identifiable
import org.spongepowered.api.util.Nameable
import java.util.*

abstract class AbstractNamedIdentifiableRegistry<E> : NamedIdentifiableRegistry.Mutable<E>
        where E : Identifiable, E : Nameable {

    protected val elementsById = HashMap<UUID, E>()
    protected val elementsByName = HashMap<String, E>()

    override fun getAll(): Collection<E> =
        this.elementsById.values.toSet()

    override fun get(uniqueId: UUID): Optional<E> =
        Optional.ofNullable(this.elementsById[uniqueId])

    override fun get(name: String): Optional<E> =
        Optional.ofNullable(this.elementsByName[name])

    override fun contains(element: E): Boolean =
        element.uniqueId in this.elementsById

    override fun add(element: E): Boolean {
        if (element in this) return false

        this.elementsById[element.uniqueId] = element
        this.elementsByName[element.name] = element

        return true
    }

    override fun remove(element: E): Boolean {
        if (element !in this) return false

        this.elementsById.remove(element.uniqueId)
        this.elementsByName.remove(element.name)

        return true
    }
}