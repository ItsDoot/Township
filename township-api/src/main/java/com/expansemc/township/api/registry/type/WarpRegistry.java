package com.expansemc.township.api.registry.type;

import com.expansemc.township.api.registry.NamedIdentifiableRegistry;
import com.expansemc.township.api.warp.Warp;
import com.expansemc.township.api.warp.WarpArchetype;
import org.spongepowered.api.util.Identifiable;

/**
 * An object which manages warps of a certain type (town, nation, etc).
 *
 * @param <Owner> The warp owner type
 */
public interface WarpRegistry<Owner extends Identifiable> extends NamedIdentifiableRegistry<Warp<Owner>> {

    interface Mutable<Owner extends Identifiable>
            extends WarpRegistry<Owner>, NamedIdentifiableRegistry.Mutable<Warp<Owner>> {
    }

    interface ArchetypeMutable<Owner extends Identifiable>
            extends WarpRegistry<Owner>, NamedIdentifiableRegistry.ArchetypeMutable<Warp<Owner>, WarpArchetype> {
    }
}