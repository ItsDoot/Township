package com.expansemc.township.api.registry.type;

import com.expansemc.township.api.permission.Role;
import com.expansemc.township.api.permission.RoleArchetype;
import com.expansemc.township.api.registry.NamedIdentifiableRegistry;
import org.spongepowered.api.util.Identifiable;

/**
 * An object which manages roles of a certain type (town, nation, etc).
 *
 * @param <Owner> The role owner type
 */
public interface RoleRegistry<Owner extends Identifiable> extends NamedIdentifiableRegistry<Role<Owner>> {

    /**
     * The base role that all residents are affected by.
     *
     * @return The base role
     */
    Role<Owner> getVisitorRole();

    interface Mutable<Owner extends Identifiable>
            extends RoleRegistry<Owner>, NamedIdentifiableRegistry.Mutable<Role<Owner>> {
    }

    interface ArchetypeMutable<Owner extends Identifiable>
            extends RoleRegistry<Owner>, NamedIdentifiableRegistry.ArchetypeMutable<Role<Owner>, RoleArchetype> {
    }
}