package com.expansemc.township.api.town;

import com.expansemc.township.api.bank.Bank;
import com.expansemc.township.api.claim.Claim;
import com.expansemc.township.api.claim.ClaimService;
import com.expansemc.township.api.nation.Nation;
import com.expansemc.township.api.permission.RoleRegistry;
import com.expansemc.township.api.resident.Resident;
import com.expansemc.township.api.resident.ResidentRegistry;
import com.expansemc.township.api.util.OwnedBy;
import com.expansemc.township.api.warp.WarpRegistry;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.ServerLocation;

import java.util.Collection;
import java.util.Optional;

/**
 * A named group of a bank, residents, roles, warps, and claims.
 */
public interface Town extends Identifiable, Nameable, MessageReceiver, OwnedBy<Resident>, Bank {

    /**
     * Creates a new {@link Builder} to build a {@link Town}.
     *
     * @return The new builder
     */
    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Town.Builder.class);
    }

    /**
     * Gets the name of this town.
     *
     * @return The name
     */
    @Override
    String getName();

    /**
     * Sets the name of this town.
     *
     * @param name The new name
     */
    void setName(String name);

    /**
     * Gets whether this town is joinable without an invite.
     *
     * @return True if joinable without invitation
     */
    boolean isOpen();

    /**
     * Sets whether this town is joinable without an invite.
     *
     * @param open The new open status
     */
    void setOpen(boolean open);

    /**
     * Gets the owner of this town.
     *
     * @return The town owner
     */
    @Override
    Resident getOwner();

    /**
     * Checks whether the provided resident is the owner of this town.
     *
     * @param resident The resident to check
     * @return True if the resident is the town owner, false otherwise
     */
    @Override
    boolean isOwner(Resident resident);

    /**
     * Sets the owner of this town.
     *
     * @param resident The new town owner
     */
    @Override
    void setOwner(Resident resident);

    ResidentRegistry.Mutable getResidents();

    RoleRegistry.Mutable<TownRole> getRoles();

    WarpRegistry.Mutable<TownWarp> getWarps();

    /**
     * Gets the nation this town is part of.
     *
     * @return This town's nation
     */
    Optional<Nation> getNation();

    /**
     * Sets the nation this town is part of.
     *
     * @param nation The town's new nation, or null to leave the nation
     */
    void setNation(@Nullable Nation nation);

    /**
     * Gets all claims owned by this town.
     *
     * @return All owned claims
     */
    default Collection<Claim> getClaims() {
        return ClaimService.getInstance().getClaimsByTown(this);
    }

    /**
     * Gets the claim owned by this town at the provided location.
     *
     * @param location The location to get the claim from
     * @return The owned claim or empty if not found
     */
    default Optional<Claim> getClaimAt(ServerLocation location) {
        return ClaimService.getInstance().getClaimAt(location).filter(claim -> claim.getTown().equals(this));
    }

    interface Builder extends ResettableBuilder<Town, Builder> {

        /**
         * Sets the name of the town.
         *
         * @param name The name to use
         * @return This builder, for chaining
         */
        Builder name(String name);

        /**
         * Sets whether the town is joinable without an invite.
         *
         * @param open The open status to use
         * @return This builder, for chaining
         */
        Builder open(boolean open);

        /**
         * Sets the owner of the town.
         *
         * @param owner The resident to use
         * @return This builder, for chaining
         */
        Builder owner(Resident owner);

        /**
         * Sets the initial residents in the town.
         *
         * @param residents The residents to add
         * @return This builder, for chaining
         */
        Builder residents(Iterable<Resident> residents);

        /**
         * Sets the initial residents in the town.
         *
         * @param residents The residents to add
         * @return This builder, for chaining
         */
        Builder residents(Resident... residents);

        /**
         * Builds the {@link Town}.
         *
         * @return The built town
         * @throws IllegalStateException If not all required options were specified
         */
        Town build();
    }
}