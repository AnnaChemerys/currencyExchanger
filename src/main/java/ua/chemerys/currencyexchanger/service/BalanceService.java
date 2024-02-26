package ua.chemerys.currencyexchanger.service;

import ua.chemerys.currencyexchanger.entity.Balance;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface BalanceService {

    List<Balance> findAll();

    Balance findById(long theId);

    void save(Balance theBalance);

    void addBalance(Long theUserId, String currencyCode, BigDecimal sumOfReceive);

    void addBalance(String userName, String currencyCode, BigDecimal sumOfReceive);

//    void update(long theId, Balance theBalance);

    void updateUserBalances(User theUser, Transaction theTransaction);

    Balance findByTypeOfCurrency(String currencyCode);

    List<Balance> findByUser(User theUser);

//    Balance findByUserAndTypeOfCurrency(long theUserId, String currencyCode);
//
//    Balance getByUserIdAndCurrencyCode(long theUserId, String currencyCode);

    Balance getByUserAndCurrencyCode(User theUser, String currencyCode);

    Balance getByUserNameAndCurrencyCode(String userName, String currencyCode);
}
