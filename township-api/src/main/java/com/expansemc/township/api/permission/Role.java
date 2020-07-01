package com.expansemc.township.api.permission;

import org.spongepowered.api.data.persistence.DataSerializable;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.ResettableBuilder;

public interface Role extends Identifiable, Nameable, PermissionHolder {

    /**
     * Gets the name of the role.
     *
     * @return The name
     */
    @Override
    String getName();

    /**
     * Sets the name of the role.
     *
     * @param name The new name
     */
    void setName(String name);

    /**
     * Gets the priority of the role. Larger priority values take precedence
     * over smaller priority values.
     *
     * @return The priority
     */
    int getPriority();

    /**
     * Sets the priority of the role.
     *
     * @param priority The new priority
     */
    void setPriority(int priority);

    interface Builder<T extends Role, B extends Builder<T, B>> extends ResettableBuilder<T, B> {

        /**
         * Sets the name of the role.
         *
         * @param name The name to use
         * @return This builder, for chaining
         */
        B name(String name);

        /**
         * Sets the priority of the role.
         *
         * @param priority The priority to use
         * @return This builder, for chaining
         */
        B priority(int priority);

        /**
         * Sets the permissions of the role.
         *
         * @param permissions The permissions to use
         * @return This builder, for chaining
         */
        B permissions(Iterable<Permission> permissions);

        /**
         * Sets the permissions of the role.
         *
         * @param permissions The permissions to use
         * @return This builder, for chaining
         */
        B permissions(Permission... permissions);

        T build();
    }
}