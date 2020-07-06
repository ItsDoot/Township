package com.expansemc.township.plugin.command

import com.expansemc.township.api.registry.central.CentralTownRegistry
import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.town.Town
import com.expansemc.township.plugin.util.extension.toText
import com.expansemc.township.plugin.util.extension.unwrap
import org.spongepowered.api.Sponge
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.exception.CommandException
import org.spongepowered.api.command.parameter.CommandContext
import org.spongepowered.api.command.parameter.Parameter
import org.spongepowered.api.text.format.TextColors

object CommandTownDelete : CommandExecutor {

    private val RESIDENT_KEY: Parameter.Key<Resident> = TownshipParameter.key("resident")

    val COMMAND: Command.Parameterized = Command.builder()
        .setPermission("township.town.delete.base")
        .setExecutor(this)
        .parameters(
            TownshipParameter.residentOrSource()
                .setRequiredPermission("township.town.delete.other")
                .setKey(RESIDENT_KEY)
                .build()
        )
        .build()

    override fun execute(context: CommandContext): CommandResult {
        val resident: Resident = context.requireOne(RESIDENT_KEY)

        val town: Town = resident.town.unwrap()
            ?: throw CommandException("Must be in a town to perform that action.".toText())

        if (!town.isOwner(resident)) {
            throw CommandException("Only mayors can delete their towns.".toText())
        }

        Sponge.getServer().broadcastChannel.send("The town of ${town.name} has fallen into ruin!" toText TextColors.AQUA)
        CentralTownRegistry.getInstance().unregister(town)

        return CommandResult.success()
    }
}