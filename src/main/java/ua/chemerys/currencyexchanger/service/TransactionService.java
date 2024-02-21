package ua.chemerys.currencyexchanger.service;

import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {

    List<Transaction> findAll();

    Transaction findById(long theId);

    void save(Transaction transaction);

    BigDecimal getBalance(String currencyCode, String username);

    BigDecimal convert(BigDecimal sellMoney, BigDecimal receiveMoney);

    BigDecimal calculateCommissionFee();

    boolean validateTransaction(BigDecimal sumForTransaction);

    List<Transaction> findByUsername(String username);

//    List<Transaction> findByUser(User theUser);

    BigDecimal calculateSellFromReceive(BigDecimal receive, String currencyCode);

    BigDecimal calculateReceiveFromSell(BigDecimal sell, String currencyCode);

    BigDecimal calculateCoefficientForCurrenciesExceptEUR(BigDecimal sellRate, BigDecimal receiveRate);

}
