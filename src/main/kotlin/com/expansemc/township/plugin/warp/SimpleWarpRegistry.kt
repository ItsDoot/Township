package com.expansemc.township.plugin.warp

import com.expansemc.township.api.warp.Warp
import com.expansemc.township.api.registry.type.WarpRegistry
import com.expansemc.township.plugin.registry.AbstractNamedIdentifiableRegistry

class SimpleWarpRegistry<W : Warp> : AbstractNamedIdentifiableRegistry<W>(), WarpRegistry.Mutable<W>