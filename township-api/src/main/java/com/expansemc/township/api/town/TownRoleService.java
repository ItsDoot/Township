package com.expansemc.township.api.town;

import com.expansemc.township.api.TownshipAPI;
import com.expansemc.township.api.permission.RoleService;

public interface TownRoleService extends RoleService<TownRole, Town> {

    static TownRoleService getInstance() {
        return TownshipAPI.getInstance().getTownRoleService();
    }
}