package com.expansemc.township.api.permission;

import com.expansemc.township.api.util.NamedIdentifiable;
import com.expansemc.township.api.util.OwnedBy;
import org.spongepowered.api.util.Identifiable;

/**
 * A named permission holder, sorted by priority.
 */
public interface Role<Owner extends Identifiable> extends NamedIdentifiable, PermissionHolder, OwnedBy<Owner> {

    /**
     * Gets the name of this role.
     *
     * @return The name
     */
    @Override
    String getName();

    /**
     * Sets the name of this role.
     *
     * @param name The new name
     */
    void setName(String name);

    /**
     * Gets the priority of this role. Larger priority values take precedence
     * over smaller priority values.
     *
     * @return The priority
     */
    int getPriority();

    /**
     * Sets the priority of this role.
     *
     * @param priority The new priority
     */
    void setPriority(int priority);
}