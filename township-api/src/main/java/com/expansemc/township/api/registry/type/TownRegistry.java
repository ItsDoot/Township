package com.expansemc.township.api.registry.type;

import com.expansemc.township.api.registry.NamedIdentifiableRegistry;
import com.expansemc.township.api.registry.Registry;
import com.expansemc.township.api.town.Town;

/**
 * A collection of towns.
 */
public interface TownRegistry extends NamedIdentifiableRegistry<Town> {

    interface Mutable extends TownRegistry, Registry.Mutable<Town> {
    }
}