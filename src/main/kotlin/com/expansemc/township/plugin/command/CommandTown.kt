package com.expansemc.township.plugin.command

import org.spongepowered.api.command.Command

object CommandTown {

    val COMMAND: Command.Parameterized = Command.builder()
        .setPermission("township.town.base")
        .child(CommandTownClaim.COMMAND, "claim", "c")
        .child(CommandTownCreate.COMMAND, "create")
        .child(CommandTownDelete.COMMAND, "delete")
        .child(CommandTownHere.COMMAND, "here", "h")
        .child(CommandTownInfo.COMMAND, "info", "i")
        .child(CommandTownJoin.COMMAND, "join")
        .child(CommandTownLeave.COMMAND, "leave")
        .child(CommandTownList.COMMAND, "list", "l")
        .child(CommandTownResidents.COMMAND, "residents", "res")
        .build()
}