package com.expansemc.township.api.resident;

import com.expansemc.township.api.TownshipAPI;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface ResidentService {

    static ResidentService getInstance() {
        return TownshipAPI.getInstance().getResidentService();
    }

    Collection<UserResident> getUserResidents();

    Optional<UserResident> getUserResident(UUID uniqueId);

    Optional<UserResident> getUserResident(String name);

    UserResident getOrCreateUserResident(User user);

    Collection<VirtualResident> getVirtualResidents();

    Optional<VirtualResident> getVirtualResident(UUID uniqueId);

    Optional<VirtualResident> getVirtualResident(String name);

    VirtualResident getOrCreateVirtualResident(String name);

    default VirtualResident getSystemResident() {
        return this.getVirtualResident(Sponge.getSystemSubject().getIdentifier())
                .orElseThrow(() -> new IllegalStateException("Server has no system resident!"));
    }
}