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
}