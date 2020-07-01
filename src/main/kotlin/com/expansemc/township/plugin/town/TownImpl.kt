package com.expansemc.township.plugin.town

import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.resident.ResidentService
import com.expansemc.township.api.town.Town
import com.expansemc.township.api.town.TownRole
import com.expansemc.township.api.town.TownWarp
import com.expansemc.township.plugin.util.unwrap
import org.spongepowered.api.Sponge
import org.spongepowered.api.service.economy.EconomyService
import org.spongepowered.api.service.economy.account.Account
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.channel.MessageChannel
import java.util.*

data class TownImpl(
    private val uniqueId: UUID,
    private var name: String,
    private var open: Boolean,
    private var ownerId: UUID
) : Town {

    private var messageChannel: MessageChannel = TownMessageChannel(this)

    private val residents = HashSet<UUID>()

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

    override fun getResidents(): Collection<Resident> =
        this.residents.mapNotNull { ResidentService.getInstance().getUserResident(it).unwrap() }

    override fun containsResident(resident: Resident): Boolean =
        resident.uniqueId in this.residents

    override fun addResident(resident: Resident): Boolean =
        this.residents.add(resident.uniqueId)

    override fun removeResident(resident: Resident): Boolean =
        this.residents.remove(resident.uniqueId)

    override fun getVisitorRole(): TownRole = TODO()

    override fun getAccount(): Optional<Account> =
        Sponge.getServiceManager().provide(EconomyService::class.java)
            .flatMap { it.getOrCreateAccount("town-$uniqueId") }

    override fun getRoles(): MutableCollection<TownRole> {
        TODO("Not yet implemented")
    }

    override fun getRole(name: String?): Optional<TownRole> {
        TODO("Not yet implemented")
    }

    override fun hasRole(role: TownRole?): Boolean {
        TODO("Not yet implemented")
    }

    override fun addRole(role: TownRole?): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeRole(role: TownRole?): Boolean {
        TODO("Not yet implemented")
    }

    override fun getWarps(): MutableCollection<TownWarp> {
        TODO("Not yet implemented")
    }

    override fun getWarp(name: String?): Optional<TownWarp> {
        TODO("Not yet implemented")
    }

    override fun hasWarp(warp: TownWarp?): Boolean {
        TODO("Not yet implemented")
    }

    override fun addWarp(warp: TownWarp?): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeWarp(warp: TownWarp?): Boolean {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: Text) {
        this.messageChannel.send(message)
    }

    override fun getMessageChannel(): MessageChannel = this.messageChannel

    override fun setMessageChannel(channel: MessageChannel?) {}
}