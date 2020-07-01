package com.expansemc.township.api.permission;

import org.spongepowered.api.util.Tristate;

import java.util.Collection;

public interface PermissionHolder {

    Collection<Permission> getPermissions();

    boolean hasPermission(Permission permission);

    boolean addPermission(Permission permission);

    boolean removePermission(Permission permission);

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

    default boolean hasPermission(Permission permission, PermissionOverrideHolder override) {
        return getPermissionValue(permission, override).asBoolean();
    }
}