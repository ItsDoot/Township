package com.expansemc.township.api.registry;

import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;

public interface NamedIdentifiableRegistry<E extends Identifiable & Nameable> extends IdentifiableRegistry<E>, NameableRegistry<E> {

    interface Mutable<E extends Identifiable & Nameable> extends NamedIdentifiableRegistry<E>, Registry.Mutable<E> {
    }
}