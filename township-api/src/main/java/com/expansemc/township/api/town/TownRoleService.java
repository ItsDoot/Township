package com.expansemc.township.api.town;

import com.expansemc.township.api.TownshipAPI;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface TownRoleService {

    static TownRoleService getInstance() {
        return TownshipAPI.getInstance().getTownRoleService();
    }

    Collection<TownRole> getRoles();

    Optional<TownRole> getRole(UUID uniqueId);

    Collection<TownRole> getRolesByTown(Town town);

    Optional<TownRole> getRole(Town town, String name);

    boolean contains(TownRole role);

    boolean register(TownRole role);

    boolean unregister(TownRole role);
}