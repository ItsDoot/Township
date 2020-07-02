package com.expansemc.township.api.claim;

import com.expansemc.township.api.nation.NationWarp;
import com.expansemc.township.api.permission.Permission;
import com.expansemc.township.api.permission.PermissionHolder;
import com.expansemc.township.api.permission.PermissionOverrideHolder;
import com.expansemc.township.api.registry.type.WarpRegistry;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.town.TownWarp;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.math.vector.Vector3i;

import java.util.UUID;

/**
 * Represents a chunk owned by a town.
 */
public interface Claim extends PermissionOverrideHolder {

    /**
     * Gets the {@link UUID} of the world this chunk is claimed in.
     *
     * @return The world UUID
     */
    UUID getWorldId();

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
    WarpRegistry<TownWarp> getTownWarpRegistry();

    /**
     * Gets a read-only view into the registry of nation warps located within
     * this claim.
     *
     * @return The nation warp registry
     */
    WarpRegistry<NationWarp> getNationWarpRegistry();

    /**
     * Represents a builder to create {@link Claim}s.s
     */
    interface Builder extends ResettableBuilder<Claim, Builder> {

        /**
         * Sets the world of the claim.
         *
         * @param world The world to use
         * @return This builder, for chaining
         */
        Builder world(ServerWorld world);

        /**
         * Sets the chunk position of the claim.
         *
         * @param chunkPosition The chunk position to use
         * @return This builder, for chaining
         */
        Builder chunkPosition(Vector3i chunkPosition);

        /**
         * Sets the owning town of the claim.
         *
         * @param town The town to use
         * @return This builder, for chaining
         */
        Builder town(Town town);

        /**
         * Adds a permission override to the claim.
         *
         * @param holder The holder to override for
         * @param permission The permission to override
         * @param value The overridden value
         * @return This builder, for chaining
         */
        Builder addPermissionOverride(PermissionHolder holder, Permission permission, boolean value);

        /**
         * Builds the claim.
         *
         * @return The built claim
         * @throws IllegalStateException If not all required options were specified
         */
        Claim build();
    }
}