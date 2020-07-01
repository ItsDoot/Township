package com.expansemc.township.api.claim;

import com.expansemc.township.api.TownshipAPI;
import com.expansemc.township.api.town.Town;
import org.spongepowered.api.world.ServerLocation;
import org.spongepowered.api.world.server.ServerWorld;

import java.util.Collection;
import java.util.Optional;

public interface ClaimService {

    static ClaimService getInstance() {
        return TownshipAPI.getInstance().getClaimService();
    }

    Optional<Claim> getClaimAt(ServerLocation location);

    Collection<Claim> getClaimsByWorld(ServerWorld world);

    Collection<Claim> getClaimsByTown(Town town);

    boolean contains(Claim claim);

    boolean register(Claim claim);

    boolean unregister(Claim claim);
}