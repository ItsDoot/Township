package com.expansemc.township.api.registry.type;

import com.expansemc.township.api.registry.NamedIdentifiableRegistry;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.town.TownArchetype;

/**
 * A collection of towns.
 */
public interface TownRegistry extends NamedIdentifiableRegistry<Town> {

    interface ArchetypeMutable
            extends TownRegistry, NamedIdentifiableRegistry.ArchetypeMutable<Town, TownArchetype> {
    }
}