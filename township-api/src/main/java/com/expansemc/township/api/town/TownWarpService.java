package com.expansemc.township.api.town;

import com.expansemc.township.api.TownshipAPI;
import org.spongepowered.api.Sponge;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a service for managing town warps.
 */
public interface TownWarpService {

    /**
     * The singleton service instance.
     *
     * @return The service instance
     */
    static TownWarpService getInstance() {
        return TownshipAPI.getInstance().getTownWarpService();
    }

    /**
     * Gets all registered warps.
     *
     * @return All registered warps
     */
    Collection<TownWarp> getWarps();

    /**
     * Gets the registered warp with the specified unique id.
     *
     * @param uniqueId The UUID to find
     * @return The warp, if available
     */
    Optional<TownWarp> getWarp(UUID uniqueId);

    /**
     * Gets all warps registered to the specified town.
     *
     * @param town The town to look under
     * @return All warps registered to the town
     */
    Collection<TownWarp> getWarpsByTown(Town town);

    /**
     * Gets the registered warp in the specified town with the specified name.
     *
     * @param town The town to look under
     * @param name The name to find
     * @return The warp, if available
     */
    Optional<TownWarp> getWarp(Town town, String name);

    /**
     * Checks whether the specified warp has been registered to the service.
     *
     * @param warp The warp to check
     * @return True if the warp has been registered
     */
    boolean contains(TownWarp warp);

    /**
     * Registers the specified warp to the service.
     *
     * @param warp The warp to register
     * @return True if the warp was successfully registered
     */
    boolean register(TownWarp warp);

    /**
     * Unregisters the specified warp from the service.
     *
     * @param warp The warp to unregister
     * @return True if the warp was successfully unregistered
     */
    boolean unregister(TownWarp warp);
}