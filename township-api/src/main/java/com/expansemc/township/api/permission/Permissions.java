package com.expansemc.township.api.permission;

import org.spongepowered.api.Sponge;

import java.util.function.Supplier;

/**
 * An enumeration of known {@link Permission}s used throughout the plugin.
 */
public final class Permissions {

    /**
     * Allow players to break blocks in the claim.
     */
    public static final Supplier<Permission> CLAIM_BLOCK_BREAK =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "claim_block_break");

    /**
     * Allows players to modify blocks in the claim.
     */
    public static final Supplier<Permission> CLAIM_BLOCK_MODIFY =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "claim_block_modify");

    /**
     * Allows players to place blocks in the claim.
     */
    public static final Supplier<Permission> CLAIM_BLOCK_PLACE =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "claim_block_place");

    /**
     * Allows players to open doors, click buttons, flip switches, etc., in the claim.
     */
    public static final Supplier<Permission> CLAIM_INTERACT_BLOCK =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "claim_interact_block");

    /**
     * Allows players to interact with containers in the claim.
     */
    public static final Supplier<Permission> CLAIM_INTERACT_CONTAINER =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "claim_interact_container");

    /**
     * Allows players to use items in the claim.
     */
    public static final Supplier<Permission> CLAIM_INTERACT_ITEM =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "claim_interact_item");

    /**
     * Allows entities to spawn in the claim.
     */
    public static final Supplier<Permission> CLAIM_ENTITY_SPAWN =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "claim_entity_spawn");

    /**
     * Allows explosions in the claim.
     */
    public static final Supplier<Permission> CLAIM_EXPLOSIONS =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "claim_explosions");

    /**
     * Allows players to attack non-player entities in the claim.
     */
    public static final Supplier<Permission> CLAIM_PVE =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "claim_pve");

    /**
     * Allows players to attack other players in the claim.
     */
    public static final Supplier<Permission> CLAIM_PVP =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "claim_pvp");

    /**
     * Allows the resident to deposit money into the town bank.
     */
    public static final Supplier<Permission> TOWN_BANK_DEPOSIT =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "town_bank_deposit");

    /**
     * Allows the resident to withdraw money from the town bank.
     */
    public static final Supplier<Permission> TOWN_BANK_WITHDRAW =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "town_bank_withdraw");

    /**
     * Allows the resident to talk in the town chat channel.
     */
    public static final Supplier<Permission> TOWN_CHAT =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "town_chat");

    /**
     * Allows the resident to create and delete claims for the town.
     */
    public static final Supplier<Permission> TOWN_CLAIM_MANAGE =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "town_claim_manage");

    /**
     * Allows the resident ALL town/claim permissions, and ignores town/claim permission overrides.
     * Use with caution.
     */
    public static final Supplier<Permission> TOWN_DEPUTY =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "town_deputy");

    /**
     * Allows the resident to kick other residents from the town.
     */
    public static final Supplier<Permission> TOWN_RESIDENT_KICK =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "town_resident_kick");

    /**
     * Allows the resident to change role permissions for the town's roles.
     */
    public static final Supplier<Permission> TOWN_ROLE_MANAGE =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "town_role_manage");

    /**
     * Allows the resident to deposit money into the nation bank.
     */
    public static final Supplier<Permission> NATION_BANK_DEPOSIT =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "nation_bank_deposit");

    /**
     * Allows the resident to withdraw money from the nation bank.
     */
    public static final Supplier<Permission> NATION_BANK_WITHDRAW =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "nation_bank_withdraw");

    /**
     * Allows the resident to talk in the nation chat channel.
     */
    public static final Supplier<Permission> NATION_CHAT =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "nation_chat");

    /**
     * Allows the resident ALL nation permissions, and ignores nation permission overrides.
     * Use with caution.
     */
    public static final Supplier<Permission> NATION_DEPUTY =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "nation_deputy");
    /**
     * Allows the resident to change role permissions for the nation's roles.
     */
    public static final Supplier<Permission> NATION_ROLE_MANAGE =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "nation_role_manage");

    /**
     * Allows the resident to invite towns to the nation.
     */
    public static final Supplier<Permission> NATION_TOWN_ADD =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "nation_town_add");

    /**
     * Allows the resident to kick towns from the nation.
     */
    public static final Supplier<Permission> NATION_TOWN_REMOVE =
            Sponge.getRegistry().getCatalogRegistry().provideSupplier(Permission.class, "nation_town_remove");

    private Permissions() {
        throw new AssertionError("Don't instantiate me!");
    }
}