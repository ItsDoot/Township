package com.expansemc.township.plugin

import com.expansemc.township.api.TownshipAPI
import com.expansemc.township.api.claim.ClaimService
import com.expansemc.township.api.nation.NationService
import com.expansemc.township.api.permission.WildernessRole
import com.expansemc.township.api.resident.ResidentService
import com.expansemc.township.api.town.TownService

data class SimpleTownshipAPI(
    private val residentService: ResidentService,
    private val claimService: ClaimService,
    private val townService: TownService,
    private val nationService: NationService
) : TownshipAPI {

    override fun getWildernessRole(): WildernessRole = TODO()

    override fun getResidentService(): ResidentService = this.residentService

    override fun getClaimService(): ClaimService = this.claimService

    override fun getTownService(): TownService = this.townService

    override fun getNationService(): NationService = this.nationService
}