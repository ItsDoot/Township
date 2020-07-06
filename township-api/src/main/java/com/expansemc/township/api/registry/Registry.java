package com.expansemc.township.api.registry;

import com.expansemc.township.api.util.Archetype;

import java.util.Collection;
import java.util.Optional;

public interface Registry<E> {

    /**
     * Gets the number of elements in this collection.
     *
     * @return The number of elements
     */
    int size();

    /**
     * Gets all elements in this collection.
     *
     * @return All elements
     */
    Collection<E> getAll();

    /**
     * Checks if the specified element is a member of this collection.
     *
     * @param element The element to check
     * @return True if the element is a member
     */
    boolean contains(E element);

    interface Mutable<E> extends Registry<E> {

        /**
         * Registers the provided element to this collection.
         *
         * @param element The element to register
         * @return True if the element was successfully added, false otherwise
         */
        boolean register(E element);

        /**
         * Unregisters the provided element from this collection.
         *
         * @param element The element to unregister
         * @return True if the element was successfully removed, false otherwise
         */
        boolean unregister(E element);
    }

    interface ArchetypeMutable<E, A extends Archetype> extends Registry<E> {

        /**
         * Registers the provided template as a member of this collection.
         *
         * @param archetype The template to register with
         * @return The full element, if available
         */
        Optional<E> register(A archetype);

        /**
         * Unregisters the provided element from this collection.
         *
         * @param element The element to unregister
         * @return True if the element was successfully removed, false otherwise
         */
        boolean unregister(E element);
    }
}