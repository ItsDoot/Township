package com.expansemc.township.api.permission;

import org.spongepowered.api.util.Tristate;

import java.util.Map;

/**
 * An object which holds overrides of Township permission data.
 *
 * @see Permissions Known permissions
 */
public interface PermissionOverrideHolder {

    /**
     * Gets all permission overrides for the given permission holder.
     *
     * @param holder The permission holder to get overrides from
     * @return The map of permission overrides
     * @throws IllegalArgumentException If the given holder is not supported
     */
    Map<Permission, Boolean> getPermissionOverrides(PermissionHolder holder);

    /**
     * Gets the tristate representation of the override for the given
     * permission holder and permission.
     *
     * @param holder The permission holder to get overrides from
     * @param permission The permission to get the override from
     * @return The tristate representation
     * @throws IllegalArgumentException If the given holder or permission is not supported
     */
    Tristate getPermissionOverride(PermissionHolder holder, Permission permission);

    /**
     * Sets the override value for the given permission holder and permission.
     *
     * @param holder The permission holder to set overrides for
     * @param permission The permission to override
     * @param value The overridden value
     * @throws IllegalArgumentException If the given holder or permission is not supported
     */
    void setPermissionOverride(PermissionHolder holder, Permission permission, boolean value);

    /**
     * Removes the permission override for the given permission holder and
     * permission.
     *
     * @param holder The permission holder to remove overrides for
     * @param permission The permission to remove
     * @return True if the permission was previously overridden, false otherwise
     * @throws IllegalArgumentException If the given holder or permission is not supported
     */
    boolean removePermissionOverride(PermissionHolder holder, Permission permission);
}