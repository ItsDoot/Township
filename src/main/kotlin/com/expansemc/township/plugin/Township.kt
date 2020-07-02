package com.expansemc.township.plugin

import com.expansemc.township.api.TownshipAPI
import com.expansemc.township.api.registry.central.CentralClaimRegistry
import com.expansemc.township.api.registry.central.CentralNationRegistry
import com.expansemc.township.api.permission.Permission
import com.expansemc.township.api.permission.PermissionType
import com.expansemc.township.api.registry.central.CentralResidentRegistry
import com.expansemc.township.api.registry.central.CentralTownRegistry
import com.expansemc.township.plugin.registry.central.CentralClaimRegistryImpl
import com.expansemc.township.plugin.listener.BlockListener
import com.expansemc.township.plugin.listener.ConnectionListener
import com.expansemc.township.plugin.permission.PermissionImpl
import com.expansemc.township.plugin.permission.PermissionTypeImpl
import com.expansemc.township.plugin.registry.central.CentralNationRegistryImpl
import com.expansemc.township.plugin.registry.central.CentralResidentRegistryImpl
import com.expansemc.township.plugin.registry.central.CentralTownRegistryImpl
import com.google.common.reflect.TypeToken
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.spongepowered.api.CatalogKey
import org.spongepowered.api.Sponge
import org.spongepowered.api.data.Key
import org.spongepowered.api.data.value.Value
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.lifecycle.ConstructPluginEvent
import org.spongepowered.api.event.lifecycle.StartingServerEvent
import org.spongepowered.api.event.registry.RegistryEvent
import org.spongepowered.plugin.PluginContainer
import org.spongepowered.plugin.jvm.Plugin
import java.util.*

@Plugin("township")
class Township {
    private val logger: Logger = LogManager.getLogger("township")

    private lateinit var container: PluginContainer

    private lateinit var residentRegistry: CentralResidentRegistry
    private lateinit var claimRegistry: CentralClaimRegistry
    private lateinit var townRegistry: CentralTownRegistry
    private lateinit var nationRegistry: CentralNationRegistry

    @Listener
    fun onConstruct(event: ConstructPluginEvent) {
        this.logger.info("Constructing plugin...")

        this.container = event.plugin

        this.logger.info("Loading services...")

        this.residentRegistry = CentralResidentRegistryImpl()
        this.claimRegistry = CentralClaimRegistryImpl()
        this.townRegistry = CentralTownRegistryImpl()
        this.nationRegistry = CentralNationRegistryImpl()
    }

    @Listener
    fun onRegisterFactories(event: RegistryEvent.Factory) {
        this.logger.info("Registering factories...")

        this.logger.info("Registering TownshipAPI...")

        val api = SimpleTownshipAPI(
            residentRegistry,
            claimRegistry,
            townRegistry,
            nationRegistry
        )

        event.register(TownshipAPI::class.java, api)
    }

    @Listener
    fun onRegisterBuilders(event: RegistryEvent.Builder) {
        this.logger.info("Registering builders...")

        event.register(PermissionType.Builder::class.java, PermissionTypeImpl::Builder)
        event.register(Permission.Builder::class.java, PermissionImpl::Builder)
    }

    @Listener
    fun onRegisterCatalogRegistries(event: RegistryEvent.CatalogRegistry) {
        this.logger.info("Registering catalog registries...")

        this.logger.info("Registering permissions...")

        event.register(PermissionType::class.java) {
            PermissionTypeImpl.getPermissionTypes(this.container)
        }

        event.register(Permission::class.java) {
            PermissionImpl.getPermissions(this.container)
        }
    }

    @Listener
    fun onRegisterKeys(event: RegistryEvent.Catalog<Key<*>>) {
        this.logger.info("Registering data keys...")

        event.register(keyOf<Set<UUID>, Value.Mutable<Set<UUID>>>("friends"))
        event.register(keyOf<Set<Permission>, Value.Mutable<Set<Permission>>>("permissions"))
        event.register(keyOf<UUID, Value.Mutable<UUID>>("town"))
        event.register(keyOf<Set<UUID>, Value.Mutable<Set<UUID>>>("town_roles"))
    }

    private inline fun <E, reified V : Value<E>> keyOf(keyValue: String): Key<V> =
        Key.builder()
            .type(object : TypeToken<V>() {})
            .key(CatalogKey.of(this.container, keyValue))
            .build()

    @Listener
    fun onStarting(event: StartingServerEvent) {
        this.logger.info("Registering listeners...")

        Sponge.getEventManager().registerListeners(this.container, BlockListener())
        Sponge.getEventManager().registerListeners(this.container, ConnectionListener())
    }
}