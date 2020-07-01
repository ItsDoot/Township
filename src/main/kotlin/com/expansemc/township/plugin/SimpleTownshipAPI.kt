package com.expansemc.township.plugin

import com.expansemc.township.api.TownshipAPI
import com.expansemc.township.api.claim.ClaimService
import com.expansemc.township.api.nation.NationRoleService
import com.expansemc.township.api.nation.NationService
import com.expansemc.township.api.resident.ResidentService
import com.expansemc.township.api.town.TownRoleService
import com.expansemc.township.api.town.TownService
import com.expansemc.township.api.town.TownWarpService

data class SimpleTownshipAPI(
    private val residentService: ResidentService,
    private val claimService: ClaimService,
    private val townService: TownService,
    private val townRoleService: TownRoleService,
    private val townWarpService: TownWarpService,
    private val nationService: NationService,
    private val nationRoleService: NationRoleService
) : TownshipAPI {

    override fun getResidentService(): ResidentService = this.residentService

    override fun getClaimService(): ClaimService = this.claimService

    override fun getTownService(): TownService = this.townService

    override fun getTownRoleService(): TownRoleService = this.townRoleService

    override fun getTownWarpService(): TownWarpService = this.townWarpService

    override fun getNationService(): NationService = this.nationService

    override fun getNationRoleService(): NationRoleService = this.nationRoleService
}