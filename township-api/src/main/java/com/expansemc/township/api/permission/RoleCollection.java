package com.expansemc.township.api.permission;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * An object which manages roles of a certain type (town, nation, etc).
 *
 * @param <R> The role type
 */
public interface RoleCollection<R extends Role> {

    /**
     * The base role that all residents are affected by.
     *
     * @return The base role
     */
    R getVisitorRole();

    /**
     * Gets all roles registered to this collection.
     *
     * @return All registered roles
     */
    Collection<R> getRoles();

    /**
     * Gets the role with the specified {@link UUID} that's registered to this
     * collection, if available.
     *
     * @param uniqueId The unique id to find
     * @return The role, if available
     */
    Optional<R> getRole(UUID uniqueId);

    /**
     * Gets the role with the specified name that's registered to this
     * collection, if available.
     *
     * @param name The name to find
     * @return The role, if available
     */
    Optional<R> getRole(String name);

    /**
     * Checks if the specified role is registered to this collection.
     *
     * @param role The role to check
     * @return True if the role is registered
     */
    boolean hasRole(R role);

    /**
     * Registers the specified role to this collection.
     *
     * @param role The role to register
     * @return True if the registration was successful
     */
    boolean addRole(R role);

    /**
     * Unregisters the specified role to this collection.
     *
     * @param role The role to unregister
     * @return True if the removal was successful
     */
    boolean removeRole(R role);
}