package com.expansemc.township.api.registry;

import com.expansemc.township.api.util.Archetype;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;

public interface NamedIdentifiableRegistry<E extends Identifiable & Nameable> extends IdentifiableRegistry<E>, NameableRegistry<E> {

    interface Mutable<E extends Identifiable & Nameable>
            extends NamedIdentifiableRegistry<E>, IdentifiableRegistry.Mutable<E>, NameableRegistry.Mutable<E> {
    }

    interface ArchetypeMutable<E extends Identifiable & Nameable, A extends Archetype & Nameable>
            extends NamedIdentifiableRegistry<E>, IdentifiableRegistry.ArchetypeMutable<E, A>, Registry.ArchetypeMutable<E, A> {
    }
}