package com.expansemc.township.plugin.listener

import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.claim.ClaimService
import com.expansemc.township.api.permission.Permission
import com.expansemc.township.api.permission.Permissions
import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.resident.ResidentService
import com.expansemc.township.plugin.util.unwrap
import org.spongepowered.api.block.BlockSnapshot
import org.spongepowered.api.data.Transaction
import org.spongepowered.api.entity.living.player.Player
import org.spongepowered.api.entity.living.player.server.ServerPlayer
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.block.ChangeBlockEvent
import org.spongepowered.api.event.block.entity.ChangeSignEvent
import org.spongepowered.api.event.filter.cause.Root
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors.RED
import org.spongepowered.api.world.ServerLocation

class BlockListener {

    @Listener
    fun onBlockBreak(event: ChangeBlockEvent.Break, @Root player: ServerPlayer) {
        checkProtection(event, player, Permissions.CLAIM_BLOCK_BREAK.get())
    }

    @Listener
    fun onBlockPlace(event: ChangeBlockEvent.Place, @Root player: ServerPlayer) {
        checkProtection(event, player, Permissions.CLAIM_BLOCK_PLACE.get())
    }

    @Listener
    fun onBlockModify(event: ChangeBlockEvent.Modify, @Root player: ServerPlayer) {
        checkProtection(event, player, Permissions.CLAIM_BLOCK_MODIFY.get())
    }

    private fun checkProtection(event: ChangeBlockEvent, player: ServerPlayer, permission: Permission) {
        val resident: Resident = ResidentService.getInstance().getOrCreateUserResident(player.user)

        for (transaction: Transaction<BlockSnapshot> in event.transactions) {
            val location: ServerLocation = transaction.original.location.unwrap() ?: continue
            val claim: Claim = ClaimService.getInstance().getClaimAt(location).unwrap() ?: continue

            if (!resident.hasPermission(permission, claim)) {
                event.isCancelled = true
                player.sendMessage(Text.of(RED, "You do not have the permission $permission in ${claim.town.name}'s claim."))
                return
            }
        }
    }

    @Listener
    fun onChangeSign(event: ChangeSignEvent, @Root player: ServerPlayer) {
        val resident: Resident = ResidentService.getInstance().getOrCreateUserResident(player.user)
        val claim: Claim = ClaimService.getInstance().getClaimAt(event.sign.serverLocation).unwrap() ?: return

        if (!resident.hasPermission(Permissions.CLAIM_BLOCK_MODIFY.get(), claim)) {
            event.isCancelled = true
            player.sendMessage(Text.of(RED, "That chunk is owned by the town ${claim.town.name}."))
            return
        }
    }
}