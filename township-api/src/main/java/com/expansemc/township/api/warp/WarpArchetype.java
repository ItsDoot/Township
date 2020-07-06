package com.expansemc.township.api.warp;

import com.expansemc.township.api.util.Archetype;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.ServerLocation;

/**
 * A template/unregistered {@link Warp}.
 */
public interface WarpArchetype extends Archetype, Nameable {

    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Builder.class);
    }

    static WarpArchetype of(String name, ServerLocation location) {
        return builder().name(name).location(location).build();
    }

    /**
     * Gets the template name of this warp.
     *
     * @return The name
     */
    @Override
    String getName();

    /**
     * Gets the template location where players are warped to.
     *
     * @return The warp location
     */
    ServerLocation getLocation();

    /**
     * Represents a builder used to create {@link WarpArchetype}s.
     */
    interface Builder extends ResettableBuilder<WarpArchetype, Builder> {

        /**
         * Sets the template name of the warp.
         *
         * @param name The name to use
         * @return This builder, for chaining
         */
        Builder name(String name);

        /**
         * Sets the template location of the warp.
         *
         * @param location The location to use
         * @return This builder, for chaining
         */
        Builder location(ServerLocation location);

        /**
         * Builds the {@link WarpArchetype}.
         *
         * @return The built warp template
         * @throws IllegalStateException If not all required options were specified
         */
        WarpArchetype build();
    }
}