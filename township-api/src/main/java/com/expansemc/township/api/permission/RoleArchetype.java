package com.expansemc.township.api.permission;

import com.expansemc.township.api.util.Archetype;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.ResettableBuilder;

import java.util.Set;

public interface RoleArchetype extends Archetype, Nameable {

    /**
     * Gets the template name of the role.
     *
     * @return The name
     */
    @Override
    String getName();

    /**
     * Gets the template priority of the role. Larger priority values take precedence
     * over smaller priority values.
     *
     * @return The priority
     */
    int getPriority();

    /**
     * Gets the template permissions of the tole.
     *
     * @return The permissions
     */
    Set<Permission> getPermissions();

    /**
     * Represents a builder used to create {@link RoleArchetype}s.
     */
    interface Builder extends ResettableBuilder<RoleArchetype, Builder> {

        /**
         * Sets the template name of the role.
         *
         * @param name The name to use
         * @return This builder, for chaining
         */
        Builder name(String name);

        /**
         * Sets the template priority of the role.
         *
         * @param priority The priority to use
         * @return This builder, for chaining
         */
        Builder priority(int priority);

        /**
         * Sets the template permissions of the role.
         *
         * @param permissions The permissions to use
         * @return This builder, for chaining
         */
        Builder permissions(Iterable<Permission> permissions);

        /**
         * Sets the template permissions of the role.
         *
         * @param permissions The permissions to use
         * @return This builder, for chaining
         */
        Builder permissions(Permission... permissions);

        /**
         * Builds the {@link RoleArchetype}.
         *
         * @return The built role template
         * @throws IllegalStateException If not all required options were specified
         */
        RoleArchetype build();
    }
}