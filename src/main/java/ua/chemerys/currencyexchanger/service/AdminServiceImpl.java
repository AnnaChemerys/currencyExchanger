package ua.chemerys.currencyexchanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.chemerys.currencyexchanger.entity.Balance;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.repository.BalanceRepository;
import ua.chemerys.currencyexchanger.repository.ExchangerDetailsRepository;
import ua.chemerys.currencyexchanger.repository.TransactionRepository;
import ua.chemerys.currencyexchanger.repository.UserRepository;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private TransactionRepository transactionRepository;

    private UserRepository userRepository;

    private BalanceRepository balanceRepository;

    private ExchangerDetailsRepository exchangerDetailsRepository;

    @Autowired
    public AdminServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository,
                            BalanceRepository balanceRepository,
                            ExchangerDetailsRepository exchangerDetailsRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.balanceRepository = balanceRepository;
        this.exchangerDetailsRepository = exchangerDetailsRepository;
    }

    @Override
    public void changeCommissionFee(float newCommissionFee) {
        exchangerDetailsRepository.findAll()
                .stream().findFirst()
                .ifPresent(exchangerDetails -> exchangerDetails.setCommissionFee(newCommissionFee));
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Balance> getAllBalances() {
        return balanceRepository.findAll();
    }

    @Override
    public void changeCountOfFreeTransactions(int newCountOfFreeTransactions) {
        exchangerDetailsRepository.findAll()
                .stream().findFirst()
                .ifPresent(exchangerDetails -> exchangerDetails.setCountOfFreeTransactions(newCountOfFreeTransactions));
    }
}
