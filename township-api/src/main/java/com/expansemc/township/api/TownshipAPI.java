package com.expansemc.township.api;

import com.expansemc.township.api.claim.ClaimService;
import com.expansemc.township.api.nation.NationService;
import com.expansemc.township.api.resident.ResidentService;
import com.expansemc.township.api.town.TownService;
import org.spongepowered.api.Sponge;

public interface TownshipAPI {

    static TownshipAPI getInstance() {
        return Sponge.getRegistry().getFactoryRegistry().provideFactory(TownshipAPI.class);
    }

    ResidentService getResidentService();

    ClaimService getClaimService();

    TownService getTownService();

    NationService getNationService();
}