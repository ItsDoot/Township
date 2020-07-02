package com.expansemc.township.api.registry.type;

import com.expansemc.township.api.permission.Role;
import com.expansemc.township.api.registry.NamedIdentifiableRegistry;
import com.expansemc.township.api.registry.Registry;

/**
 * An object which manages roles of a certain type (town, nation, etc).
 *
 * @param <R> The role type
 */
public interface RoleRegistry<R extends Role> extends NamedIdentifiableRegistry<R> {

    /**
     * The base role that all residents are affected by.
     *
     * @return The base role
     */
    R getVisitorRole();

    interface Mutable<R extends Role> extends RoleRegistry<R>, Registry.Mutable<R> {
    }
}