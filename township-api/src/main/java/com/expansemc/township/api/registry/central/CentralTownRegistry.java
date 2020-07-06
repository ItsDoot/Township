package com.expansemc.township.api.registry.central;

import com.expansemc.township.api.TownshipAPI;
import com.expansemc.township.api.registry.type.TownRegistry;

public interface CentralTownRegistry extends TownRegistry.ArchetypeMutable {

    static CentralTownRegistry getInstance() {
        return TownshipAPI.getInstance().getTownRegistry();
    }
}