package com.expansemc.township.api.town;

import com.expansemc.township.api.bank.Bank;
import com.expansemc.township.api.nation.Nation;
import com.expansemc.township.api.registry.central.CentralClaimRegistry;
import com.expansemc.township.api.registry.type.ClaimRegistry;
import com.expansemc.township.api.registry.type.ResidentRegistry;
import com.expansemc.township.api.registry.type.RoleRegistry;
import com.expansemc.township.api.registry.type.WarpRegistry;
import com.expansemc.township.api.resident.Resident;
import com.expansemc.township.api.util.NamedIdentifiable;
import com.expansemc.township.api.util.OwnedBy;
import org.spongepowered.api.text.channel.MessageReceiver;

import java.util.Optional;

/**
 * Represents a named grouping of a bank, residents, roles, warps, and claims.
 */
public interface Town extends NamedIdentifiable, MessageReceiver, OwnedBy.Mutable<Resident>, Bank {

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

    /**
     * Gets the registry used for managing this town's residents.
     *
     * @return The resident registry
     */
    ResidentRegistry.Mutable getResidentRegistry();

    /**
     * Gets a read-only view into this town's claim registry.
     *
     * <p>Creating and deleting claims should be done on the {@link CentralClaimRegistry}.</p>
     *
     * @return The read-only claim registry view
     */
    ClaimRegistry getClaimRegistry();

    /**
     * Gets the registry used for managing this town's roles.
     *
     * @return The role registry
     */
    RoleRegistry.ArchetypeMutable<Town> getRoleRegistry();

    /**
     * Gets the registry used for managing this town's warps.
     *
     * @return The warp registry
     */
    WarpRegistry.ArchetypeMutable<Town> getWarpRegistry();

    /**
     * Gets the nation this town is part of.
     *
     * @return This town's nation
     */
    Optional<Nation> getNation();

    /**
     * Checks if this town is part of a nation.
     *
     * @return True if part of a nation, false otherwise
     */
    boolean hasNation();
}