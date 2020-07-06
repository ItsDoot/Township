package com.expansemc.township.plugin.command

import com.expansemc.township.api.town.Town
import com.expansemc.township.plugin.util.Texts
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.parameter.CommandContext
import org.spongepowered.api.command.parameter.Parameter

object CommandTownInfo : CommandExecutor {

    private val TOWN_KEY: Parameter.Key<Town> = TownshipParameter.key("town")

    val COMMAND: Command.Parameterized = Command.builder()
        .setPermission("township.town.info.base")
        .setExecutor(this)
        .parameters(
            TownshipParameter.townOrSource()
                .setRequiredPermission("township.town.info.other")
                .setKey(TOWN_KEY)
                .build()
        )
        .build()

    override fun execute(context: CommandContext): CommandResult {
        val town: Town = context.requireOne(TOWN_KEY)

        Texts.Towns.pagination(town).sendTo(context.messageChannel.members)

        return CommandResult.success()
    }
}