package com.expansemc.township.plugin.registry

import com.expansemc.township.api.registry.NamedIdentifiableRegistry
import com.expansemc.township.api.util.Archetype
import org.spongepowered.api.util.Identifiable
import org.spongepowered.api.util.Nameable
import java.util.*

abstract class AbstractNamedIdentifiableRegistry<E> :
    NamedIdentifiableRegistry<E>, NamedIdentifiableRegistry.Mutable<E>
        where E : Identifiable, E : Nameable {

    protected val elementsById = HashMap<UUID, E>()
    protected val elementsByName = HashMap<String, E>()

    override fun size(): Int = this.elementsById.size

    override fun getAll(): Collection<E> =
        this.elementsById.values.toSet()

    override fun contains(uniqueId: UUID): Boolean =
        uniqueId in this.elementsById

    override fun contains(name: String): Boolean =
        name in this.elementsByName

    override fun get(uniqueId: UUID): Optional<E> =
        Optional.ofNullable(this.elementsById[uniqueId])

    override fun get(name: String): Optional<E> =
        Optional.ofNullable(this.elementsByName[name])

    override fun contains(element: E): Boolean =
        element.uniqueId in this.elementsById

    override fun register(element: E): Boolean {
        if (element in this) return false

        this.elementsById[element.uniqueId] = element
        this.elementsByName[element.name] = element

        return true
    }

    override fun unregister(element: E): Boolean {
        if (element !in this) return false

        this.elementsById.remove(element.uniqueId)
        this.elementsByName.remove(element.name)

        return true
    }

    abstract class Archetypal<E, A> :
        AbstractNamedIdentifiableRegistry<E>(), NamedIdentifiableRegistry.ArchetypeMutable<E, A>
            where E : Identifiable, E : Nameable, A : Archetype, A : Nameable {

        abstract fun fromArchetype(archetype: A): E

        override fun register(archetype: A): Optional<E> {
            if (archetype.name in this) {
                return Optional.empty()
            }

            val element: E = this.fromArchetype(archetype)

            this.elementsById[element.uniqueId] = element
            this.elementsByName[element.name] = element

            return Optional.of(element)
        }
    }
}