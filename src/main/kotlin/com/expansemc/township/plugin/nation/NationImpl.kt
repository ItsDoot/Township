package com.expansemc.township.plugin.nation

import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.nation.NationRole
import com.expansemc.township.api.nation.NationWarp
import com.expansemc.township.api.permission.RoleRegistry
import com.expansemc.township.api.resident.ResidentRegistry
import com.expansemc.township.api.town.Town
import com.expansemc.township.api.town.TownRegistry
import com.expansemc.township.api.town.TownService
import com.expansemc.township.api.warp.WarpRegistry
import org.spongepowered.api.service.economy.account.Account
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.channel.MessageChannel
import java.util.*

data class NationImpl(
    private val uniqueId: UUID,
    private var name: String,
    private var open: Boolean,
    private var ownerId: UUID
) : Nation {

    override fun getUniqueId(): UUID = this.uniqueId

    override fun getName(): String = this.name

    override fun setName(name: String) {
        this.name = name
    }

    override fun isOpen(): Boolean = this.open

    override fun setOpen(open: Boolean) {
        this.open = open
    }

    override fun getOwner(): Town =
        TownService.getInstance().getTown(this.ownerId).get()

    override fun isOwner(town: Town): Boolean =
        this.ownerId == town.uniqueId

    override fun setOwner(town: Town) {
        // TODO event

        this.ownerId = town.uniqueId
    }

    override fun getTowns(): TownRegistry.Mutable = TODO()

    override fun getResidents(): ResidentRegistry = TODO()

    override fun getRoles(): RoleRegistry.Mutable<NationRole> = TODO()

    override fun getWarps(): WarpRegistry.Mutable<NationWarp> = TODO()

    override fun sendMessage(message: Text) = TODO()

    override fun getMessageChannel(): MessageChannel = TODO()

    override fun setMessageChannel(channel: MessageChannel?) = TODO()

    override fun getAccount(): Optional<Account> = TODO()
}