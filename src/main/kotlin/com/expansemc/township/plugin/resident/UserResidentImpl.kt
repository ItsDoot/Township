package com.expansemc.township.plugin.resident

import com.expansemc.township.api.TownshipAPI
import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.permission.Permission
import com.expansemc.township.api.registry.type.ResidentRegistry
import com.expansemc.township.api.registry.type.RoleRegistry
import com.expansemc.township.api.resident.UserResident
import com.expansemc.township.api.town.Town
import com.expansemc.township.plugin.data.TownshipKeys
import com.expansemc.township.plugin.util.extension.getSetOrEmpty
import com.expansemc.township.plugin.util.extension.unwrap
import org.spongepowered.api.Sponge
import org.spongepowered.api.entity.living.player.User
import org.spongepowered.api.profile.GameProfile
import org.spongepowered.api.service.user.UserStorageService
import org.spongepowered.api.text.Text
import org.spongepowered.api.text.channel.MessageChannel
import java.util.*

data class UserResidentImpl(private val uniqueId: UUID) : UserResident {

    override fun getUniqueId(): UUID = this.uniqueId

    override fun sendMessage(message: Text) {
        this.user.player.ifPresent {
            it.sendMessage(message)
        }
    }

    override fun getMessageChannel(): MessageChannel {
        return this.user.player
            .map { it.messageChannel }
            .orElseGet { MessageChannel.toNone() }
    }

    override fun setMessageChannel(channel: MessageChannel) {
        this.user.player.ifPresent {
            it.messageChannel = channel
        }
    }

    override fun getPermissions(): Collection<Permission> {
        val town: Town = this.town.unwrap() ?: return emptyList()
        val townRoleIds = this.user.inventory
        TODO()
    }

    override fun hasPermission(permission: Permission): Boolean =
        permission in this.user.getSetOrEmpty(TownshipKeys.PERMISSIONS)

    override fun addPermission(permission: Permission): Boolean =
        this.user.offerSingle(TownshipKeys.PERMISSIONS, permission).isSuccessful

    override fun removePermission(permission: Permission): Boolean =
        this.user.removeSingle(TownshipKeys.PERMISSIONS, permission).isSuccessful

    override fun getName(): String = this.user.name

    override fun getTown(): Optional<Town> =
        this.user.get(TownshipKeys.TOWN)
            .flatMap(TownshipAPI.getInstance().townRegistry::get)

    override fun hasTown(): Boolean =
        this.user.get(TownshipKeys.TOWN).isPresent

    override fun getTownRoleRegistry(): RoleRegistry.ArchetypeMutable<Town> = TODO()

    override fun getNationRoleRegistry(): RoleRegistry.ArchetypeMutable<Nation> = TODO()

    override fun getUser(): User =
        Sponge.getServiceManager().provideUnchecked(UserStorageService::class.java)
            .getOrCreate(GameProfile.of(uniqueId))

    override fun getFriends(): ResidentRegistry.ArchetypeMutable = TODO()
}