package com.expansemc.township.api.permission;

import org.spongepowered.api.Sponge;

import java.util.function.Supplier;

public final class PermissionTypes {

    public static final Supplier<PermissionType> CLAIM =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(PermissionType.class, "claim");

    public static final Supplier<PermissionType> NATION =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(PermissionType.class, "nation");

    public static final Supplier<PermissionType> TOWN =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(PermissionType.class, "town");

    private PermissionTypes() {
        throw new AssertionError("Don't instantiate me!");
    }
}