package com.expansemc.township.api;

import com.expansemc.township.api.permission.WildernessRole;
import com.expansemc.township.api.registry.central.CentralClaimRegistry;
import com.expansemc.township.api.registry.central.CentralNationRegistry;
import com.expansemc.township.api.registry.central.CentralResidentRegistry;
import com.expansemc.township.api.registry.central.CentralTownRegistry;
import org.spongepowered.api.Sponge;

/**
 * The primary access point for Township's API.
 */
public interface TownshipAPI {

    /**
     * Gets the {@link TownshipAPI} instance.
     *
     * @return The api instance
     */
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
     * Gets the central registry for managing residents.
     *
     * @return The central resident registry
     */
    CentralResidentRegistry getResidentRegistry();

    /**
     * Gets the central registry for managing claims.
     *
     * @return The central claim registry
     */
    CentralClaimRegistry getClaimRegistry();

    /**
     * Gets the central registry used for managing towns.
     *
     * @return The central town registry
     */
    CentralTownRegistry getTownRegistry();

    /**
     * Gets the central registry used for managing nations.
     *
     * @return The central nation registry
     */
    CentralNationRegistry getNationRegistry();
}