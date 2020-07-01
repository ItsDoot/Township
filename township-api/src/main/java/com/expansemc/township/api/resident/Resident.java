package com.expansemc.township.api.resident;

import com.expansemc.township.api.permission.PermissionHolder;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.town.TownRole;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;

import java.util.Collection;
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
     * Gets all town roles this resident is granted.
     *
     * @return All granted town roles
     */
    Collection<TownRole> getTownRoles();

    /**
     * Checks if the provided role is granted to this resident.
     *
     * @param role The role to check
     * @return True if the role is granted, false otherwise
     */
    boolean hasRole(TownRole role);

    /**
     * Grants the provided role to this resident.
     *
     * @param role The role to add
     * @return True if the role was successfully granted, false otherwise
     */
    boolean addRole(TownRole role);

    /**
     * Removes the provided role from this resident.
     *
     * @param role The role to remove
     * @return True if the role was successfully removed, false otherwise
     */
    boolean removeRole(TownRole role);

    /**
     * Gets the {@link Account} associated with this resident.
     *
     * @return The associated account, if available
     */
    Optional<? extends Account> getAccount();
}