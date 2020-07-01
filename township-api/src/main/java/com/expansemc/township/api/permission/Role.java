package com.expansemc.township.api.permission;

import org.spongepowered.api.data.persistence.DataSerializable;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.ResettableBuilder;

public interface Role extends Identifiable, DataSerializable, PermissionHolder {

    String getName();

    void setName(String name);

    int getPriority();

    void setPriority(int priority);

    interface Builder<T extends Role, B extends Builder<T, B>> extends ResettableBuilder<T, B> {

        /**
         * Sets the name of the role.
         *
         * @param name The name to set
         * @return This builder, for chaining
         */
        B name(String name);

        /**
         * Sets the priority of the role.
         *
         * @param priority The priority to set
         * @return This builder, for chaining
         */
        B priority(int priority);

        /**
         * Sets the permissions of the role.
         *
         * @param permissions The permissions to set
         * @return This builder, for chaining
         */
        B permissions(Iterable<Permission> permissions);

        /**
         * Sets the permissions of the role.
         *
         * @param permissions The permissions to set
         * @return This builder, for chaining
         */
        B permissions(Permission... permissions);

        T build();
    }
}