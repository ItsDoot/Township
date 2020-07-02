package com.expansemc.township.plugin.registry

import com.expansemc.township.api.town.Town
import com.expansemc.township.api.registry.type.TownRegistry

open class TownRegistryImpl : AbstractNamedIdentifiableRegistry<Town>(), TownRegistry.Mutable