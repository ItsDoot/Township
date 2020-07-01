package com.expansemc.township.api.town;

import com.expansemc.township.api.TownshipAPI;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.ProvisioningException;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a service for managing towns.
 */
public interface TownService {

    /**
     * The {@link TownService} instance.
     *
     * @return The town service instance
     */
    static TownService getInstance() {
        return TownshipAPI.getInstance().getTownService();
    }

    /**
     * Gets all registered towns.
     *
     * @return All registered towns
     */
    Collection<Town> getTowns();

    /**
     * Gets the registered town with the specified unique id.
     *
     * @param uniqueId The UUID to find
     * @return The town, if available
     */
    Optional<Town> getTown(UUID uniqueId);

    /**
     * Gets the registered town with the specified name.
     *
     * @param name The name to find
     * @return The town, if available
     */
    Optional<Town> getTown(String name);

    /**
     * Checks whether the specified town has been registered to the service.
     *
     * @param town The town to check
     * @return True if the town has been registered
     */
    boolean hasTown(Town town);

    /**
     * Registers the specified town to the service.
     *
     * @param town The town to register
     * @return True if the town was successfully registered
     */
    boolean addTown(Town town);

    /**
     * Unregisters the specified town from the service.
     *
     * @param town The town to unregister
     * @return True if the town was successfully unregistered
     */
    boolean removeTown(Town town);
}