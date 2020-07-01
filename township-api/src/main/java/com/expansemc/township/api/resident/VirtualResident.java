package com.expansemc.township.api.resident;

import org.spongepowered.api.service.economy.account.Account;

import java.util.Optional;

public interface VirtualResident extends Resident {

    void setName(String name);

    @Override
    Optional<? extends Account> getAccount();
}