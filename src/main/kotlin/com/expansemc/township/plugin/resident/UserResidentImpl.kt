package com.expansemc.township.plugin.resident

import com.expansemc.township.api.permission.Permission
import com.expansemc.township.api.resident.ResidentService
import com.expansemc.township.api.resident.UserResident
import com.expansemc.township.api.town.Town
import com.expansemc.township.api.town.TownRole
import com.expansemc.township.api.town.TownRoleService
import com.expansemc.township.api.town.TownService
import com.expansemc.township.plugin.data.TownshipKeys
import com.expansemc.township.plugin.util.getSetOrEmpty
import com.expansemc.township.plugin.util.unwrap
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

    override fun getPermissions(): Collection<Permission> =
        this.user.getSetOrEmpty(TownshipKeys.PERMISSIONS)

    override fun hasPermission(permission: Permission): Boolean =
        permission in this.user.getSetOrEmpty(TownshipKeys.PERMISSIONS)

    override fun addPermission(permission: Permission): Boolean =
        this.user.offerSingle(TownshipKeys.PERMISSIONS, permission).isSuccessful

    override fun removePermission(permission: Permission): Boolean =
        this.user.removeSingle(TownshipKeys.PERMISSIONS, permission).isSuccessful

    override fun getName(): String = this.user.name

    override fun getTown(): Optional<Town> =
        this.user.get(TownshipKeys.TOWN)
        .flatMap { TownService.getInstance().getTown(it) }

    override fun hasTown(): Boolean =
        this.user.get(TownshipKeys.TOWN).isPresent

    override fun setTown(town: Town?) {
        if (town == null) {
            this.user.remove(TownshipKeys.TOWN)
        } else {
            this.user.offer(TownshipKeys.TOWN, town.uniqueId)
        }
    }

    override fun getTownRoles(): Collection<TownRole> =
        this.user.getSetOrEmpty(TownshipKeys.TOWN_ROLES)
        .mapNotNull { TownRoleService.getInstance().getRole(it).unwrap() }

    override fun hasRole(role: TownRole): Boolean =
        role.uniqueId in this.user.getSetOrEmpty(TownshipKeys.TOWN_ROLES)

    override fun addRole(role: TownRole): Boolean =
        this.user.offerSingle(TownshipKeys.TOWN_ROLES, role.uniqueId).isSuccessful

    override fun removeRole(role: TownRole): Boolean =
        this.user.removeSingle(TownshipKeys.TOWN_ROLES, role.uniqueId).isSuccessful

    override fun getUser(): User =
        Sponge.getServiceManager().provideUnchecked(UserStorageService::class.java)
            .getOrCreate(GameProfile.of(uniqueId))

    override fun getFriends(): Collection<UserResident> =
        this.user.getSetOrEmpty(TownshipKeys.FRIENDS)
        .mapNotNull { ResidentService.getInstance().getUserResident(it).unwrap() }

    override fun hasFriend(resident: UserResident): Boolean =
        resident.uniqueId in this.user.getSetOrEmpty(TownshipKeys.FRIENDS)

    override fun addFriend(resident: UserResident): Boolean =
        this.user.offerSingle(TownshipKeys.FRIENDS, resident.uniqueId).isSuccessful

    override fun removeFriend(resident: UserResident): Boolean =
        this.user.removeSingle(TownshipKeys.FRIENDS, resident.uniqueId).isSuccessful
}