package com.expansemc.township.api.util.registry;

import org.spongepowered.api.util.Nameable;

import java.util.Optional;

/**
 * A collection of objects indexed by their name.
 *
 * @param <E> The element type
 */
public interface NameableRegistry<E extends Nameable>
        extends Registry<E> {

    /**
     * Gets the element with the specified name that is a member of this
     * collection.
     *
     * @param name The name to get the element from
     * @return The element or empty if not found
     */
    Optional<E> get(String name);

    interface Mutable<E extends Nameable>
            extends NameableRegistry<E>, Registry.Mutable<E> {
    }
}