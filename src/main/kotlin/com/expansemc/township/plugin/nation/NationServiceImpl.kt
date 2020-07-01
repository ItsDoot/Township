package com.expansemc.township.plugin.nation

import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.nation.NationService
import java.util.*

class NationServiceImpl : NationService {

    override fun getNations(): MutableCollection<Nation> {
        TODO("Not yet implemented")
    }

    override fun getNation(uniqueId: UUID?): Optional<Nation> {
        TODO("Not yet implemented")
    }

    override fun getNation(name: String?): Optional<Nation> {
        TODO("Not yet implemented")
    }

    override fun contains(nation: Nation?): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(nation: Nation?): Boolean {
        TODO("Not yet implemented")
    }

    override fun unregister(nation: Nation?): Boolean {
        TODO("Not yet implemented")
    }
}