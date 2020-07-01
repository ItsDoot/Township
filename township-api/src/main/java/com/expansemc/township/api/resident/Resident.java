package com.expansemc.township.api.resident;

import com.expansemc.township.api.nation.Nation;
import com.expansemc.township.api.nation.NationRole;
import com.expansemc.township.api.permission.PermissionHolder;
import com.expansemc.township.api.permission.RoleRegistry;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.town.TownRole;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;

import java.util.Optional;

/**
 * A member of a town.
 */
public interface Resident extends Identifiable, Nameable, MessageReceiver, PermissionHolder {

    /**
     * Gets this resident's {@link Town}, if they are in one.
     *
     * @return This resident's town, if available
     */
    Optional<Town> getTown();

    /**
     * Checks whether this resident is part of a town.
     *
     * @return True if this resident has a town, false otherwise
     */
    boolean hasTown();

    /**
     * Sets the {@link Town} that this resident is part of.
     *
     * @param town This resident's new town
     */
    void setTown(@Nullable Town town);

    /**
     * Checks whether this resident is the owner of their town.
     *
     * @return True if this resident owns their town, false otherwise
     */
    default boolean isTownOwner() {
        return this.getTown().filter(town -> town.isOwner(this)).isPresent();
    }

    /**
     * Gets the {@link Nation} that this resident's town is part of.
     *
     * @return The belonging nation, if available
     */
    default Optional<Nation> getNation() {
        return this.getTown().flatMap(Town::getNation);
    }

    RoleRegistry.Mutable<TownRole> getTownRoles();

    RoleRegistry.Mutable<NationRole> getNationRoles();

    /**
     * Gets the {@link Account} associated with this resident.
     *
     * @return The associated account, if available
     */
    Optional<? extends Account> getAccount();
}