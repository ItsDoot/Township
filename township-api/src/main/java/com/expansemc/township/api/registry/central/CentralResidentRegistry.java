package com.expansemc.township.api.registry.central;

import com.expansemc.township.api.TownshipAPI;
import com.expansemc.township.api.registry.type.ResidentRegistry;
import com.expansemc.township.api.resident.UserResident;
import com.expansemc.township.api.resident.VirtualResident;
import org.spongepowered.api.entity.living.player.User;

public interface CentralResidentRegistry extends ResidentRegistry.ArchetypeMutable {

    static CentralResidentRegistry getInstance() {
        return TownshipAPI.getInstance().getResidentRegistry();
    }

    VirtualResident getSystemResident();

    UserResident getOrCreateUser(User user);

    VirtualResident getOrCreateVirtual(String name);
}