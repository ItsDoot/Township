package com.expansemc.township.plugin.command

import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.claim.ClaimArchetype
import com.expansemc.township.api.registry.central.CentralClaimRegistry
import com.expansemc.township.api.town.Town
import com.expansemc.township.plugin.util.Texts
import com.expansemc.township.plugin.util.extension.first
import com.expansemc.township.plugin.util.extension.toText
import com.expansemc.township.plugin.util.extension.unwrap
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.exception.CommandException
import org.spongepowered.api.command.parameter.CommandContext
import org.spongepowered.api.command.parameter.Parameter
import org.spongepowered.api.text.format.TextColors
import org.spongepowered.api.world.Locatable
import org.spongepowered.api.world.ServerLocation

object CommandTownClaim : CommandExecutor {

    private val TOWN_KEY: Parameter.Key<Town> = TownshipParameter.key("resident")

    val COMMAND: Command.Parameterized = Command.builder()
        .setPermission("township.town.claim.base")
        .setExecutor(this)
        .parameters(
            TownshipParameter.townOrSource()
                .setRequiredPermission("township.town.claim.other")
                .setKey(TOWN_KEY)
                .build()
        )
        .build()

    override fun execute(context: CommandContext): CommandResult {
        val location: ServerLocation = context.cause.first<Locatable>()?.serverLocation
            ?: throw CommandException("Command caller must be locatable.".toText())

        val town: Town = context.requireOne(TOWN_KEY)

        val claimArchetype: ClaimArchetype = ClaimArchetype.builder()
            .world(location.world)
            .chunkPosition(location.chunkPosition)
            .town(town)
            .build()

        val claim: Claim = CentralClaimRegistry.getInstance().register(claimArchetype).unwrap()
            ?: throw CommandException(Texts.Messages.TOWN_CLAIM_FAILURE)

        context.sendMessage("Claimed the chunk at ${claim.chunkPosition} for the town of ${claim.town.name}." toText TextColors.AQUA)

        return CommandResult.success()
    }
}