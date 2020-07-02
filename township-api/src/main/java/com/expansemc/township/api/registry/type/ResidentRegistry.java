package com.expansemc.township.api.registry.type;

import com.expansemc.township.api.registry.IdentifiableRegistry;
import com.expansemc.township.api.registry.Registry;
import com.expansemc.township.api.resident.Resident;
import com.expansemc.township.api.resident.UserResident;
import com.expansemc.township.api.resident.VirtualResident;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * An object which manages residents of a town, nation, etc.
 */
public interface ResidentRegistry extends IdentifiableRegistry<Resident> {

    Collection<UserResident> getAllUsers();

    Optional<UserResident> getUser(UUID uniqueId);

    Optional<UserResident> getUser(String name);

    Collection<VirtualResident> getAllVirtuals();

    Optional<VirtualResident> getVirtual(UUID uniqueId);

    Optional<VirtualResident> getVirtual(String name);

    interface Mutable extends ResidentRegistry, Registry.Mutable<Resident> {
    }
}