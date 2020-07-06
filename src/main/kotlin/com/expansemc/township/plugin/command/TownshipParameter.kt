package com.expansemc.township.plugin.command

import com.expansemc.township.api.registry.NameableRegistry
import com.expansemc.township.api.registry.central.CentralResidentRegistry
import com.expansemc.township.api.registry.central.CentralTownRegistry
import com.expansemc.township.api.resident.Resident
import com.expansemc.township.api.town.Town
import com.expansemc.township.plugin.util.extension.first
import com.expansemc.township.plugin.util.extension.toResident
import com.expansemc.township.plugin.util.extension.typeToken
import com.expansemc.township.plugin.util.extension.unwrap
import org.spongepowered.api.command.CommandCause
import org.spongepowered.api.command.parameter.Parameter
import org.spongepowered.api.entity.living.player.server.ServerPlayer
import org.spongepowered.api.util.Nameable
import org.spongepowered.api.world.Locatable

object TownshipParameter {

    inline fun <reified T> key(key: String): Parameter.Key<T> =
        Parameter.key(key, typeToken<T>())

    inline fun <reified T : Nameable> registry(registry: NameableRegistry<T>, registryName: String): Parameter.Value.Builder<T> =
        Parameter.choices(
            T::class.java,
            { name -> registry.get(name).orElseThrow { IllegalArgumentException("No $registryName found with name '$name'") } },
            { registry.all.map { it.name } }
        )

    fun resident(): Parameter.Value.Builder<Resident> =
        registry(CentralResidentRegistry.getInstance(), "resident")

    fun residentOrSource(): Parameter.Value.Builder<Resident> =
        resident().orDefault { cause: CommandCause ->
            cause.cause.first<ServerPlayer>()?.user?.toResident()
        }

    fun town(): Parameter.Value.Builder<Town> =
        registry(CentralTownRegistry.getInstance(), "town")

    fun townOrSource(): Parameter.Value.Builder<Town> =
        town().orDefault { cause: CommandCause ->
            cause.cause.first<ServerPlayer>()?.user?.toResident()?.town?.unwrap()
        }
}