package com.expansemc.township.api.permission;

import org.spongepowered.api.util.Tristate;

import java.util.Collection;

/**
 * An object which holds Township permission data.
 *
 * <p>Note: These are not normal x.y.z permissions.</p>
 */
public interface PermissionHolder {

    /**
     * Gets the permissions that this holder is granted.
     *
     * @return All granted permissions
     */
    Collection<Permission> getPermissions();

    /**
     * Checks if this holder is granted the given permission.
     *
     * @param permission The permission to check
     * @return True if granted the permission
     */
    boolean hasPermission(Permission permission);

    /**
     * Adds the given permission to this holder.
     *
     * @param permission The permission to add
     * @return True if the permission was granted successfully
     */
    boolean addPermission(Permission permission);

    /**
     * Removes the given permission from this holder.
     *
     * @param permission The permission to remove
     * @return True if the permission was removed successfully
     */
    boolean removePermission(Permission permission);

    /**
     * Gets the tristate value that represents whether this holder has the
     * given permission, based on the given override holder.
     *
     * @param permission The permission to check
     * @param override The override holder to use
     * @return The tristate value
     */
    default Tristate getPermissionValue(Permission permission, PermissionOverrideHolder override) {
        Tristate overrideState = override.getPermissionOverride(this, permission);

        if (overrideState != Tristate.UNDEFINED) {
            // Override is set.
            return overrideState;
        } else {
            // Override is not set.
            if (this.hasPermission(permission)) {
                return Tristate.TRUE;
            } else {
                return Tristate.UNDEFINED;
            }
        }
    }

    /**
     * Checks whether this holder has the given permission, based on the given
     * override holder.
     *
     * @param permission The permission to check
     * @param override The override holder to use
     * @return True if this holder is granted permission
     */
    default boolean hasPermission(Permission permission, PermissionOverrideHolder override) {
        return getPermissionValue(permission, override).asBoolean();
    }
}