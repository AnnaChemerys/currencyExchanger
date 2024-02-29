package ua.chemerys.currencyexchanger.service;

import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.webDto.WebTransaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    List<Transaction> findAll();

    Transaction findById(long theId);

    void save(Transaction transaction);

    Transaction addTransaction(WebTransaction webTransaction);

    BigDecimal calculateCommissionFee(String userName, WebTransaction webTransaction);


    List<Transaction> findByUsername(String username);

    BigDecimal calculateSellFromReceive(BigDecimal receive, String receiveCurrencyCode, String sellCurrencyCode);

    BigDecimal calculateReceiveFromSell(BigDecimal sell, String sellCurrencyCode, String receiveCurrencyCode);

    BigDecimal calculateCoefficientForCurrenciesExceptEUR(String receiveCurrencyCode, String sellCurrencyCode);

    List<String> getCurrencyCodesAvailableToSellForCurrentUser(User currentUser);

}
