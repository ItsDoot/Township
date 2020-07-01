package com.expansemc.township.api.nation;

import com.expansemc.township.api.TownshipAPI;
import org.spongepowered.api.Sponge;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a service for managing nations.
 */
public interface NationService {

    /**
     * The singleton service instance.
     *
     * @return The service instance
     */
    static NationService getInstance() {
        return TownshipAPI.getInstance().getNationService();
    }

    /**
     * Gets all registered nations.
     *
     * @return All registered nations
     */
    Collection<Nation> getNations();

    /**
     * Gets the registered nation with the specified unique id.
     *
     * @param uniqueId The UUID to find
     * @return The nation, if available
     */
    Optional<Nation> getNation(UUID uniqueId);

    /**
     * Gets the registered nation with the specified name.
     *
     * @param name The name to find
     * @return The nation, if available
     */
    Optional<Nation> getNation(String name);

    /**
     * Checks whether the specified nation has been registered to the service.
     *
     * @param nation The nation to check
     * @return True if the nation has been registered
     */
    boolean contains(Nation nation);

    /**
     * Registers the specified nation to the service.
     *
     * @param nation The nation to register
     * @return True if the nation was successfully registered
     */
    boolean register(Nation nation);

    /**
     * Unregisters the specified nation from the service.
     *
     * @param nation The nation to unregister
     * @return True if the nation was successfully unregistered
     */
    boolean unregister(Nation nation);
}