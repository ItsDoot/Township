package com.expansemc.township.api.registry;

import com.expansemc.township.api.util.Archetype;
import org.spongepowered.api.util.Identifiable;

import java.util.Optional;
import java.util.UUID;

/**
 * A collection of objects indexed by their {@link UUID}.
 *
 * <p>Note: These IDs may not be globally unique.</p>
 *
 * @param <E> The element type
 */
public interface IdentifiableRegistry<E extends Identifiable> extends Registry<E> {

    /**
     * Gets the element with the specified {@link UUID} that is a member of
     * this collection.
     *
     * @param uniqueId The UUID to get the element from
     * @return The element or empty if not found
     */
    Optional<E> get(UUID uniqueId);

    boolean contains(UUID uniqueId);

    interface Mutable<E extends Identifiable>
            extends IdentifiableRegistry<E>, Registry.Mutable<E> {
    }

    interface ArchetypeMutable<E extends Identifiable, A extends Archetype>
            extends IdentifiableRegistry<E>, Registry.ArchetypeMutable<E, A> {
    }
}