package com.expansemc.township.api.nation;

import com.expansemc.township.api.permission.RoleArchetype;
import com.expansemc.township.api.town.Town;
import com.expansemc.township.api.util.Archetype;
import com.expansemc.township.api.warp.WarpArchetype;
import org.spongepowered.api.util.Nameable;
import org.spongepowered.api.util.ResettableBuilder;

import java.util.Set;

public interface NationArchetype extends Archetype, Nameable {

    @Override
    String getName();

    boolean isOpen();

    Town getOwner();

    Set<Town> getTowns();

    Set<RoleArchetype> getRoles();

    Set<WarpArchetype> getWarps();

    interface Builder extends ResettableBuilder<NationArchetype, Builder> {

        Builder name(String name);

        Builder open(boolean open);

        Builder owner(Town owner);

        Builder towns(Iterable<Town> towns);

        Builder towns(Town... towns);

        Builder roles(Iterable<RoleArchetype> roles);

        Builder roles(RoleArchetype... roles);

        Builder warps(Iterable<WarpArchetype> warps);

        Builder warps(WarpArchetype... warps);

        NationArchetype build();
    }
}