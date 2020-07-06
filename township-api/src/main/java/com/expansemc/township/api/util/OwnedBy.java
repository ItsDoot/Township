package com.expansemc.township.api.util;

import org.spongepowered.api.util.Identifiable;

import java.util.UUID;

/**
 * Represents an object that is owned by another object.
 *
 * @param <Owner> The owner type
 */
public interface OwnedBy<Owner extends Identifiable> {

    /**
     * Gets the owner of this object.
     *
     * @return The object owner
     */
    Owner getOwner();

    /**
     * Checks whether the provided object is the owner of this object.
     *
     * @param object The object to check
     * @return True if the object is this object's owner, false otherwise
     */
    boolean isOwner(Owner object);

    interface Mutable<Owner extends Identifiable> extends OwnedBy<Owner> {

        /**
         * Sets the owner of this object.
         *
         * @param object The new owner
         */
        void setOwner(Owner object);
    }
}