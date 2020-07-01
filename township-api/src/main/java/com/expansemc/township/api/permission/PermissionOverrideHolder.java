package com.expansemc.township.api.permission;

import org.spongepowered.api.util.Tristate;

import java.util.Map;

public interface PermissionOverrideHolder {

    Map<Permission, Boolean> getPermissionOverrides(PermissionHolder holder);

    Tristate getPermissionOverride(PermissionHolder holder, Permission permission);

    boolean setPermissionOverride(PermissionHolder holder, Permission permission, boolean value);

    boolean removePermissionOverride(PermissionHolder holder, Permission permission);
}