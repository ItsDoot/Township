package com.expansemc.township.api.nation;

import com.expansemc.township.api.warp.Warp;

import java.util.UUID;

public interface NationWarp extends Warp {

    /**
     * Gets the {@link UUID} of the nation the warp is associated with.
     *
     * @return The associated nation's id
     */
    UUID getNationId();

    /**
     * Gets the nation the warp is associated with.
     *
     * @return The associated nation
     */
    Nation getNation();

    interface Builder extends Warp.Builder<NationWarp, Builder> {

        Builder nation(Nation nation);
    }
}