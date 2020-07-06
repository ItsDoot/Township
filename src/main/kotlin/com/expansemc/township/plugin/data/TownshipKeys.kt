package com.expansemc.township.plugin.data

import com.expansemc.township.api.permission.Permission
import org.spongepowered.api.Sponge
import org.spongepowered.api.data.Key
import org.spongepowered.api.data.value.SetValue
import org.spongepowered.api.data.value.Value
import java.util.*
import java.util.function.Supplier
import kotlin.reflect.KProperty

object TownshipKeys {
    val FRIENDS: Key<SetValue.Mutable<UUID>>
            by Sponge.getRegistry().catalogRegistry.provideSupplier(Key::class.java, "friends")

    val PERMISSIONS: Key<SetValue.Mutable<Permission>>
            by Sponge.getRegistry().catalogRegistry.provideSupplier(Key::class.java, "permissions")

    val TOWN: Key<Value.Mutable<UUID>>
            by Sponge.getRegistry().catalogRegistry.provideSupplier(Key::class.java, "town")

    val TOWN_ROLES: Key<SetValue.Mutable<UUID>>
            by Sponge.getRegistry().catalogRegistry.provideSupplier(Key::class.java, "town_roles")

    private operator fun <T> Supplier<T>.getValue(self: Any?, property: KProperty<*>): T = this.get()
}