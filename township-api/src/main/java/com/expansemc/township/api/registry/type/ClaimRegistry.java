package com.expansemc.township.api.registry.type;

import com.expansemc.township.api.claim.Claim;
import com.expansemc.township.api.claim.ClaimArchetype;
import com.expansemc.township.api.registry.Registry;
import org.spongepowered.api.world.ServerLocation;
import org.spongepowered.api.world.server.ServerWorld;

import java.util.Collection;
import java.util.Optional;

public interface ClaimRegistry extends Registry<Claim> {

    /**
     * Gets all claims located in the provided {@link ServerWorld world}.
     *
     * @param world The world to get the claims from
     * @return The claims
     */
    Collection<Claim> getAllFor(ServerWorld world);

    /**
     * Gets the claim located at the provided {@link ServerLocation location}.
     *
     * @param location The location to get the claim from
     * @return The claim or empty if not found
     */
    Optional<Claim> get(ServerLocation location);

    interface ArchetypeMutable
            extends ClaimRegistry, Registry.ArchetypeMutable<Claim, ClaimArchetype> {
    }
}