package com.expansemc.township.api.registry.type;

import com.expansemc.township.api.registry.NamedIdentifiableRegistry;
import com.expansemc.township.api.registry.Registry;
import com.expansemc.township.api.warp.Warp;

/**
 * An object which manages warps of a certain type (town, nation, etc).
 *
 * @param <W> The warp type
 */
public interface WarpRegistry<W extends Warp> extends NamedIdentifiableRegistry<W> {

    interface Mutable<W extends Warp> extends WarpRegistry<W>, Registry.Mutable<W> {
    }
}