package com.expansemc.township.plugin.town

import com.expansemc.township.api.TownshipAPI
import com.expansemc.township.api.registry.type.ClaimRegistry
import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.permission.Role
import com.expansemc.township.api.registry.type.RoleRegistry
import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.registry.type.ResidentRegistry
import com.expansemc.township.api.town.Town
import com.expansemc.township.api.registry.type.WarpRegistry
import com.expansemc.township.plugin.registry.view.TownClaimRegistryView
import com.expansemc.township.plugin.registry.RoleRegistryImpl
import com.expansemc.township.plugin.registry.ResidentRegistryImpl
import com.expansemc.township.plugin.registry.WarpRegistryImpl
import com.expansemc.township.plugin.util.registry.RegistryMessageChannel
import com.expansemc.township.plugin.util.extension.wrap
import com.expansemc.township.plugin.warp.SimpleWarpRegistry
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
    private var ownerId: UUID,
    private var nationId: UUID?,
    private val visitorRole: Role<Town>
) : Town {

    private val residentRegistry = ResidentRegistryImpl()
    private val roleRegistry = RoleRegistryImpl(this.visitorRole)
    private val warpRegistry = WarpRegistryImpl<Town>()

    private val messageChannel = RegistryMessageChannel(this.residentRegistry)

    override fun getUniqueId(): UUID = this.uniqueId

    override fun getName(): String = this.name

    // TODO event
    override fun setName(name: String) {
        this.name = name
    }

    override fun isOpen(): Boolean = this.open

    // TODO event
    override fun setOpen(open: Boolean) {
        this.open = open
    }

    override fun getOwner(): Resident =
        TownshipAPI.getInstance().residentRegistry[ownerId]
            .orElseThrow { IllegalStateException("Town $name has no name!") }

    override fun isOwner(resident: Resident): Boolean =
        resident.uniqueId == this.ownerId

    // TODO event
    override fun setOwner(resident: Resident) {
        this.ownerId = resident.uniqueId
    }

    override fun getNation(): Optional<Nation> =
        nationId.wrap().flatMap(TownshipAPI.getInstance().nationRegistry::get)

    override fun hasNation(): Boolean = this.nationId != null

    override fun getResidentRegistry(): ResidentRegistry.Mutable = this.residentRegistry

    override fun getClaimRegistry(): ClaimRegistry =
        TownClaimRegistryView(TownshipAPI.getInstance().claimRegistry, this)

    override fun getRoleRegistry(): RoleRegistry.ArchetypeMutable<Town> = this.roleRegistry

    override fun getWarpRegistry(): WarpRegistry.ArchetypeMutable<Town> = this.warpRegistry

    override fun sendMessage(message: Text) = this.messageChannel.send(message)

    override fun getMessageChannel(): MessageChannel = this.messageChannel

    override fun setMessageChannel(channel: MessageChannel?) {}

    override fun getAccount(): Optional<Account> =
        Sponge.getServiceManager().provide(EconomyService::class.java)
            .flatMap { it.getOrCreateAccount("town-$uniqueId") }
}