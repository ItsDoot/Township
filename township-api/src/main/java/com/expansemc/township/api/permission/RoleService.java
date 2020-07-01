package com.expansemc.township.api.permission;

import org.spongepowered.api.util.Identifiable;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface RoleService<R extends Role, E extends Identifiable> {

    Collection<R> getRoles();

    Optional<R> getRole(UUID uniqueId);

    Collection<R> getRolesFor(E entity);

    Optional<R> getRole(E entity, String name);

    boolean contains(R role);

    boolean register(R role);

    boolean unregister(R role);
}