package com.expansemc.township.plugin.permission

import com.expansemc.township.api.permission.Role
import com.expansemc.township.api.permission.RoleRegistry
import com.expansemc.township.plugin.util.wrap
import java.util.*

data class SimpleRoleRegistry<O, R : Role>(
    private val owner: O,
    private val visitorRole: R
) : RoleRegistry.Mutable<R> {

    private val rolesById = HashMap<UUID, R>()
    private val rolesByName = HashMap<String, R>()

    override fun getVisitorRole(): R = this.visitorRole

    override fun getAll(): Collection<R> =
        this.rolesById.values.toSet()

    override fun get(uniqueId: UUID): Optional<R> =
        this.rolesById[uniqueId].wrap()

    override fun get(name: String): Optional<R> =
        this.rolesByName[name].wrap()

    override fun contains(role: R): Boolean =
        role.uniqueId in this.rolesById

    override fun add(role: R): Boolean {
        if (role in this) return false

        this.rolesById[role.uniqueId] = role
        this.rolesByName[role.name] = role

        return true
    }

    override fun remove(role: R): Boolean {
        if (role !in this) return false

        this.rolesById.remove(role.uniqueId)
        this.rolesByName.remove(role.name)

        return true
    }
}