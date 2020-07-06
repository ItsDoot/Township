package com.expansemc.township.plugin.command

import com.expansemc.township.api.TownshipAPI
import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.town.Town
import com.expansemc.township.api.town.TownArchetype
import com.expansemc.township.plugin.util.extension.toResident
import com.expansemc.township.plugin.util.extension.first
import com.expansemc.township.plugin.util.extension.unwrap
import org.spongepowered.api.Sponge
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.exception.CommandException
import org.spongepowered.api.command.parameter.CommandContext
import org.spongepowered.api.command.parameter.Parameter
import org.spongepowered.api.entity.living.player.server.ServerPlayer
import org.spongepowered.api.text.Text

object CommandTownCreate : CommandExecutor {

    private val nameRegex = Regex("^[a-zA-Z0-9]{2,32}$")

    private val NAME_KEY: Parameter.Key<String> = TownshipParameter.key("name")
    private val OWNER_KEY: Parameter.Key<Resident> = TownshipParameter.key("owner")

    val COMMAND: Command.Parameterized = Command.builder()
        .setPermission("township.town.create.base")
        .setExecutor(this)
        .parameters(
            Parameter.string()
                .setKey(NAME_KEY)
                .build(),
            TownshipParameter.resident()
                .setRequiredPermission("township.town.create.other")
                .optional()
                .setKey(OWNER_KEY)
                .build()
        )
        .build()

    override fun execute(context: CommandContext): CommandResult {
        val name: String = context.requireOne(NAME_KEY)
        val owner: Resident? = context.getOne(OWNER_KEY).unwrap()

        if (!name.matches(nameRegex)) {
            throw CommandException(Text.of("Town name must be alphanumeric and between 2 and 32 characters long."))
        }

        // Use the provided owner, or the player that executed the command, or the system resident.
        val resident: Resident = owner
            ?: context.cause.first<ServerPlayer>()?.user?.toResident()
            ?: TownshipAPI.getInstance().residentRegistry.systemResident

        if (resident.hasTown()) {
            if (owner == null) {
                throw CommandException(Text.of("You must leave your current town to create a new one."))
            } else {
                throw CommandException(Text.of("${resident.name} must leave their current town to create a new one for them."))
            }
        }

        if (name in TownshipAPI.getInstance().townRegistry) {
            throw CommandException(Text.of("The town of $name already exists!"))
        }

        val archetype: TownArchetype = TownArchetype.builder()
            .name(name)
            .open(false)
            .owner(resident)
            .residents(resident)
            .build()

        val town: Town = TownshipAPI.getInstance().townRegistry.register(archetype).unwrap()
            ?: throw CommandException(Text.of("Failed to register the town of $name."))

        Sponge.getServer().broadcastChannel
            .send(Text.of("The town of ${town.name} has been founded by ${town.owner.name}."))

        return CommandResult.success()
    }
}