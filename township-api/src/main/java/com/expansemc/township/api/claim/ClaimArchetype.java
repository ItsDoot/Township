package com.expansemc.township.api.claim;

import com.expansemc.township.api.permission.Permission;
import com.expansemc.township.api.permission.PermissionHolder;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.util.Archetype;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.math.vector.Vector3i;

import java.util.Map;

public interface ClaimArchetype extends Archetype {

    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Builder.class);
    }

    ServerWorld getWorld();

    Vector3i getChunkPosition();

    Town getTown();

    Map<PermissionHolder, Map<Permission, Boolean>> getOverrides();

    /**
     * Represents a builder to create {@link Claim}s.s
     */
    interface Builder extends ResettableBuilder<ClaimArchetype, Builder> {

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
        Builder addOverride(PermissionHolder holder, Permission permission, boolean value);

        /**
         * Builds the claim.
         *
         * @return The built claim
         * @throws IllegalStateException If not all required options were specified
         */
        ClaimArchetype build();
    }
}