package com.expansemc.township.plugin.listener

import com.expansemc.township.api.TownshipAPI
import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.permission.Permission
import com.expansemc.township.api.permission.PermissionHolder
import com.expansemc.township.api.permission.Permissions
import com.expansemc.township.api.resident.Resident
import com.expansemc.township.plugin.util.extension.unwrap
import org.spongepowered.api.block.BlockSnapshot
import org.spongepowered.api.data.Transaction
import org.spongepowered.api.entity.Entity
import org.spongepowered.api.entity.living.player.server.ServerPlayer
import org.spongepowered.api.event.Cancellable
import org.spongepowered.api.event.Event
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.Order
import org.spongepowered.api.event.block.ChangeBlockEvent
import org.spongepowered.api.event.block.InteractBlockEvent
import org.spongepowered.api.event.block.entity.ChangeSignEvent
import org.spongepowered.api.event.entity.DamageEntityEvent
import org.spongepowered.api.event.entity.SpawnEntityEvent
import org.spongepowered.api.event.filter.cause.Root
import org.spongepowered.api.event.item.inventory.InteractItemEvent
import org.spongepowered.api.event.item.inventory.container.InteractContainerEvent
import org.spongepowered.api.event.world.ExplosionEvent
import org.spongepowered.api.item.inventory.type.CarriedInventory
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors.RED
import org.spongepowered.api.world.Locatable
import org.spongepowered.api.world.ServerLocation

class ClaimPermissionListener {

    /**
     * Protect block breakage
     * @see Permissions.CLAIM_BLOCK_BREAK
     */
    @Listener(order = Order.LAST)
    fun onBlockBreak(event: ChangeBlockEvent.Break, @Root player: ServerPlayer) {
        checkBlockProtection(event, player, Permissions.CLAIM_BLOCK_BREAK.get())
    }

    /**
     * Protect block placement
     * @see Permissions.CLAIM_BLOCK_PLACE
     */
    @Listener(order = Order.LAST)
    fun onBlockPlace(event: ChangeBlockEvent.Place, @Root player: ServerPlayer) {
        checkBlockProtection(event, player, Permissions.CLAIM_BLOCK_PLACE.get())
    }

    /**
     * Protect block modification
     * @see Permissions.CLAIM_BLOCK_MODIFY
     */
    @Listener(order = Order.LAST)
    fun onBlockModify(event: ChangeBlockEvent.Modify, @Root player: ServerPlayer) {
        checkBlockProtection(event, player, Permissions.CLAIM_BLOCK_MODIFY.get())
    }

    private fun checkBlockProtection(event: ChangeBlockEvent, player: ServerPlayer, permission: Permission) {
        val resident: Resident = TownshipAPI.getInstance().residentRegistry.getOrCreateUser(player.user)

        for (transaction: Transaction<BlockSnapshot> in event.transactions) {
            val location: ServerLocation = transaction.original.location.unwrap() ?: continue
            val claim: Claim = TownshipAPI.getInstance().claimRegistry[location].unwrap() ?: continue

            if (!resident.hasPermission(permission, claim)) {
                event.isCancelled = true
                player.sendNoPermissionMessage(claim, permission)
                return
            }
        }
    }

    /**
     * Protect signs
     * @see Permissions.CLAIM_BLOCK_MODIFY
     */
    @Listener(order = Order.LAST)
    fun onChangeSign(event: ChangeSignEvent, @Root player: ServerPlayer) {
        val location: ServerLocation = event.sign.serverLocation

        checkPlayerPermission(event, player, location, Permissions.CLAIM_BLOCK_MODIFY.get())
    }

    /**
     * Protect block interaction (flick levers, push buttons, etc)
     * @see Permissions.CLAIM_INTERACT_BLOCK
     */
    @Listener(order = Order.LAST)
    fun onInteractBlock(event: InteractBlockEvent.Secondary, @Root player: ServerPlayer) {
        val location: ServerLocation = event.block.location.unwrap() ?: return

        checkPlayerPermission(event, player, location, Permissions.CLAIM_INTERACT_BLOCK.get())
    }

