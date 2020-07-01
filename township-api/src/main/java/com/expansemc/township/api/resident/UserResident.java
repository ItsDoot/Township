package com.expansemc.township.api.resident;

import com.expansemc.township.api.claim.ClaimService;
import com.expansemc.township.api.permission.Permission;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;
import org.spongepowered.api.util.Tristate;

import java.util.Optional;
import java.util.UUID;

/**
 * A member of a town, backed by a real player.
 */
public interface UserResident extends Resident {

    /**
     * {@inheritDoc}
     */
    @Override
    default String getName() {
        return this.getUser().getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    default UUID getUniqueId() {
        return this.getUser().getUniqueId();
    }

    /**
     * Gets the {@link User} that backs this resident.
     *
     * @return The associated user
     */
    User getUser();

    ResidentRegistry.Mutable getFriends();

    /**
     * {@inheritDoc}
     */
    @Override
    default Optional<UniqueAccount> getAccount() {
        return Sponge.getServiceManager().provide(EconomyService.class)
                .flatMap(service -> service.getOrCreateAccount(this.getUniqueId()));
    }

    /**
     * Gets the tristate value that represents whether this resident has the
     * provided permission, based on their current location.
     *
     * @param permission The permission to check
     * @return The tristate value
     */
    default Tristate getPermissionValueHere(Permission permission) {
        return this.getUser().getPlayer()
                .flatMap(player -> ClaimService.getInstance().getClaimAt(player.getServerLocation()))
                .map(claim -> this.getPermissionValue(permission, claim))
                .orElse(Tristate.UNDEFINED);
    }

    /**
     * Checks whether this resident has the provided permission, based on their
     * current location.
     *
     * @param permission The permission to check
     * @return True if this resident is granted permission, false otherwise
     */
    default boolean hasPermissionHere(Permission permission) {
        return this.getPermissionValueHere(permission).asBoolean();
    }
}