package com.expansemc.township.api.town;

import com.expansemc.township.api.bank.Bank;
import com.expansemc.township.api.claim.Claim;
import com.expansemc.township.api.claim.ClaimService;
import com.expansemc.township.api.permission.RoleCollection;
import com.expansemc.township.api.resident.Resident;
import com.expansemc.township.api.resident.ResidentCollection;
import com.expansemc.township.api.warp.WarpCollection;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.util.Identifiable;
import org.spongepowered.api.util.ResettableBuilder;
import org.spongepowered.api.world.ServerLocation;

import java.util.Collection;
import java.util.Optional;

public interface Town extends Identifiable, ResidentCollection, RoleCollection<TownRole>, WarpCollection<TownWarp>, Bank {

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

    interface Builder extends ResettableBuilder<Town, Builder> {

        Builder name(String name);

        Builder open(boolean open);

        Builder owner(Resident owner);

        Builder residents(Iterable<Resident> residents);

        Builder residents(Resident... residents);

        Town build();
    }
}