package com.expansemc.township.plugin.util

import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.town.Town
import com.expansemc.township.plugin.util.Texts.Generic.LIST_SEPARATOR
import com.expansemc.township.plugin.util.Texts.Generic.NO
import com.expansemc.township.plugin.util.Texts.Generic.SEPARATOR
import com.expansemc.township.plugin.util.Texts.Generic.YES
import com.expansemc.township.plugin.util.Texts.Pagination.PADDING
import com.expansemc.township.plugin.util.Texts.Towns.TOWN_ROLES
import com.expansemc.township.plugin.util.extension.joinToText
import com.expansemc.township.plugin.util.extension.toText
import org.spongepowered.api.service.pagination.PaginationList
import org.spongepowered.api.text.LiteralText
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.format.TextColors.*

object Texts {

    object Generic {
        val YES: LiteralText = "yes" toText GREEN
        val NO: LiteralText = "no" toText RED

        val SEPARATOR: LiteralText = ": " toText DARK_GRAY
        val LIST_SEPARATOR: LiteralText = ", " toText GREEN

        fun size(size: Int): LiteralText = "[$size]" toText GREEN
        fun sizeLeftPad(size: Int): LiteralText = " [$size]" toText GREEN
    }

    object Pagination {
        val PADDING: LiteralText = "-" toText GOLD
    }

    object Towns {
        val TOWNS_HEADER: LiteralText = "Towns" toText YELLOW

        fun townHeader(town: Town): LiteralText = "Town: ${town.name}" toText YELLOW
        fun townResidentsHeader(town: Town): LiteralText = "Town Residents: ${town.name}" toText YELLOW

        val OPEN: LiteralText = "Open" toText DARK_GREEN
        val TOWN_SIZE: LiteralText = "Town Size" toText DARK_GREEN
        val BANK: LiteralText = "Bank" toText DARK_GREEN
        val MAYOR: LiteralText = "Mayor" toText DARK_GREEN
        val RESIDENTS: LiteralText = "Residents" toText DARK_GREEN

        val TOWN_ROLES: LiteralText = "Town Roles" toText DARK_GREEN

        fun open(town: Town): Text =
            Text.of(
                OPEN,
                SEPARATOR,
                if (town.isOpen) YES else NO
            )

        fun townSize(town: Town): Text =
            Text.of(
                TOWN_SIZE,
                SEPARATOR,
                Generic.size(town.claimRegistry.size())
            )

        fun bank(town: Town): Text =
            Text.of(
                BANK,
                SEPARATOR,
                town.formattedBalance
            )

        fun mayor(town: Town): Text =
            Text.of(
                MAYOR,
                SEPARATOR,
                Residents.resident(town.owner)
            )

        fun residents(town: Town): Text =
            Text.of(
                RESIDENTS,
                Generic.sizeLeftPad(town.residentRegistry.size()),
                SEPARATOR,
                Residents.residentList(town.residentRegistry.all.asSequence().take(15))
            )

        fun pagination(town: Town): PaginationList =
            PaginationList.builder()
                .title(townHeader(town))
                .padding(PADDING)
                .contents(
                    open(town),
                    townSize(town),
                    bank(town),
                    mayor(town),
                    residents(town)
                )
                .build()
    }

    object Residents {

        fun resident(resident: Resident): LiteralText = resident.name toText GREEN

        fun residentList(residents: Sequence<Resident>): Text = residents.map { Text.of(it.name) }.joinToText(LIST_SEPARATOR)

        fun townRoleList(resident: Resident): Text =
            Text.of(
                TOWN_ROLES,
                SEPARATOR,
                Text.newLine(),
                resident.townRoleRegistry.all
                    .sortedByDescending { it.priority }
                    .map { it.name toText GREEN }
                    .joinToText(Text.newLine())
            )
    }

    object Messages {
        val TOWN_CLAIM_FAILURE: LiteralText = "Failed to claim this chunk." toText RED
        val TOWN_LEAVE_FAILURE: LiteralText = "Failed to leave the town." toText RED
        val TOWN_LEAVE_NO_TOWN: LiteralText = "There is no town to leave." toText RED
        val TOWN_LEAVE_SUCCESS: LiteralText = "You have left the town." toText AQUA
        val TOWN_NOT_IN: LiteralText = "Not a resident of a town." toText RED

        fun townLeaveBroadcast(resident: Resident): LiteralText = "${resident.name} has left the town." toText AQUA
    }
}