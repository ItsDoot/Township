package com.expansemc.township.api.warp;

import com.expansemc.township.api.util.NamedIdentifiable;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.ServerLocation;

/**
 * A named teleport location.
 */
public interface Warp extends NamedIdentifiable {

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

    interface Builder<T extends Warp, B extends Builder<T, B>> extends ResettableBuilder<T, B> {

        /**
         * Sets the name of the warp.
         *
         * @param name The name to use
         * @return This builder, for chaining
         */
        B name(String name);

        /**
         * Sets the location of the warp.
         *
         * @param location The location to use
         * @return This builder, for chaining
         */
        B location(ServerLocation location);

        /**
         * Builds the {@link Warp} of type {@link T}.
         *
         * @return The built warp
         * @throws IllegalStateException If not all required options were specified
         */
        T build();
    }
}