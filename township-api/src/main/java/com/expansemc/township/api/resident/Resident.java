package com.expansemc.township.api.resident;

import com.expansemc.township.api.permission.PermissionHolder;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.town.TownRole;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.Nameable;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Optional;

/**
 * A member of a town.
 */
public interface Resident extends Identifiable, Nameable, MessageReceiver, PermissionHolder {

    Optional<Town> getTown();

    boolean hasTown();

    void setTown(@Nullable Town town);

    default boolean isTownOwner() {
        return this.getTown().filter(town -> town.isOwner(this)).isPresent();
    }

    Collection<TownRole> getTownRoles();

    boolean hasRole(TownRole role);

    boolean addRole(TownRole role);

    boolean removeRole(TownRole role);

    Optional<? extends Account> getAccount();
}