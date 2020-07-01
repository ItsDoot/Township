package com.expansemc.township.api.permission;

import org.spongepowered.api.NamedCatalogType;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.NamedCatalogBuilder;
import org.spongepowered.api.util.annotation.CatalogedBy;

import java.util.Optional;

/**
 * Represents a Township permission for towns, nations, claims, etc.
 */
@CatalogedBy(Permissions.class)
public interface Permission extends NamedCatalogType {

    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Builder.class);
    }

    /**
     * Gets the type of this permission.
     *
     * @return The permission type
     * @see PermissionTypes Known permission types
     */
    PermissionType getType();

    /**
     * Gets the description of this permission.
     *
     * @return The description, if available
     */
    Optional<Text> getDescription();

    /**
     * Gets the GUI icon of this permission.
     *
     * @return The icon, if available
     */
    Optional<ItemStack> getIcon();

    interface Builder extends NamedCatalogBuilder<Permission, Builder> {

        /**
         * Sets the type of the permission.
         *
         * @param type The type to use
         * @return This builder, for chaining
         */
        Builder type(PermissionType type);

        /**
         * Sets the description of the permission
         *
         * @param description The description to use
         * @return This builder, for chaining
         */
        Builder description(Text description);

        /**
         * Sets the icon of the permission
         *
         * @param icon The icon to use
         * @return This builder, for chaining
         */
        Builder icon(ItemStack icon);
    }
}