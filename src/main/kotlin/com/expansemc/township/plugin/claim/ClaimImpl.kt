package com.expansemc.township.plugin.claim

import com.expansemc.township.api.claim.Claim
import com.expansemc.township.api.permission.Permission
import com.expansemc.township.api.permission.PermissionHolder
import com.expansemc.township.api.permission.PermissionTypes
import com.expansemc.township.api.permission.Role
import com.expansemc.township.api.town.Town
import com.expansemc.township.api.town.TownRole
import com.expansemc.township.api.town.TownService
import com.expansemc.township.plugin.util.toTristate
import com.google.common.collect.Table
import com.google.common.collect.Tables
import org.spongepowered.api.Sponge
import org.spongepowered.api.util.Tristate
import org.spongepowered.api.world.server.ServerWorld
import org.spongepowered.math.vector.Vector3i
import java.util.*

data class ClaimImpl(
    private val worldId: UUID,
    private val chunkPosition: Vector3i,
    private val townId: UUID
) : Claim {

    private val rolePermissionOverrides: Table<UUID, Permission, Boolean> =
        Tables.newCustomTable(HashMap()) { IdentityHashMap<Permission, Boolean>() }

    override fun getWorldId(): UUID = this.worldId

    override fun getWorld(): ServerWorld =
        Sponge.getServer().worldManager.getWorld(this.worldId)
            .orElseThrow { IllegalStateException("World $worldId is not loaded!") }

    override fun getChunkPosition(): Vector3i =
        this.chunkPosition

    override fun getTown(): Town =
        TownService.getInstance().getTown(this.townId)
            .orElseThrow { IllegalStateException("Town $townId is not loaded!") }

    override fun getPermissionOverrides(holder: PermissionHolder): Map<Permission, Boolean> {
        check(holder is TownRole) { "Only town roles can have claim overrides" }
        return this.rolePermissionOverrides.row(holder.uniqueId).toMap()
    }

    override fun getPermissionOverride(holder: PermissionHolder, permission: Permission): Tristate {
        check(holder is TownRole) { "Only town roles can have claim overrides" }
        check(permission.type == PermissionTypes.CLAIM) { "Claim overrides only work with claim permissions" }
        return this.rolePermissionOverrides.get(holder.uniqueId, permission).toTristate()
    }

    override fun setPermissionOverride(holder: PermissionHolder, permission: Permission, value: Boolean): Boolean {
        check(holder is TownRole) { "Only town roles can have claim overrides" }
        check(permission.type == PermissionTypes.CLAIM) { "Claim overrides only work with claim permissions" }
        this.rolePermissionOverrides.put(holder.uniqueId, permission, value)
        return true
    }

    override fun removePermissionOverride(holder: PermissionHolder, permission: Permission): Boolean {
        check(holder is TownRole) { "Only town roles can have claim overrides" }
        check(permission.type == PermissionTypes.CLAIM) { "Claim overrides only work with claim permissions" }
        return this.rolePermissionOverrides.remove(holder.uniqueId, permission) != null
    }
}