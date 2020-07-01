package com.expansemc.township.api.warp;

import com.expansemc.township.api.util.registry.Registry;
import com.expansemc.township.api.util.registry.NamedIdentifiableRegistry;

/**
 * An object which manages warps of a certain type (town, nation, etc).
 *
 * @param <W> The warp type
 */
public interface WarpRegistry<W extends Warp> extends NamedIdentifiableRegistry<W> {

    interface Mutable<W extends Warp> extends WarpRegistry<W>, Registry.Mutable<W> {
    }
}