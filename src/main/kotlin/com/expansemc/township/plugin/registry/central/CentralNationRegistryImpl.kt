package com.expansemc.township.plugin.registry.central

import com.expansemc.township.api.registry.central.CentralNationRegistry
import com.expansemc.township.api.nation.Nation
import com.expansemc.township.plugin.registry.AbstractNamedIdentifiableRegistry

class CentralNationRegistryImpl : AbstractNamedIdentifiableRegistry<Nation>(),
    CentralNationRegistry