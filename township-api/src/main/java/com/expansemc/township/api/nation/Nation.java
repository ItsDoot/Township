package com.expansemc.township.api.nation;

import com.expansemc.township.api.bank.Bank;
import com.expansemc.township.api.registry.type.RoleRegistry;
import com.expansemc.township.api.resident.Resident;
import com.expansemc.township.api.registry.type.ResidentRegistry;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.registry.type.TownRegistry;
import com.expansemc.township.api.util.NamedIdentifiable;
import com.expansemc.township.api.util.OwnedBy;
import com.expansemc.township.api.registry.type.WarpRegistry;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.util.ResettableBuilder;

/**
 * Represents a named grouping of a bank, towns, roles, and warps.
 */
public interface Nation extends NamedIdentifiable, MessageReceiver, OwnedBy<Town>, Bank {

    /**
     * Creates a new {@link Builder} to build a {@link Nation}.
     *
     * @return The new builder
     */
    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Builder.class);
    }

    /**
     * Gets the name of this nation.
     *
     * @return The name
     */
    @Override
    String getName();

    /**
     * Sets the name of this nation.
     *
     * @param name The name to set
     */
    void setName(String name);

    /**
     * Checks if this nation is joinable without an invitation.
     *
     * @return True if this nation is joinable without invite, false otherwise
     */
    boolean isOpen();

    /**
     * Sets whether the town is joinable without an invitation.
     *
     * @param open True if joinable without an invite, false otherwise
     */
    void setOpen(boolean open);

    /**
     * Gets the owner of this nation.
     *
     * @return The owner of this nation
     */
    @Override
    Town getOwner();

    /**
     * Gets the resident that owns this nation.
     *
     * @return The resident owner of this nation
     */
    default Resident getOwnerResident() {
        return this.getOwner().getOwner();
    }

    /**
     * Checks if the specified town is the owner of this nation.
     *
     * @param town The town to check
     * @return True if the town is the owner, false otherwise
     */
    @Override
    boolean isOwner(Town town);

    /**
     * Sets the owner of the town.
     *
     * @param town The town to set
     * @return True if the action was successful
     */
    @Override
    void setOwner(Town town);

    /**
     * Gets the registry used for managing this nation's towns.
     *
     * @return The town registry
     */
    TownRegistry.Mutable getTownRegistry();

    /**
     * Gets a read-only view into the residents in this nation's towns.
     *
     * @return The read-only resident registry view
     */
    ResidentRegistry getResidentRegistry();

    /**
     * Gets the registry used for managing this nation's roles.
     *
     * @return The role registry
     */
    RoleRegistry.Mutable<NationRole> getRoleRegistry();

    /**
     * Gets the registry used for managing this nation's warps.
     *
     * @return The warp registry
     */
    WarpRegistry.Mutable<NationWarp> getWarpRegistry();

    /**
     * Represents a builder to create {@link Nation}s.
     */
    interface Builder extends ResettableBuilder<Nation, Builder> {

        Builder name(String name);

        Builder open(boolean open);

        Builder owner(Town owner);

        Builder towns(Iterable<Town> towns);

        Builder towns(Town... towns);

        Nation build();
    }
}