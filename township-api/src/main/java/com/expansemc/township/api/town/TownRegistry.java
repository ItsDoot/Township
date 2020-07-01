package com.expansemc.township.api.town;

import com.expansemc.township.api.util.registry.Registry;
import com.expansemc.township.api.util.registry.NamedIdentifiableRegistry;
import org.spongepowered.api.text.channel.MessageReceiver;

/**
 * A collection of towns.
 */
public interface TownRegistry extends MessageReceiver, NamedIdentifiableRegistry<Town> {

    interface Mutable extends TownRegistry, Registry.Mutable<Town> {
    }
}