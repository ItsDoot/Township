package com.expansemc.township.api.resident;

import com.expansemc.township.api.TownshipAPI;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.User;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a service for managing residents.
 */
public interface ResidentService {

    /**
     * The {@link ResidentService} instance.
     *
     * @return The resident service instance
     */
    static ResidentService getInstance() {
        return TownshipAPI.getInstance().getResidentService();
    }

    /**
     * Gets all registered user residents (residents backed by real players).
     *
     * @return All registered user residents.
     */
    Collection<UserResident> getUserResidents();

    /**
     * Gets a {@link UserResident} by their UUID.
     *
     * @param uniqueId The UUID to get the resident from
     * @return The {@link UserResident} or empty if not found
     */
    Optional<UserResident> getUserResident(UUID uniqueId);

    /**
     * Gets a {@link UserResident} by their name.
     *
     * @param name The name to get the resident from
     * @return The {@link UserResident} or empty if not found
     */
    Optional<UserResident> getUserResident(String name);

    /**
     * Gets a {@link UserResident} by their {@link User} object, or creates it
     * if it doesn't exist.
     *
     * @param user The user to get the resident from
     * @return The {@link UserResident}
     */
    UserResident getOrCreateUserResident(User user);

    /**
     * Gets all registered virtual (non-player) residents.
     *
     * @return All registered virtual residents.
     */
    Collection<VirtualResident> getVirtualResidents();

    /**
     * Gets a {@link VirtualResident} by their UUID.
     *
     * @param uniqueId The UUID to get the resident from
     * @return The {@link VirtualResident} or empty if not found
     */
    Optional<VirtualResident> getVirtualResident(UUID uniqueId);

    /**
     * Gets a {@link VirtualResident} by their name.
     *
     * @param name The name to get the resident from
     * @return The {@link VirtualResident} or empty if not found
     */
    Optional<VirtualResident> getVirtualResident(String name);

    /**
     * Gets a {@link VirtualResident} by their name, or creates it if it
     * doesn't exist.
     *
     * @param name The name to get the resident from
     * @return The {@link VirtualResident}
     */
    VirtualResident getOrCreateVirtualResident(String name);

    /**
     * Gets the system resident.
     *
     * @return The system resident.
     */
    default VirtualResident getSystemResident() {
        return this.getVirtualResident(Sponge.getSystemSubject().getIdentifier())
                .orElseThrow(() -> new IllegalStateException("Server has no system resident!"));
    }
}