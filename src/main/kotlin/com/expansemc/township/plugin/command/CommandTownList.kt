package com.expansemc.township.plugin.command

import com.expansemc.township.api.registry.central.CentralTownRegistry
import com.expansemc.township.api.resident.UserResident
import com.expansemc.township.api.town.Town
import com.expansemc.township.plugin.util.Texts
import com.expansemc.township.plugin.util.extension.toText
import org.spongepowered.api.command.Command
import org.spongepowered.api.command.CommandExecutor
import org.spongepowered.api.command.CommandResult
import org.spongepowered.api.command.parameter.CommandContext
import org.spongepowered.api.command.parameter.Parameter
import org.spongepowered.api.service.pagination.PaginationList
import org.spongepowered.api.text.Text

object CommandTownList : CommandExecutor {

    private val SORT_BY_KEY: Parameter.Key<SortBy> = TownshipParameter.key("sort_by")

    val COMMAND: Command.Parameterized = Command.builder()
        .setPermission("township.town.list.base")
        .setExecutor(this)
        .parameters(
            Parameter.enumValue(SortBy::class.java)
                .orDefault(SortBy.NAME)
                .setKey(SORT_BY_KEY)
                .build()
        )
        .build()

    override fun execute(context: CommandContext): CommandResult {
        val sortBy: SortBy = context.requireOne(SORT_BY_KEY)

        val towns: Collection<Text> = CentralTownRegistry.getInstance().all
            .sortedWith(sortBy.comparator)
            .map { "${it.name} (size: ${it.residentRegistry.size()})".toText() }

        val pagination: PaginationList = PaginationList.builder()
            .title(Texts.Towns.TOWNS_HEADER)
            .padding(Texts.Pagination.PADDING)
            .contents(towns)
            .build()

        pagination.sendTo(context.messageChannel.members)

        return CommandResult.success()
    }

    private enum class SortBy(val comparator: Comparator<Town>) {
        /**
         * Sort alphabetically.
         */
        NAME(compareBy(Town::getName)),

        /**
         * Sort by the number of residents.
         */
        RESIDENT_SIZE(compareBy { it.residentRegistry.size() }),

        /**
         * Sort by the number of residents currently online.
         */
        RESIDENT_ONLINE_SIZE(compareBy { it.residentRegistry.all.filter { res -> res is UserResident && res.user.isOnline }.size })
    }
}