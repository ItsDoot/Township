package com.expansemc.township.plugin.nation

import com.expansemc.township.api.nation.Nation
import com.expansemc.township.api.nation.NationService
import java.util.*

class NationServiceImpl : NationService {

    private val nationsById = HashMap<UUID, Nation>()
    private val nationsByName = HashMap<String, Nation>()

    override fun getNations(): Collection<Nation> =
        this.nationsById.values.toSet()

    override fun getNation(uniqueId: UUID): Optional<Nation> =
        Optional.ofNullable(this.nationsById[uniqueId])

    override fun getNation(name: String): Optional<Nation> =
        Optional.ofNullable(this.nationsByName[name])

    override fun hasNation(nation: Nation): Boolean =
        nation.uniqueId in this.nationsById

    override fun addNation(nation: Nation): Boolean {
        if (this.hasNation(nation)) {
            return false
        }

        this.nationsById[nation.uniqueId] = nation
        this.nationsByName[nation.name] = nation

        // TODO set towns' nation

        return true
    }

    override fun removeNation(nation: Nation): Boolean {
        if (!this.hasNation(nation)) {
            return false
        }

        this.nationsById.remove(nation.uniqueId)
        this.nationsByName.remove(nation.name)

        // TODO unset towns' nation

        return true
    }
}