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

public interface Claim extends PermissionOverrideHolder {

    UUID getWorldId();

    ServerWorld getWorld();

    Vector3i getChunkPosition();

    Town getTown();

    interface Builder extends ResettableBuilder<Claim, Builder> {

        Builder world(ServerWorld world);

        Builder chunkPosition(Vector3i chunkPosition);

        Builder town(Town town);

        Builder addPermissionOverride(PermissionHolder holder, Permission permission, boolean value);

        Claim build();
    }
}