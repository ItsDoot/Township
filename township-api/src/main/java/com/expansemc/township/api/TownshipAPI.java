package com.expansemc.township.api;

import com.expansemc.township.api.claim.ClaimService;
import com.expansemc.township.api.nation.NationService;
import com.expansemc.township.api.permission.WildernessRole;
import com.expansemc.township.api.resident.ResidentService;
import com.expansemc.township.api.town.TownService;
import org.spongepowered.api.Sponge;

/**
 * The primary access point for accessing Township's API.
 */
public interface TownshipAPI {

    static TownshipAPI getInstance() {
        return Sponge.getRegistry().getFactoryRegistry().provideFactory(TownshipAPI.class);
    }

    /**
     * Gets the role used for protection checks in unclaimed chunks.
     *
     * @return The wilderness role
     */
    WildernessRole getWildernessRole();

    /**
     * Gets the service used for managing residents.
     *
     * @return The resident service
     */
    ResidentService getResidentService();

    /**
     * Gets the service used for managing claims.
     *
     * @return The claim service
     */
    ClaimService getClaimService();

    /**
     * Gets the service used for managing towns.
     *
     * @return The town service
     */
    TownService getTownService();

    /**
     * Gets the service used for managing nations.
     *
     * @return The nation service.
     */
    NationService getNationService();
}