package com.expansemc.township.api.registry.central;

import com.expansemc.township.api.claim.Claim;
import com.expansemc.township.api.registry.type.ClaimRegistry;
import com.expansemc.township.api.town.Town;
import org.spongepowered.api.world.server.ServerWorld;

import java.util.Collection;

/**
 * Represents the central registry for managing claims (chunks owned by towns).
 */
public interface CentralClaimRegistry extends ClaimRegistry.Mutable {

    /**
     * Gets all claims located in the provided {@link Town town}.
     *
     * @param town The town to get the claims from
     * @return The claims
     */
    Collection<Claim> getAllFor(Town town);

    /**
     * Gets all claims located in the provided {@link Town town} and {@link ServerWorld world}.
     *
     * @param town The town to get the claims from
     * @param world The world to get the claims from
     * @return The claims
     */
    Collection<Claim> getAllFor(Town town, ServerWorld world);
}