package com.expansemc.township.plugin.command

import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.town.Town
import com.expansemc.township.plugin.util.extension.toText
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.exception.CommandException
import org.spongepowered.api.command.parameter.CommandContext
import org.spongepowered.api.command.parameter.Parameter

object CommandTownJoin : CommandExecutor {

    private val TOWN_KEY: Parameter.Key<Town> = TownshipParameter.key("town")
    private val RESIDENT_KEY: Parameter.Key<Resident> = TownshipParameter.key("resident")

    val COMMAND: Command.Parameterized = Command.builder()
        .setPermission("township.town.join.base")
        .setExecutor(this)
        .parameters(
            TownshipParameter.town()
                .setKey(TOWN_KEY)
                .build(),
            TownshipParameter.residentOrSource()
                .setRequiredPermission("township.town.join.other")
                .setKey(RESIDENT_KEY)
                .build()
        )
        .build()

    override fun execute(context: CommandContext): CommandResult {
        val town: Town = context.requireOne(TOWN_KEY)
        val resident: Resident = context.requireOne(RESIDENT_KEY)

        if (resident.hasTown()) {
            throw CommandException("Must not be in a town to be able to join another one.".toText())
        }

        if (!town.isOpen) {
            throw CommandException("The town of ${town.name} is not joinable without invitation.".toText())
        }

        town.residentRegistry.register(resident)
        town.sendMessage("${resident.name} has joined the town.".toText())

        return CommandResult.success()
    }
}