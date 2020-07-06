package com.expansemc.township.api.town;

import com.expansemc.township.api.claim.ClaimArchetype;
import com.expansemc.township.api.permission.RoleArchetype;
import com.expansemc.township.api.resident.Resident;
import com.expansemc.township.api.util.Archetype;
import com.expansemc.township.api.warp.WarpArchetype;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.ResettableBuilder;

import java.util.Set;

/**
 * An unregistered town.
 */
public interface TownArchetype extends Archetype, Nameable {

    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Builder.class);
    }

    @Override
    String getName();

    /**
     * Gets whether the town should be joinable without invitation by default.
     *
     * @return True if joinable without invitation, false otherwise
     */
    boolean isOpen();

    /**
     * Gets the owner of the town by default.
     *
     * @return The town owner
     */
    Resident getOwner();

    /**
     * Gets the residents in the town by default.
     *
     * @return The town residents
     */
    Set<Resident> getResidents();

    Set<ClaimArchetype> getClaims();

    Set<RoleArchetype> getRoles();

    Set<WarpArchetype> getWarps();

    interface Builder extends ResettableBuilder<TownArchetype, Builder> {

        Builder name(String name);

        Builder open(boolean open);

        Builder owner(Resident owner);

        Builder residents(Iterable<Resident> residents);

        Builder residents(Resident... residents);

        Builder claims(Iterable<ClaimArchetype> claims);

        Builder claims(ClaimArchetype... claims);

        Builder roles(Iterable<RoleArchetype> roles);

        Builder roles(RoleArchetype... roles);

        Builder warps(Iterable<WarpArchetype> warps);

        Builder warps(WarpArchetype... warps);

        TownArchetype build();
    }
}