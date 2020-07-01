package com.expansemc.township.api.town;

import com.expansemc.township.api.warp.Warp;

import java.util.UUID;

/**
 * A named teleport location owned by a town.
 */
public interface TownWarp extends Warp {

    /**
     * Gets the {@link UUID} of the town this warp is associated with.
     *
     * @return The associated town's id
     */
    UUID getTownId();

    /**
     * Gets the town this warp is associated with.
     *
     * @return The associated town
     */
    Town getTown();

    interface Builder extends Warp.Builder<TownWarp, Builder> {

        /**
         * Sets the town the warp is associated with.
         * 
         * @param town The town to use
         * @return This builder, for chaining
         */
        Builder town(Town town);
    }
}