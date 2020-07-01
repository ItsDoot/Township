package com.expansemc.township.api.claim;

import com.expansemc.township.api.permission.Permission;
import com.expansemc.township.api.permission.PermissionHolder;
import com.expansemc.township.api.permission.PermissionOverrideHolder;
import com.expansemc.township.api.town.Town;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.math.vector.Vector3i;

import java.util.UUID;

/**
 * A chunk owned by a town.
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

    interface Builder extends ResettableBuilder<Claim, Builder> {

        Builder world(ServerWorld world);

        Builder chunkPosition(Vector3i chunkPosition);

        Builder town(Town town);

        Builder addPermissionOverride(PermissionHolder holder, Permission permission, boolean value);

        Claim build();
    }
}