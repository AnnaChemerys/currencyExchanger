package ua.chemerys.currencyexchanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;
//import ua.chemerys.currencyexchanger.repository.CurrencyRepository;
import ua.chemerys.currencyexchanger.repository.ExchangerDetailsRepository;
import ua.chemerys.currencyexchanger.repository.TransactionRepository;
import ua.chemerys.currencyexchanger.repository.UserRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    private UserRepository userRepository;

    private ExchangerDetailsRepository exchangerDetailsRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository,
                                  ExchangerDetailsRepository exchangerDetailsRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.exchangerDetailsRepository = exchangerDetailsRepository;
    }

    //    private CurrencyRepository currencyRepository;


//    @Autowired
//    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository,
//                                  ExchangerDetailsRepository exchangerDetailsRepository,
//                                  CurrencyRepository currencyRepository) {
//        this.transactionRepository = transactionRepository;
//        this.userRepository = userRepository;
//        this.exchangerDetailsRepository = exchangerDetailsRepository;
//        this.currencyRepository = currencyRepository;
//    }

    //    private UserService userService;

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

    @Override
    public BigDecimal getBalance(String username, String currencyCode) {

        return userRepository.findByUserName(username).getUserBalances().stream()
                .filter(balance -> balance.getCurrencyCode().equals(currencyCode)).findFirst().get()
                .getSumOnTheBalance();
//        return userRepository.findByUserName(userName).getUserBalance(typeOfCurrency).getSumOfBalance();
    }

    @Override
    public BigDecimal convert(BigDecimal sellMoney, BigDecimal receiveMoney) {
        return null;
    }

    @Override
    public BigDecimal calculateCommissionFee() {
        return null;
    }

    @Override
    public BigDecimal convert(BigDecimal sellMoney, BigDecimal receiveMoney, String username) {
        if (validateTransaction(sellMoney)) {

        }

            userRepository.findByUserName(userName).getCountOfTransactions()++;
        return null;
    }

    @Override
    public BigDecimal calculateCommissionFee(User currentUser, Transaction transaction) {

        float commissionFee = exchangerDetailsRepository.findAll().stream().findFirst().get().getCommissionFee();
        BigDecimal calculatedCommissionFee;

        if (currentUser.getCountOfTransactions() < 5) {
            calculatedCommissionFee = BigDecimal.valueOf(0);
        } else {
            calculatedCommissionFee = transaction.getSell().getAmountOfMoney()
                    .multiply(BigDecimal.valueOf(commissionFee)).
                    divide(BigDecimal.valueOf(100))
                    .setScale(2, RoundingMode.HALF_DOWN);
        }
        return calculatedCommissionFee;
    }

//    @Override
//    public boolean validateTransaction(int id) {
//        return findById(id).getSell().getCountOfMoney().add(commissionFee()).compareTo(getBalance()) >= 0;
//    }

    @Override
    public boolean validateTransaction(BigDecimal sumForTransaction) {
        return sumForTransaction.add(calculateCommissionFee()).compareTo(getBalance()) >= 0;
    }

    @Override
    public List<Transaction> findByUsername(String username) {

        return transactionRepository.findAll()
                .stream().filter(transaction -> transaction.getUser().getUserName().equals(username)).toList();
    }

//    @Override
//    public List<Transaction> findByUser(User theUser) {
//        return null;
//    }

    @Override
    public BigDecimal calculateSellFromReceive(BigDecimal receive, String currencyCode) {
        return null;
    }

    @Override
    public BigDecimal calculateSellFromReceive(BigDecimal receive, String receiveCurrencyCode, String sellCurrencyCode) {

        BigDecimal calculatedSell;

        if (currencyRepository.findByCurrencyCode(receiveCurrencyCode).equals("EUR")) {
            calculatedSell = receive.multiply(currencyRepository.findByCurrencyCode(receiveCurrencyCode).getRate());
        } else {

            calculatedSell = receive.multiply(calculateCoefficientForCurrenciesExceptEUR(sellCurrencyCode, receiveCurrencyCode));
        }


        return calculatedSell;
    }

    @Override
    public BigDecimal calculateReceiveFromSell(BigDecimal sell, String currencyCode) {

        BigDecimal calculatedReceive = sell.divide(currencyRepository.findByCurrencyCode(currencyCode).getRate()).
                setScale(2, RoundingMode.HALF_DOWN);
        return calculatedReceive;
    }

    @Override
    public BigDecimal calculateCoefficientForCurrenciesExceptEUR(BigDecimal sellRate, BigDecimal receiveRate) {

        BigDecimal coefficient = sellRate.divide(receiveRate).setScale(2, RoundingMode.HALF_DOWN);

        return coefficient;
    }
}
