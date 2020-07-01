package com.expansemc.township.api.resident;

import org.spongepowered.api.text.channel.MessageReceiver;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * An object which manages residents of a town, nation, etc.
 */
public interface ResidentCollection extends MessageReceiver {

    /**
     * Gets all residents in the collection.
     *
     * @return All residents
     */
    Collection<Resident> getResidents();

    /**
     * Gets the resident with the specified {@link UUID} that's registered to
     * this collection, if available.
     *
     * @param uniqueId The unique id to find
     * @return The resident, if available
     */
    Optional<Resident> getResident(UUID uniqueId);

    /**
     * Checks whether the provided resident is a member of this collection.
     *
     * @param resident The resident to check
     * @return True if the resident is in this collection, false otherwise
     */
    boolean hasResident(Resident resident);
}