package com.expansemc.township.api.util;

import org.spongepowered.api.util.Identifiable;

/**
 * Represents an object that is owned by another object.
 *
 * @param <O> The owner type
 */
public interface OwnedBy<O extends Identifiable> {

    /**
     * Gets the owner of this object.
     *
     * @return The object owner
     */
    O getOwner();

    /**
     * Checks whether the provided object is the owner of this object.
     *
     * @param object The object to check
     * @return True if the object is this object's owner, false otherwise
     */
    boolean isOwner(O object);

    /**
     * Sets the owner of this object.
     *
     * @param object The new owner
     */
    void setOwner(O object);
}