package com.expansemc.township.api.warp;

import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.ServerLocation;

/**
 * A named teleport location.
 */
public interface Warp extends Identifiable, Nameable {

    /**
     * Gets the name of the warp.
     *
     * @return The name
     */
    @Override
    String getName();

    /**
     * Sets the name of the warp.
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

    interface Builder<T extends Warp, B extends Builder<T, B>> extends ResettableBuilder<T, B> {

        B name(String name);

        B location(ServerLocation location);

        T build();
    }
}