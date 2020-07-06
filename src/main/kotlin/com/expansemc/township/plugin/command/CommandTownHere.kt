package com.expansemc.township.plugin.command

import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.registry.central.CentralClaimRegistry
import com.expansemc.township.plugin.util.Texts
import com.expansemc.township.plugin.util.extension.first
import com.expansemc.township.plugin.util.extension.unwrap
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.exception.CommandException
import org.spongepowered.api.command.parameter.CommandContext
import org.spongepowered.api.command.parameter.Parameter
import org.spongepowered.api.entity.living.player.server.ServerPlayer
import org.spongepowered.api.text.Text
import org.spongepowered.api.world.Locatable
import org.spongepowered.api.world.ServerLocation

object CommandTownHere : CommandExecutor {

    private val PLAYER_KEY: Parameter.Key<ServerPlayer> = TownshipParameter.key("player")

    val COMMAND = Command.builder()
        .setPermission("township.town.here.base")
        .setExecutor(this)
        .parameters(
            Parameter.player()
                .setRequiredPermission("township.town.here.other")
                .optional()
                .setKey(PLAYER_KEY)
                .build()
        )
        .build()

    override fun execute(context: CommandContext): CommandResult {
        val locatable: Locatable = context.getOne(PLAYER_KEY).unwrap()
            ?: context.cause.first()
            ?: throw CommandException(Text.of("The player argument must be specified."))
        val location: ServerLocation = locatable.serverLocation

        val claim: Claim = CentralClaimRegistry.getInstance()[location].unwrap()
            ?: throw CommandException(Text.of("You are not within a claimed chunk."))

        Texts.Towns.pagination(claim.town).sendTo(context.messageChannel.members)

        return CommandResult.success()
    }
}