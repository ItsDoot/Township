package com.expansemc.township.api.permission;

import org.spongepowered.api.NamedCatalogType;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.NamedCatalogBuilder;
import org.spongepowered.api.util.annotation.CatalogedBy;

import javax.annotation.Nullable;
import java.util.Optional;

@CatalogedBy(Permissions.class)
public interface Permission extends NamedCatalogType {

    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Builder.class);
    }

    PermissionType getType();

    Optional<Text> getDescription();

    Optional<ItemStack> getIcon();

    interface Builder extends NamedCatalogBuilder<Permission, Builder> {

        Builder type(PermissionType type);

        Builder description(Text description);

        Builder icon(ItemStack icon);
    }
}