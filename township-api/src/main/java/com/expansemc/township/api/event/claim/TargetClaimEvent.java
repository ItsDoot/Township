package com.expansemc.township.api.event.claim;

import com.expansemc.township.api.claim.Claim;
import com.expansemc.township.api.event.TownshipEvent;

public interface TargetClaimEvent extends TownshipEvent {

    Claim getClaim();
}