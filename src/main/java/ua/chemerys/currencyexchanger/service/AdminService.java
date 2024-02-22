package ua.chemerys.currencyexchanger.service;

import ua.chemerys.currencyexchanger.entity.Balance;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;

import java.util.List;

public interface AdminService {

    void changeCommissionFee(float newCommissionFee);

    void changeCountOfFreeTransactions(int newCountOfFreeTransactions);

    List<User> getAllUsers();

    List<Transaction> getAllTransactions();

    List<Balance> getAllBalances();

    List<Transaction> getTransactionsByUser(User theUser);

    List<Balance> getBalanceByUser(User theUser);
}
