package ua.chemerys.currencyexchanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.chemerys.currencyexchanger.entity.SumUnit;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.repository.*;
import ua.chemerys.currencyexchanger.util.RatesParser;
import ua.chemerys.currencyexchanger.webDto.WebTransaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private UserRepository userRepository;

    private ExchangerDetailsRepository exchangerDetailsRepository;

    private BalanceRepository balanceRepository;

    private SumUnitRepository sumUnitRepository;

    private RatesParser ratesParser;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository,
                                  ExchangerDetailsRepository exchangerDetailsRepository,
                                  BalanceRepository balanceRepository, SumUnitRepository sumUnitRepository,
                                  RatesParser ratesParser) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.exchangerDetailsRepository = exchangerDetailsRepository;
        this.balanceRepository = balanceRepository;
        this.sumUnitRepository = sumUnitRepository;
        this.ratesParser = ratesParser;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction findById(long theId) {
        Optional<Transaction> result = transactionRepository.findById(theId);

        Transaction theTransaction;

        if (result.isPresent()) {
            theTransaction = result.get();
        } else {
            throw new RuntimeException("Did not find transaction id " + theId);
        }

        return theTransaction;
    }

    @Override
    public void save(Transaction transaction) {

        transactionRepository.save(transaction);

    }

    //    @Override
//    public void save(WebTransaction webTransaction) {
//
//        Transaction transaction = new Transaction();
//
//        transaction.
//    }
//
    @Override
    public Transaction addTransaction(WebTransaction webTransaction) {

        User currentUser = userRepository.findByUserName(webTransaction.getUserName());

        Transaction newTransaction = new Transaction();
        SumUnit sellSumUnit = new SumUnit();
        SumUnit receiveSumUnit = new SumUnit();

        sellSumUnit.setAmountOfMoney(webTransaction.getSellSum());
        sellSumUnit.setCurrencyCode(webTransaction.getSellCurrencyCode());
        sellSumUnit.setTransaction(newTransaction);

        receiveSumUnit.setAmountOfMoney(webTransaction.getReceiveSum());
        receiveSumUnit.setCurrencyCode(webTransaction.getReceiveCurrencyCode());
        receiveSumUnit.setTransaction(newTransaction);

        newTransaction.setSell(sellSumUnit);
        newTransaction.setReceive(receiveSumUnit);
        newTransaction.setUser(currentUser);
        newTransaction.setCalculatedCommissionFee(calculateCommissionFee(webTransaction.getUserName(), webTransaction));
        newTransaction.setCreationTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        newTransaction.setExchangerDetails(exchangerDetailsRepository.findAll().stream().findFirst().get());

        transactionRepository.save(newTransaction);

        return newTransaction;

    }

//    @Override
//    public BigDecimal getBalance(String username, String currencyCode) {
//
//        return userRepository.findByUserName(username).getUserBalances().stream()
//                .filter(balance -> balance.getCurrencyCode().equals(currencyCode)).findFirst().get()
//                .getSumOnTheBalance();
//    }

//    @Override
//    public BigDecimal convert(BigDecimal sellMoney, BigDecimal receiveMoney) {
//        return null;
//    }

//    @Override
//    public BigDecimal convert(BigDecimal sellMoney, BigDecimal receiveMoney, String userName) {
////        if (validateTransaction(sellMoney)) {
////
////        }
//
//        userRepository.findByUserName(userName).setCountOfTransactions(userRepository.findByUserName(userName).getCountOfTransactions() + 1);
//        return null;
//    }

    @Override
    public BigDecimal calculateCommissionFee(String userName, WebTransaction webTransaction) {

        float commissionFee = exchangerDetailsRepository.findAll().stream().findFirst().get().getCommissionFee();
        BigDecimal calculatedCommissionFee;

        if (userRepository.findByUserName(userName).getCountOfTransactions() < 5) {
            calculatedCommissionFee = BigDecimal.valueOf(0);
        } else {
            calculatedCommissionFee = webTransaction.getSellSum()
                    .multiply(BigDecimal.valueOf(commissionFee)).
                    divide(BigDecimal.valueOf(100))
                    .setScale(2, RoundingMode.HALF_DOWN);
        }
        return calculatedCommissionFee;
    }

//    @Override
//    public BigDecimal calculateCommissionFee(String userName, Transaction transaction) {
//
//        float commissionFee = exchangerDetailsRepository.findAll().stream().findFirst().get().getCommissionFee();
//        BigDecimal calculatedCommissionFee;
//
//        if (userRepository.findByUserName(userName).getCountOfTransactions() < 5) {
//            calculatedCommissionFee = BigDecimal.valueOf(0);
//        } else {
//            calculatedCommissionFee = transaction.getSell().getAmountOfMoney()
//                    .multiply(BigDecimal.valueOf(commissionFee)).
//                    divide(BigDecimal.valueOf(100))
//                    .setScale(2, RoundingMode.HALF_DOWN);
//        }
//        return calculatedCommissionFee;
//    }

//    @Override
//    public boolean validateTransaction(String userName, WebTransaction webTransaction) {
//
//        return webTransaction.getSellSum().add(calculateCommissionFee(userName, webTransaction))
//                .compareTo(balanceRepository
//                        .findBalanceByUsernameAndCurrencyCode(userName, webTransaction.getSellCurrencyCode())
//                        .getSumOnTheBalance()) >= 0;
//    }

//    @Override
//    public boolean validateTransaction(BigDecimal sumForTransaction) {
//        return sumForTransaction.add(calculateCommissionFee()).compareTo(getBalance()) >= 0;
//    }

    @Override
    public List<Transaction> findByUsername(String username) {

        return transactionRepository.findAll()
                .stream().filter(transaction -> transaction.getUser().getUserName().equals(username)).toList();
    }

    @Override
    public BigDecimal calculateSellFromReceive(BigDecimal receive, String receiveCurrencyCode, String sellCurrencyCode) {

        BigDecimal calculatedSell;

        if (receiveCurrencyCode.equals("EUR")) {
            calculatedSell = receive.multiply(ratesParser.getCurrencyRate(receiveCurrencyCode));
        } else {
            calculatedSell = receive.multiply(calculateCoefficientForCurrenciesExceptEUR(sellCurrencyCode, receiveCurrencyCode));
        }


        return calculatedSell;
    }

    @Override
    public BigDecimal calculateReceiveFromSell(BigDecimal sell, String sellCurrencyCode, String receiveCurrencyCode) {

        BigDecimal calculatedReceive;

        if (sellCurrencyCode.equals("EUR")) {
            calculatedReceive = sell.divide(ratesParser.getCurrencyRate(receiveCurrencyCode))
                    .setScale(2, RoundingMode.HALF_DOWN);
        } else {
            calculatedReceive = sell.divide(calculateCoefficientForCurrenciesExceptEUR(receiveCurrencyCode, sellCurrencyCode))
                    .setScale(2, RoundingMode.HALF_DOWN);
        }
        return calculatedReceive;
    }

    @Override
    public BigDecimal calculateCoefficientForCurrenciesExceptEUR(String receiveCurrencyCode, String sellCurrencyCode) {

        return ratesParser.getCurrencyRate(sellCurrencyCode).divide(ratesParser.getCurrencyRate(receiveCurrencyCode))
                .setScale(2, RoundingMode.HALF_DOWN);
    }
}
