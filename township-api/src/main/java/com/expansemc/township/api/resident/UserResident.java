package com.expansemc.township.api.resident;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * A member of a town, backed by a real player.
 */
public interface UserResident extends Resident {

    @Override
    default String getName() {
        return this.getUser().getName();
    }

    @Override
    default UUID getUniqueId() {
        return this.getUser().getUniqueId();
    }

    User getUser();

    Collection<UserResident> getFriends();

    boolean hasFriend(UserResident resident);

    boolean addFriend(UserResident resident);

    boolean removeFriend(UserResident resident);

    @Override
    default Optional<UniqueAccount> getAccount() {
        return Sponge.getServiceManager().provide(EconomyService.class)
                .flatMap(service -> service.getOrCreateAccount(this.getUniqueId()));
    }
}