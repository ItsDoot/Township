package com.expansemc.township.api.claim;

import com.expansemc.township.api.TownshipAPI;
import com.expansemc.township.api.town.Town;
import org.spongepowered.api.world.ServerLocation;
import org.spongepowered.api.world.server.ServerWorld;

import java.util.Collection;
import java.util.Optional;

/**
 * Represents a service for managing claims (chunks owned by towns).
 */
public interface ClaimService {

    /**
     * Gets the {@link ClaimService} instance.
     *
     * @return The claim service instance
     */
    static ClaimService getInstance() {
        return TownshipAPI.getInstance().getClaimService();
    }

    /**
     * Gets the claim at the given {@link ServerLocation}.
     *
     * @param location The location to get the claim from
     * @return The {@link Claim} or empty if not found
     */
    Optional<Claim> getClaimAt(ServerLocation location);

    /**
     * Gets all claims located in the given world.
     *
     * @param world The world to get the claims from
     * @return All claims in the given world
     */
    Collection<Claim> getClaimsByWorld(ServerWorld world);

    /**
     * Gets all claims owned by the given town.
     *
     * @param town The town to get the claims from
     * @return All owned claims
     */
    Collection<Claim> getClaimsByTown(Town town);

    /**
     * Checks whether the given claim is registered to this service.
     *
     * @param claim The claim to check
     * @return True if the claim exists in this claim service, false otherwise
     */
    boolean hasClaim(Claim claim);

    /**
     * Registers the given claim to this service.
     *
     * @param claim The claim to register
     * @return True if the registration was successful, false otherwise
     */
    boolean addClaim(Claim claim);

    /**
     * Unregisters the given claim from this service.
     *
     * @param claim The claim to unregister
     * @return True if the removal was successful, false otherwise
     */
    boolean removeClaim(Claim claim);
}