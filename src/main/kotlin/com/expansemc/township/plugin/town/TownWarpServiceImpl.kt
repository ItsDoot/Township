package com.expansemc.township.plugin.town

import com.expansemc.township.api.town.Town
import com.expansemc.township.api.town.TownWarp
import com.expansemc.township.api.town.TownWarpService
import java.util.*

class TownWarpServiceImpl : TownWarpService {

    override fun getWarps(): MutableCollection<TownWarp> {
        TODO("Not yet implemented")
    }

    override fun getWarp(uniqueId: UUID?): Optional<TownWarp> {
        TODO("Not yet implemented")
    }

    override fun getWarpsByTown(town: Town?): MutableCollection<TownWarp> {
        TODO("Not yet implemented")
    }

    override fun getWarp(town: Town?, name: String?): Optional<TownWarp> {
        TODO("Not yet implemented")
    }

    override fun contains(warp: TownWarp?): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(warp: TownWarp?): Boolean {
        TODO("Not yet implemented")
    }

    override fun unregister(warp: TownWarp?): Boolean {
        TODO("Not yet implemented")
    }
}