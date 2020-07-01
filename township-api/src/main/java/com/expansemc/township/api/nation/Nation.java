package com.expansemc.township.api.nation;

import com.expansemc.township.api.bank.Bank;
import com.expansemc.township.api.permission.RoleCollection;
import com.expansemc.township.api.resident.Resident;
import com.expansemc.township.api.resident.ResidentCollection;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.warp.WarpCollection;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.ResettableBuilder;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface Nation extends Identifiable, Nameable, ResidentCollection, RoleCollection<NationRole>, WarpCollection<NationWarp>, Bank {

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
     * @return True if the action was successful
     */
    boolean setName(String name);

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
     * @return True if the action was successful
     */
    boolean setOpen(boolean open);

    /**
     * Gets the owner of this nation.
     *
     * @return The owner of this nation
     */
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
    boolean isOwner(Town town);

    /**
     * Sets the owner of the town.
     *
     * @param town The town to set
     * @return True if the action was successful
     */
    boolean setOwner(Town town);

    /**
     * Gets all towns in this nation.
     *
     * @return All towns in this nation
     */
    Collection<Town> getTowns();

    /**
     * Checks if the specified town is a member of this nation.
     *
     * @param town The town to check
     * @return True if the town is a member
     */
    boolean hasTown(Town town);

    /**
     * Adds the specified town as a member of this nation.
     *
     * @param town The town to add
     * @return True if the action was successful
     */
    boolean addTown(Town town);

    /**
     * Removes the specified town as a member of this nation.
     *
     * @param town The town to remove
     * @return True if the action was successful
     */
    boolean removeTown(Town town);

    interface Builder extends ResettableBuilder<Nation, Builder> {

        Builder name(String name);

        Builder open(boolean open);

        Builder owner(Town owner);

        Builder towns(Iterable<Town> towns);

        Builder towns(Town... towns);

        Nation build();
    }
}