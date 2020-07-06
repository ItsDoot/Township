package com.expansemc.township.api.registry.type;

import com.expansemc.township.api.nation.Nation;
import com.expansemc.township.api.nation.NationArchetype;
import com.expansemc.township.api.registry.NamedIdentifiableRegistry;

/**
 * A registry of nations.
 */
public interface NationRegistry extends NamedIdentifiableRegistry<Nation> {

    interface ArchetypeMutable
            extends NationRegistry, NamedIdentifiableRegistry.ArchetypeMutable<Nation, NationArchetype> {
    }
}