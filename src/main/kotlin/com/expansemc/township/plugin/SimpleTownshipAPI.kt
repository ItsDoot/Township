package com.expansemc.township.plugin

import com.expansemc.township.api.TownshipAPI
import com.expansemc.township.api.registry.central.CentralClaimRegistry
import com.expansemc.township.api.registry.central.CentralNationRegistry
import com.expansemc.township.api.permission.WildernessRole
import com.expansemc.township.api.registry.central.CentralResidentRegistry
import com.expansemc.township.api.registry.central.CentralTownRegistry

data class SimpleTownshipAPI(
    private val residentRegistry: CentralResidentRegistry,
    private val claimRegistry: CentralClaimRegistry,
    private val townRegistry: CentralTownRegistry,
    private val nationRegistry: CentralNationRegistry
) : TownshipAPI {

    override fun getWildernessRole(): WildernessRole = TODO()

    override fun getResidentRegistry(): CentralResidentRegistry = this.residentRegistry

    override fun getClaimRegistry(): CentralClaimRegistry = this.claimRegistry

    override fun getTownRegistry(): CentralTownRegistry = this.townRegistry

    override fun getNationRegistry(): CentralNationRegistry = this.nationRegistry
}