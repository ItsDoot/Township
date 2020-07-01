package com.expansemc.township.api.town;

import com.expansemc.township.api.permission.Role;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * An object which can hold permission data, with {@link Town} association.
 */
public interface TownRole extends Role {

    /**
     * Creates a new {@link Builder} to build a {@link TownRole}.
     *
     * @return The new builder
     */
    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Builder.class);
    }

    /**
     * Gets the {@link UUID} of the town this role is associated with.
     *
     * @return The associated town's id
     */
    UUID getTownId();

    /**
     * Gets the {@link Town} this role is associated with.
     *
     * @return The associated town
     */
    Town getTown();

    interface Builder extends Role.Builder<TownRole, Builder> {

        /**
         * Sets the associated town of the role.
         *
         * @param town The town to use
         * @return This builder, for chaining
         */
        Builder town(Town town);
    }
}