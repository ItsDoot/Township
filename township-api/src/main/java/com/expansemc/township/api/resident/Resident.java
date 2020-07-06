package com.expansemc.township.api.resident;

import com.expansemc.township.api.nation.Nation;
import com.expansemc.township.api.permission.PermissionHolder;
import com.expansemc.township.api.registry.type.RoleRegistry;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.util.NamedIdentifiable;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.text.channel.MessageReceiver;

import java.util.Optional;

/**
 * A member of a town.
 */
public interface Resident extends NamedIdentifiable, MessageReceiver, PermissionHolder {

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

    RoleRegistry.ArchetypeMutable<Town> getTownRoleRegistry();

    RoleRegistry.ArchetypeMutable<Nation> getNationRoleRegistry();

    /**
     * Gets the {@link Account} associated with this resident.
     *
     * @return The associated account, if available
     */
    Optional<? extends Account> getAccount();
}