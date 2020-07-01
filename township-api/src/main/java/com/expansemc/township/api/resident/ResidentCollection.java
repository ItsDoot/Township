package com.expansemc.township.api.resident;

import org.spongepowered.api.text.channel.MessageReceiver;

import java.util.Collection;

public interface ResidentCollection extends MessageReceiver {

    /**
     * Gets all residents in the collection.
     *
     * @return All residents
     */
    Collection<Resident> getResidents();
}