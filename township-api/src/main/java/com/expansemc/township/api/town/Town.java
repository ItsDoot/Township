package com.expansemc.township.api.town;

import com.expansemc.township.api.claim.Claim;
import com.expansemc.township.api.claim.ClaimService;
import com.expansemc.township.api.resident.Resident;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.Account;
import org.spongepowered.api.text.channel.MessageReceiver;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.ServerLocation;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface Town extends Identifiable, MessageReceiver {

    static Builder builder() {
        return Sponge.getRegistry().getBuilderRegistry().provideBuilder(Town.Builder.class);
    }

    String getName();

    void setName(String name);

    boolean isOpen();

    void setOpen(boolean open);

    Resident getOwner();

    boolean isOwner(Resident resident);

    void setOwner(Resident resident);

    Collection<Resident> getResidents();

    boolean containsResident(Resident resident);

    boolean addResident(Resident resident);

    boolean removeResident(Resident resident);

    default Collection<Claim> getClaims() {
        return ClaimService.getInstance().getClaimsByTown(this);
    }

    default Optional<Claim> getClaimAt(ServerLocation location) {
        return ClaimService.getInstance().getClaimAt(location).filter(claim -> claim.getTown().equals(this));
    }

    TownRole getVisitorRole();

    default Collection<TownRole> getRoles() {
        return TownRoleService.getInstance().getRolesByTown(this);
    }

    default Optional<TownRole> getRole(String name) {
        return TownRoleService.getInstance().getRole(this, name);
    }

    default Collection<TownWarp> getWarps() {
        return TownWarpService.getInstance().getWarpsByTown(this);
    }

    default Optional<TownWarp> getWarp(String name) {
        return TownWarpService.getInstance().getWarp(this, name);
    }

    Optional<Account> getAccount();

    default BigDecimal getBalance(Currency currency) {
        return this.getAccount().map(account -> account.getBalance(currency)).orElse(BigDecimal.ZERO);
    }

    default BigDecimal getBalance() {
        return Sponge.getServiceManager().provide(EconomyService.class)
                .map(service -> this.getBalance(service.getDefaultCurrency()))
                .orElse(BigDecimal.ZERO);
    }

    interface Builder extends ResettableBuilder<Town, Builder> {

        Builder name(String name);

        Builder open(boolean open);

        Builder owner(Resident owner);

        Builder residents(Iterable<Resident> residents);

        Builder residents(Resident... residents);

        Town build();
    }
}