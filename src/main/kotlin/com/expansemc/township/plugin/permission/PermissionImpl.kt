package com.expansemc.township.plugin.permission

import com.expansemc.township.api.permission.Permission
import com.expansemc.township.api.permission.PermissionType
import com.expansemc.township.api.permission.PermissionTypes
import com.expansemc.township.plugin.util.wrap
import org.spongepowered.api.CatalogKey
import org.spongepowered.api.item.inventory.ItemStack
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.translation.Translation
import org.spongepowered.plugin.PluginContainer
import java.util.*

data class PermissionImpl(
    private val key: CatalogKey,
    private val name: String,
    private val type: PermissionType,
    private val description: Text?,
    private val icon: ItemStack?
) : Permission {

    override fun getKey(): CatalogKey = this.key

    override fun getName(): String = this.name

    override fun getType(): PermissionType = this.type

    override fun getDescription(): Optional<Text> = this.description.wrap()

    override fun getIcon(): Optional<ItemStack> = this.icon.wrap()

    class Builder : Permission.Builder {

        private var key: CatalogKey? = null
        private var name: String? = null
        private var type: PermissionType? = null
        private var description: Text? = null
        private var icon: ItemStack? = null

        override fun key(key: CatalogKey): Permission.Builder {
            this.key = key
            return this
        }

        override fun name(name: String): Permission.Builder {
            this.name = name
            return this
        }

        override fun name(translation: Translation): Permission.Builder {
            return name(translation.get())
        }

        override fun type(type: PermissionType): Permission.Builder {
            this.type = type
            return this
        }

        override fun description(description: Text): Permission.Builder {
            this.description = description
            return this
        }

        override fun icon(icon: ItemStack): Permission.Builder {
            this.icon = icon
            return this
        }

        override fun reset(): Permission.Builder {
            this.key = null
            this.name = null
            this.type = null
            this.description = null
            this.icon = null
            return this
        }

        override fun build(): Permission = PermissionImpl(
            key = checkNotNull(this.key) { "key" },
            name = checkNotNull(this.name) { "name" },
            type = checkNotNull(this.type) { "type" },
            description = this.description,
            icon = this.icon
        )
    }
    
    companion object {
        fun getPermissions(container: PluginContainer): Set<Permission> = setOf(
            Permission.builder()
                .key(CatalogKey.of(container, "claim_block_break"))
                .name("Break Blocks In Claim")
                .type(PermissionTypes.CLAIM.get())
                .description(Text.of("Allow players to break blocks in the claim."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "claim_block_modify"))
                .name("Modify Blocks In Claim")
                .type(PermissionTypes.CLAIM.get())
                .description(Text.of("Allow players to modify blocks in the claim."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "claim_block_place"))
                .name("Place Blocks In Claim")
                .type(PermissionTypes.CLAIM.get())
                .description(Text.of("Allows players to place blocks in the claim."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "claim_interact_block"))
                .name("Interact With Blocks In Claim")
                .type(PermissionTypes.CLAIM.get())
                .description(Text.of("Allows players to open doors, click buttons, flip switches, etc., in the claim."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "claim_interact_container"))
                .name("Interact With Containers In Claim")
                .type(PermissionTypes.CLAIM.get())
                .description(Text.of("Allows players to interact with containers in the claim."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "claim_interact_item"))
                .name("Interact With Items In Claim")
                .type(PermissionTypes.CLAIM.get())
                .description(Text.of("Allows players to use items in the claim."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "claim_entity_spawn"))
                .name("Entity Spawns In Claim")
                .type(PermissionTypes.CLAIM.get())
                .description(Text.of("Allow entities to spawn in the claim"))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "claim_explosions"))
                .name("Explosions In Claim")
                .type(PermissionTypes.CLAIM.get())
                .description(Text.of("Allow explosions in the claim."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "claim_pve"))
                .name("PvE In Claim")
                .type(PermissionTypes.CLAIM.get())
                .description(Text.of("Allow players to attack non-player entities in the claim."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "claim_pvp"))
                .name("PvP In Claim")
                .type(PermissionTypes.CLAIM.get())
                .description(Text.of("Allow players to attack other players in the claim."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "town_bank_deposit"))
                .name("Deposit Into Town Bank")
                .type(PermissionTypes.TOWN.get())
                .description(Text.of("Allows the resident to deposit money into the town bank."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "town_bank_withdraw"))
                .name("Withdraw From Town Bank")
                .type(PermissionTypes.TOWN.get())
                .description(Text.of("Allows the resident to withdraw money from the town bank."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "town_chat"))
                .name("Use Town Chat")
                .type(PermissionTypes.TOWN.get())
                .description(Text.of("Allows the resident to talk in the town chat channel."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "town_claim_manage"))
                .name("Manage Town Claims")
                .type(PermissionTypes.TOWN.get())
                .description(Text.of("Allows the resident to create and delete claims for the town."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "town_deputy"))
                .name("Town Deputy")
                .type(PermissionTypes.TOWN.get())
                .description(Text.of("Allows the resident ALL town/claim permissions, and ignores town/claim permission overrides. Use with caution."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "town_resident_kick"))
                .name("Kick Residents From Town")
                .type(PermissionTypes.TOWN.get())
                .description(Text.of("Allows the resident to kick other residents from the town."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "town_role_manage"))
                .name("Manage Town Roles")
                .type(PermissionTypes.TOWN.get())
                .description(Text.of("Allows the resident to change role permissions for the town's roles."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "nation_bank_deposit"))
                .name("Deposit Into Nation Bank")
                .type(PermissionTypes.NATION.get())
                .description(Text.of("Allows the resident to deposit money into the nation bank."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "nation_bank_withdraw"))
                .name("Withdraw From Nation Bank")
                .type(PermissionTypes.NATION.get())
                .description(Text.of("Allows the resident to withdraw money from the nation bank."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "nation_chat"))
                .name("Use Nation Chat")
                .type(PermissionTypes.NATION.get())
                .description(Text.of("Allows the resident to talk in the nation chat channel."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "nation_deputy"))
                .name("Nation Deputy")
                .type(PermissionTypes.NATION.get())
                .description(Text.of("Allows the resident ALL nation permissions, and ignores nation permission overrides. Use with caution."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "nation_role_manage"))
                .name("Manage Nation Roles")
                .type(PermissionTypes.NATION.get())
                .description(Text.of("Allows the resident to change role permissions for the nation's roles."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "nation_town_add"))
                .name("Add Town To Nation")
                .type(PermissionTypes.NATION.get())
                .description(Text.of("Allows the resident to invite towns to the nation."))
                .build(),
            Permission.builder()
                .key(CatalogKey.of(container, "nation_town_remove"))
                .name("Remove Town From Nation")
                .type(PermissionTypes.NATION.get())
                .description(Text.of("Allows the resident to kick towns from the nation."))
                .build()
        )
    }
}