package com.expansemc.township.api.town;

import com.expansemc.township.api.bank.Bank;
import com.expansemc.township.api.claim.Claim;
import com.expansemc.township.api.claim.ClaimService;
import com.expansemc.township.api.permission.RoleCollection;
import com.expansemc.township.api.resident.Resident;
import com.expansemc.township.api.resident.ResidentCollection;
import com.expansemc.township.api.warp.WarpCollection;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.ServerLocation;

import java.util.Collection;
import java.util.Optional;

/**
 * A named group of a bank, residents, roles, warps, and claims.
 */
public interface Town extends Identifiable, Nameable, ResidentCollection, RoleCollection<TownRole>, WarpCollection<TownWarp>, Bank {

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
    Resident getOwner();

    /**
     * Checks whether the provided resident is the owner of this town.
     *
     * @param resident The resident to check
     * @return True if the resident is the town owner, false otherwise
     */
    boolean isOwner(Resident resident);

    /**
     * Sets the owner of this town.
     *
     * @param resident The new town owner
     */
    void setOwner(Resident resident);

    /**
     * Adds the provided resident to this town.
     *
     * @param resident The resident to add.
     * @return True if the resident was successfully added, false otherwise
     */
    boolean addResident(Resident resident);

    /**
     * Removes the provided resident from this town.
     *
     * @param resident The resident to remove.
     * @return True if the resident was successfully removed, false otherwise
     */
    boolean removeResident(Resident resident);

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