package com.expansemc.township.plugin.command

import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.town.Town
import com.expansemc.township.plugin.util.Texts
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.parameter.CommandContext
import org.spongepowered.api.command.parameter.Parameter
import org.spongepowered.api.service.pagination.PaginationList
import org.spongepowered.api.text.LiteralText
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.action.TextActions

object CommandTownResidents : CommandExecutor {

    private val TOWN_KEY: Parameter.Key<Town> = TownshipParameter.key("town")

    val COMMAND: Command.Parameterized = Command.builder()
        .setPermission("township.town.residents.base")
        .setExecutor(this)
        .parameters(
            TownshipParameter.townOrSource()
                .setRequiredPermission("township.town.residents.other")
                .setKey(TOWN_KEY)
                .build()
        )
        .build()

    override fun execute(context: CommandContext): CommandResult {
        val town: Town = context.requireOne(TOWN_KEY)

        val residents: List<LiteralText> = town.residentRegistry.all.map { resident: Resident ->
            Text.builder(resident.name)
                .onHover(TextActions.showText(Texts.Residents.townRoleList(resident)))
                .build()
        }

        val pagination: PaginationList = PaginationList.builder()
            .title(Texts.Towns.townResidentsHeader(town))
            .padding(Texts.Pagination.PADDING)
            .contents(residents)
            .build()

        pagination.sendTo(context.messageChannel.members)

        return CommandResult.success()
    }
}