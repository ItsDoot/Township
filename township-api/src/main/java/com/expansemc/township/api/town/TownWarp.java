package com.expansemc.township.api.town;

import com.expansemc.township.api.warp.Warp;

import java.util.UUID;

public interface TownWarp extends Warp {

    /**
     * Gets the {@link UUID} of the town the warp is associated with.
     *
     * @return The associated town's id
     */
    UUID getTownId();

    /**
     * Gets the town the warp is associated with.
     *
     * @return The associated town
     */
    Town getTown();

    interface Builder extends Warp.Builder<TownWarp, Builder> {

        Builder town(Town town);
    }
}