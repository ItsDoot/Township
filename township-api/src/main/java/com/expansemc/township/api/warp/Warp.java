package com.expansemc.township.api.warp;

import com.expansemc.township.api.util.NamedIdentifiable;
import com.expansemc.township.api.util.OwnedBy;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.ServerLocation;

/**
 * A named teleport location.
 */
public interface Warp<Owner extends Identifiable> extends NamedIdentifiable, OwnedBy<Owner> {

    /**
     * Gets the name of this warp.
     *
     * @return The name
     */
    @Override
    String getName();

    /**
     * Sets the name of this warp.
     *
     * @param name The new name
     */
    void setName(String name);

    /**
     * Gets the location where players are warped to.
     *
     * @return The warp location
     */
    ServerLocation getLocation();

    /**
     * Sets the location where players are warped to.
     *
     * @param location The new warp location
     */
    void setLocation(ServerLocation location);
}