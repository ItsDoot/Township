package com.expansemc.township.plugin.nation

import com.expansemc.township.api.TownshipAPI
import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.nation.NationRole
import com.expansemc.township.api.nation.NationWarp
import com.expansemc.township.api.registry.type.RoleRegistry
import com.expansemc.township.api.registry.type.ResidentRegistry
import com.expansemc.township.api.town.Town
import com.expansemc.township.api.registry.type.TownRegistry
import com.expansemc.township.api.registry.type.WarpRegistry
import com.expansemc.township.plugin.registry.RoleRegistryImpl
import com.expansemc.township.plugin.registry.view.NationResidentRegistryView
import com.expansemc.township.plugin.registry.TownRegistryImpl
import com.expansemc.township.plugin.util.registry.RegistryMessageChannel
import com.expansemc.township.plugin.warp.SimpleWarpRegistry
import org.spongepowered.api.Sponge
import org.spongepowered.api.service.economy.EconomyService
import org.spongepowered.api.service.economy.account.Account
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.channel.MessageChannel
import java.util.*

data class NationImpl(
    private val uniqueId: UUID,
    private var name: String,
    private var open: Boolean,
    private var ownerId: UUID,
    private val visitorRole: NationRole
) : Nation {

    private val townRegistry = TownRegistryImpl()
    private val roleRegistry = RoleRegistryImpl(this.visitorRole)
    private val warpRegistry = SimpleWarpRegistry<NationWarp>()

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

    override fun getOwner(): Town = TownshipAPI.getInstance().townRegistry[this.ownerId].get()

    override fun isOwner(town: Town): Boolean = this.ownerId == town.uniqueId

    // TODO event
    override fun setOwner(town: Town) {
        this.ownerId = town.uniqueId
    }

    override fun getTownRegistry(): TownRegistry.Mutable = this.townRegistry

    override fun getResidentRegistry(): ResidentRegistry =
        NationResidentRegistryView(this)

    override fun getRoleRegistry(): RoleRegistry.Mutable<NationRole> = this.roleRegistry

    override fun getWarpRegistry(): WarpRegistry.Mutable<NationWarp> = this.warpRegistry

    override fun sendMessage(message: Text) = this.messageChannel.send(message)

    override fun getMessageChannel(): MessageChannel = this.messageChannel

    override fun setMessageChannel(channel: MessageChannel?) {}

    override fun getAccount(): Optional<Account> =
        Sponge.getServiceManager().provide(EconomyService::class.java)
            .flatMap { it.getOrCreateAccount("nation-$uniqueId") }
}