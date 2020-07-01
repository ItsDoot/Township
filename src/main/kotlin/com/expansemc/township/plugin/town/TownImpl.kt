package com.expansemc.township.plugin.town

import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.nation.NationService
import com.expansemc.township.api.permission.RoleRegistry
import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.resident.ResidentRegistry
import com.expansemc.township.api.resident.ResidentService
import com.expansemc.township.api.town.Town
import com.expansemc.township.api.town.TownRole
import com.expansemc.township.api.town.TownWarp
import com.expansemc.township.api.warp.WarpRegistry
import com.expansemc.township.plugin.util.wrap
import org.spongepowered.api.service.economy.account.Account
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.channel.MessageChannel
import java.util.*

data class TownImpl(
    private val uniqueId: UUID,
    private var name: String,
    private var open: Boolean,
    private var ownerId: UUID,
    private var nationId: UUID?
) : Town {

    override fun getUniqueId(): UUID = this.uniqueId

    override fun getName(): String = this.name

    override fun setName(name: String) {
        this.name = name
    }

    override fun isOpen(): Boolean = this.open

    override fun setOpen(open: Boolean) {
        this.open = open
    }

    override fun getOwner(): Resident =
        ResidentService.getInstance().getUserResident(ownerId)
            .orElseThrow { IllegalStateException("Town $name has no name!") }

    override fun isOwner(resident: Resident): Boolean = resident.uniqueId == this.ownerId

    override fun setOwner(resident: Resident) {
        this.ownerId = resident.uniqueId
    }

    override fun getNation(): Optional<Nation> =
        nationId.wrap().flatMap { NationService.getInstance().getNation(it) }

    override fun setNation(nation: Nation?) {
        // TODO event

        this.nationId = nation?.uniqueId
    }

    override fun getResidents(): ResidentRegistry.Mutable = TODO()

    override fun getRoles(): RoleRegistry.Mutable<TownRole> = TODO()

    override fun getWarps(): WarpRegistry.Mutable<TownWarp> = TODO()

    override fun sendMessage(message: Text) = TODO()

    override fun getMessageChannel(): MessageChannel = TODO()

    override fun setMessageChannel(channel: MessageChannel?) = TODO()

    override fun getAccount(): Optional<Account> = TODO()
}