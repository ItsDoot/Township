package com.expansemc.township.api.bank;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.Account;

import java.math.BigDecimal;
import java.util.Optional;

public interface Bank {

    /**
     * Gets the {@link Account} associated with the bank, if available.
     *
     * @return The associated account, if available
     */
    Optional<Account> getAccount();

    /**
     * Gets the bank's current balance in the specified currency.
     *
     * @param currency The currency to check
     * @return The current balance, or zero if no economy plugin is registered
     */
    default BigDecimal getBalance(Currency currency) {
        return this.getAccount()
                .map(account -> account.getBalance(currency))
                .orElse(BigDecimal.ZERO);
    }

    /**
     * Gets the bank's current balance in the default currency.
     *
     * @return The current balance, or zero if no economy plugin is registered
     */
    default BigDecimal getBalance() {
        return Sponge.getServiceManager().provide(EconomyService.class)
                .map(service -> this.getBalance(service.getDefaultCurrency()))
                .orElse(BigDecimal.ZERO);
    }
}