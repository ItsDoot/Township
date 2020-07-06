package com.expansemc.township.plugin.command

import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.town.Town
import com.expansemc.township.plugin.util.Texts
import com.expansemc.township.plugin.util.extension.unwrap
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.exception.CommandException
import org.spongepowered.api.command.parameter.CommandContext
import org.spongepowered.api.command.parameter.Parameter

object CommandTownLeave : CommandExecutor {

    private val RESIDENT_KEY: Parameter.Key<Resident> = TownshipParameter.key("resident")

    val COMMAND: Command.Parameterized = Command.builder()
        .setPermission("township.town.leave.base")
        .setExecutor(this)
        .parameters(
            TownshipParameter.residentOrSource()
                .setRequiredPermission("township.town.leave.other")
                .setKey(RESIDENT_KEY)
                .build()
        )
        .build()

    override fun execute(context: CommandContext): CommandResult {
        val resident: Resident = context.requireOne(RESIDENT_KEY)

        val town: Town = resident.town.unwrap()
            ?: throw CommandException(Texts.Messages.TOWN_LEAVE_NO_TOWN)

        if (!town.residentRegistry.unregister(resident)) {
            throw CommandException(Texts.Messages.TOWN_LEAVE_FAILURE)
        }

        town.sendMessage(Texts.Messages.townLeaveBroadcast(resident))
        resident.sendMessage(Texts.Messages.TOWN_LEAVE_SUCCESS)

        return CommandResult.success()
    }
}