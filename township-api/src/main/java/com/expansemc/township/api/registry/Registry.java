package com.expansemc.township.api.registry;

import java.util.Collection;

public interface Registry<E> {

    /**
     * Gets all elements in this collection.
     *
     * @return All elements in this collection
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
         * Adds the specified element as a member of this collection.
         *
         * @param element The element to add
         * @return True if the action was successful
         */
        boolean add(E element);

        /**
         * Removes the specified element as a member of this collection.
         *
         * @param element The element to remove
         * @return True if the action was successful
         */
        boolean remove(E element);
    }
}