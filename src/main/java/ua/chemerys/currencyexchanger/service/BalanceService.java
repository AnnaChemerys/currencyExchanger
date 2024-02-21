package ua.chemerys.currencyexchanger.service;

import ua.chemerys.currencyexchanger.entity.Balance;
//import ua.chemerys.currencyexchanger.entity.Currency;
import ua.chemerys.currencyexchanger.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface BalanceService {

    List<Balance> findAll();

    Balance findById(long theId);

    void save(Balance theBalance);

    void addBalance(Long theUserId, String currencyCode, BigDecimal sumOfReceive);

    void update(long theId, Balance theBalance);

    Balance findByTypeOfCurrency(String currencyCode);

//    Balance findByTypeOfCurrency(Currency typeOfCurrency);

    Set<Balance> findByUser(User theUser);

    Balance findByUserAndTypeOfCurrency(long theUserId, String currencyCode);

//    Balance findByUserAndTypeOfCurrency(User theUser, Currency theTypeOfCurrency);

    Balance getByUserIdAndCurrencyCode(long theUserId, String currencyCode);
}
