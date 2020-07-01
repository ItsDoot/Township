package com.expansemc.township.api.nation;

import com.expansemc.township.api.TownshipAPI;
import org.spongepowered.api.Sponge;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface NationRoleService {

    static NationRoleService getInstance() {
        return TownshipAPI.getInstance().getNationRoleService();
    }

    Collection<NationRole> getRoles();

    Optional<NationRole> getRole(UUID uniqueId);

    Collection<NationRole> getRoles(Nation nation);

    Optional<NationRole> getRole(Nation nation, String name);

    boolean contains(NationRole role);

    boolean register(NationRole role);

    boolean unregister(NationRole role);
}