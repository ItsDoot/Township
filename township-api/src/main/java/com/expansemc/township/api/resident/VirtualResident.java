package com.expansemc.township.api.resident;

/**
 * A member of a town with no player backing. Useful for NPC towns.
 */
public interface VirtualResident extends Resident {

    /**
     * Sets the name that represents this resident.
     *
     * @param name The new name to use
     */
    void setName(String name);
}