    /**
     * Protect container interaction (open chests, furnaces, etc)
     * @see Permissions.CLAIM_INTERACT_CONTAINER
     */
    @Listener(order = Order.LAST)
    fun onInteractContainer(event: InteractContainerEvent.Open, @Root player: ServerPlayer) {
        val inv: CarriedInventory<*> = event.container as? CarriedInventory<*> ?: return
        val carrier: Locatable = inv.carrier.unwrap() as? Locatable ?: return
        val location: ServerLocation = carrier.serverLocation

        checkPlayerPermission(event, player, location, Permissions.CLAIM_INTERACT_CONTAINER.get())
    }

    /**
     * Protect item interaction (shoot bows, dump water buckets, etc)
     * @See Permissions.CLAIM_INTERACT_ITEM
     */
    @Listener(order = Order.LAST)
    fun onInteractItem(event: InteractItemEvent.Secondary, @Root player: ServerPlayer) {
        val location: ServerLocation = player.world.getLocation(event.interactionPoint.orElse(player.position))

        checkPlayerPermission(event, player, location, Permissions.CLAIM_INTERACT_ITEM.get())
    }

    /**
     * Protect entities from spawning
     * @see Permissions.CLAIM_ENTITY_SPAWN
     */
    @Listener(order = Order.LAST)
    fun onEntitySpawn(event: SpawnEntityEvent) {
        // Maybe a player spawned these entities.
        val resident: Resident? = event.cause.first(ServerPlayer::class.java).unwrap()
            ?.let { TownshipAPI.getInstance().residentRegistry.getOrCreateUser(it.user) }

        event.filterEntityLocations { location: ServerLocation ->
            checkLocationPermission(resident, location, Permissions.CLAIM_ENTITY_SPAWN.get())
        }
    }

    /**
     * Protect from explosions
     * @see Permissions.CLAIM_EXPLOSIONS
     */
    @Listener(order = Order.LAST)
    fun onExplode(event: ExplosionEvent.Detonate) {
        // Maybe a player detonated this explosion.
        val resident: Resident? = event.cause.first(ServerPlayer::class.java).unwrap()
            ?.let { TownshipAPI.getInstance().residentRegistry.getOrCreateUser(it.user) }

        event.affectedLocations.removeIf { location: ServerLocation ->
            checkLocationPermission(resident, location, Permissions.CLAIM_EXPLOSIONS.get())
        }
    }

    private fun checkLocationPermission(resident: Resident?, location: ServerLocation, permission: Permission): Boolean {
        // Get the claim to check, if available.
        val claim: Claim? = TownshipAPI.getInstance().claimRegistry.get(location).unwrap()

        // Check claim permissions if the chunk is claimed.
        if (claim != null) {
            // Use the player that caused it, or the visitor role otherwise
            val holder: PermissionHolder = resident ?: claim.town.roleRegistry.visitorRole

            return holder.hasPermission(permission, claim)
        } else {
            // Otherwise just check if the wilderness allows the permission.
            return TownshipAPI.getInstance().wildernessRole.hasPermission(permission)
        }
    }

    /**
     * Protect from entity/player damage
     * @see Permissions.CLAIM_PVE
     * @see Permissions.CLAIM_PVP
     */
    @Listener
    fun onDamage(event: DamageEntityEvent, @Root player: ServerPlayer) {
        val entity: Entity = event.entity
        val permission: Permission = if (entity is ServerPlayer) Permissions.CLAIM_PVP.get() else Permissions.CLAIM_PVE.get()

        checkPlayerPermission(event, player, entity.serverLocation, permission)
    }

    private fun <T> checkPlayerPermission(event: T, player: ServerPlayer, location: ServerLocation, permission: Permission)
            where T : Event, T : Cancellable {
        val resident: Resident = TownshipAPI.getInstance().residentRegistry.getOrCreateUser(player.user)
        val claim: Claim = TownshipAPI.getInstance().claimRegistry.get(location).unwrap() ?: return

        if (!resident.hasPermission(permission, claim)) {
            event.isCancelled = true
            player.sendNoPermissionMessage(claim, permission)
        }
    }

    private fun ServerPlayer.sendNoPermissionMessage(claim: Claim, permission: Permission) {
        this.sendMessage(Text.of(RED, "You do not have permission $permission in ${claim.town.name}'s claim."))
    }
}