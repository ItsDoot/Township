package com.expansemc.township.api.warp;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * An object which manages warps of a certain type (town, nation, etc).
 *
 * @param <W> The warp type
 */
public interface WarpCollection<W extends Warp> {

    /**
     * Gets all warps registered to this collection.
     *
     * @return All registered warps
     */
    Collection<W> getWarps();

    /**
     * Gets the warp with the specified {@link UUID} that's registered to this
     * collection, if available.
     *
     * @param uniqueId The unique id to find
     * @return The warp, if available
     */
    Optional<W> getWarp(UUID uniqueId);

    /**
     * Gets the warp with the specified name that's registered to this
     * collection, if available.
     *
     * @param name The name to find
     * @return The warp, if available
     */
    Optional<W> getWarp(String name);

    /**
     * Checks if the specified warp is registered to this collection.
     *
     * @param warp The warp to check
     * @return True if the warp is registered
     */
    boolean hasWarp(W warp);

    /**
     * Registers the specified warp to this collection.
     *
     * @param warp The warp to register
     * @return True if the registration was successful
     */
    boolean addWarp(W warp);

    /**
     * Unregisters the specified warp to this collection.
     *
     * @param warp The warp to unregister
     * @return True if the removal was successful
     */
    boolean removeWarp(W warp);
}