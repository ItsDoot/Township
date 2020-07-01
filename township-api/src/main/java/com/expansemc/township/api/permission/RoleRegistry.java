package com.expansemc.township.api.permission;

import com.expansemc.township.api.util.registry.Registry;
import com.expansemc.township.api.util.registry.NamedIdentifiableRegistry;

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