package com.expansemc.township.api.permission;

import org.spongepowered.api.CatalogKey;
import org.spongepowered.api.CatalogType;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.util.CatalogBuilder;
import org.spongepowered.api.util.annotation.CatalogedBy;

@CatalogedBy(PermissionTypes.class)
public interface PermissionType extends CatalogType {

    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Builder.class);
    }

    static PermissionType of(CatalogKey key) {
        return builder().key(key).build();
    }

    interface Builder extends CatalogBuilder<PermissionType, Builder> {
    }
}