package com.expansemc.township.api.nation;

import com.expansemc.township.api.permission.Role;
import org.spongepowered.api.Sponge;

import java.util.UUID;

/**
 * An object which can hold permission data, with {@link Nation} association.
 */
public interface NationRole extends Role {

    /**
     * Creates a new {@link Builder} to build a {@link NationRole}.
     *
     * @return The new builder
     */
    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Builder.class);
    }

    /**
     * Gets the {@link UUID} of the nation this role is associated with.
     *
     * @return The associated nation's id
     */
    UUID getNationId();

    /**
     * Gets the {@link Nation} this role is associated with.
     *
     * @return The associated nation
     */
    Nation getNation();

    interface Builder extends Role.Builder<NationRole, Builder> {

        /**
         * Sets the associated nation of the role.
         *
         * @param nation The nation to set
         * @return This builder, for chaining
         */
        Builder town(Nation nation);
    }
}