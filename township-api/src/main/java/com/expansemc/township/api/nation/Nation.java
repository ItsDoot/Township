package com.expansemc.township.api.nation;

import com.expansemc.township.api.bank.Bank;
import com.expansemc.township.api.permission.RoleRegistry;
import com.expansemc.township.api.resident.Resident;
import com.expansemc.township.api.resident.ResidentRegistry;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.town.TownRegistry;
import com.expansemc.township.api.util.OwnedBy;
import com.expansemc.township.api.warp.WarpRegistry;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.ResettableBuilder;

public interface Nation
        extends Identifiable, Nameable, MessageReceiver, OwnedBy<Town>, Bank {

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
     * @return True if this nation is joinable without an invite
     */
    boolean isOpen();

    /**
     * Sets whether the town is joinable without an invitation.
     *
     * @param open True if joinable without an invite
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
     * @return True if the town is the owner
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

    TownRegistry.Mutable getTowns();

    ResidentRegistry getResidents();

    RoleRegistry.Mutable<NationRole> getRoles();

    WarpRegistry.Mutable<NationWarp> getWarps();

    interface Builder extends ResettableBuilder<Nation, Builder> {

        Builder name(String name);

        Builder open(boolean open);

        Builder owner(Town owner);

        Builder towns(Iterable<Town> towns);

        Builder towns(Town... towns);

        Nation build();
    }
}