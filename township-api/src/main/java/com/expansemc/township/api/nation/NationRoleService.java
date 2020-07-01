package com.expansemc.township.api.nation;

import com.expansemc.township.api.TownshipAPI;
import com.expansemc.township.api.permission.RoleService;
import org.spongepowered.api.Sponge;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface NationRoleService extends RoleService<NationRole, Nation> {

    static NationRoleService getInstance() {
        return TownshipAPI.getInstance().getNationRoleService();
    }
}