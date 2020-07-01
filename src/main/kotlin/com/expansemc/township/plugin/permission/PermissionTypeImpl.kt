package com.expansemc.township.plugin.permission

import com.expansemc.township.api.permission.PermissionType
import org.spongepowered.api.CatalogKey
import org.spongepowered.plugin.PluginContainer

data class PermissionTypeImpl(private val key: CatalogKey) : PermissionType {

    override fun getKey(): CatalogKey = this.key

    class Builder : PermissionType.Builder {

        private var key: CatalogKey? = null

        override fun key(key: CatalogKey): PermissionType.Builder {
            this.key = key
            return this
        }

        override fun reset(): PermissionType.Builder {
            this.key = null
            return this
        }

        override fun build(): PermissionType = PermissionTypeImpl(
            key = checkNotNull(this.key) { "key" }
        )
    }

    companion object {
        fun getPermissionTypes(container: PluginContainer): Set<PermissionType> = setOf(
            PermissionType.of(CatalogKey.of(container, "claim")),
            PermissionType.of(CatalogKey.of(container, "nation")),
            PermissionType.of(CatalogKey.of(container, "town"))
        )
    }
}