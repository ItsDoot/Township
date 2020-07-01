package com.expansemc.township.api.event.town;

import com.expansemc.township.api.event.TownshipEvent;
import com.expansemc.township.api.town.Town;

public interface TargetTownEvent extends TownshipEvent {

    Town getTown();
}