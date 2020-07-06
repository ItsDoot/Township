package com.expansemc.township.api.registry.central;

import com.expansemc.township.api.TownshipAPI;
import com.expansemc.township.api.nation.Nation;
import com.expansemc.township.api.nation.NationArchetype;
import com.expansemc.township.api.registry.NamedIdentifiableRegistry;

public interface CentralNationRegistry extends NamedIdentifiableRegistry.ArchetypeMutable<Nation, NationArchetype> {

    static CentralNationRegistry getInstance() {
        return TownshipAPI.getInstance().getNationRegistry();
    }
}