package com.expansemc.township.api.claim;

import com.expansemc.township.api.nation.Nation;
import com.expansemc.township.api.permission.PermissionOverrideHolder;
import com.expansemc.township.api.registry.type.WarpRegistry;
import com.expansemc.township.api.town.Town;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.math.vector.Vector3i;

/**
 * Represents a chunk owned by a town.
 */
public interface Claim extends PermissionOverrideHolder {

    /**
     * Gets the world this chunk is claimed in.
     *
     * @return The world
     */
    ServerWorld getWorld();

    /**
     * Gets the chunk position (player-x / 16, 0, player-z / 16) this chunk is claimed at.
     *
     * @return The chunk position
     */
    Vector3i getChunkPosition();

    /**
     * Gets the town that owns this chunk.
     *
     * @return The town
     */
    Town getTown();

    /**
     * Gets a read-only view into the registry of town warps located within
     * this claim.
     *
     * @return The town warp registry
     */
    WarpRegistry<Town> getTownWarpRegistry();

    /**
     * Gets a read-only view into the registry of nation warps located within
     * this claim.
     *
     * @return The nation warp registry
     */
    WarpRegistry<Nation> getNationWarpRegistry();
}