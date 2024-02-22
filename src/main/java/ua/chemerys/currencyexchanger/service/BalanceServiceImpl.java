package ua.chemerys.currencyexchanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.chemerys.currencyexchanger.entity.Balance;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.repository.BalanceRepository;
import ua.chemerys.currencyexchanger.repository.UserRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BalanceServiceImpl implements BalanceService {

    private BalanceRepository balanceRepository;

    private UserRepository userRepository;

    @Autowired
    public BalanceServiceImpl(BalanceRepository balanceRepository, UserRepository userRepository) {
        this.balanceRepository = balanceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Balance> findAll() {
        return balanceRepository.findAll();
    }

    @Override
    public Balance findById(long theId) {
        Optional<Balance> result = balanceRepository.findById(theId);

        Balance theBalance;

        if (result.isPresent()) {
            theBalance = result.get();
        } else {
            throw new RuntimeException("Did not find balance id " + theId);
        }
        return theBalance;
    }

    @Override
    public void save(Balance theBalance) {
        balanceRepository.save(theBalance);
    }

    @Override
    public void addBalance(String userName, String currencyCode, BigDecimal sumOfReceive) {

        User currentUser = userRepository.findByUserName(userName);

        Balance balance = new Balance();
        balance.setSumOnTheBalance(sumOfReceive);

        balance.setCurrencyCode(currencyCode);
        balance.setUser(currentUser);
        balanceRepository.save(balance);
    }

    @Override
    public void addBalance(Long theUserId, String currencyCode, BigDecimal sumOfReceive) {

        Optional<User> optionalUser = userRepository.findById(theUserId);

        optionalUser.ifPresent(user -> {
            Balance balance = new Balance();
            balance.setSumOnTheBalance(sumOfReceive);

            balance.setCurrencyCode(currencyCode);
            balance.setUser(user);
            balanceRepository.save(balance);
        });
    }

    @Override
    public void updateUserBalances(User theUser, Transaction theTransaction) {

        Balance receiveBalance = getByUserAndCurrencyCode(theUser, theTransaction.getReceive().getCurrencyCode());

        if (receiveBalance != null) {
            receiveBalance.setSumOnTheBalance(receiveBalance.getSumOnTheBalance()
                    .add(theTransaction.getReceive().getAmountOfMoney()));
        } else {
            addBalance(theUser.getUserName(), theTransaction.getReceive().getCurrencyCode(),
                    theTransaction.getReceive().getAmountOfMoney());
        }

        Balance sellBalance = getByUserAndCurrencyCode(theUser, theTransaction.getSell().getCurrencyCode());

        sellBalance.setSumOnTheBalance(sellBalance.getSumOnTheBalance()
                .subtract(theTransaction.getSell().getAmountOfMoney()).subtract(theTransaction
                        .getCalculatedCommissionFee()));

        balanceRepository.save(sellBalance);
    }

    @Override
    public Balance findByTypeOfCurrency(String currencyCode) {
        return null;
    }

    @Override
    public Set<Balance> findByUser(User theUser) {
        return balanceRepository.findByUser(theUser);
    }

//    @Override
//    public Balance findByUserAndTypeOfCurrency(long theUserId, String currencyCode) {
//        return null;
//    }
//
//    @Override
//    public Balance getByUserIdAndCurrencyCode(long theUserId, String currencyCode) {
//        return null;
//    }

    @Override
    public Balance getByUserAndCurrencyCode(User theUser, String currencyCode) {

        Balance theBalance;

        Optional<Balance> optionalBalance = balanceRepository.findAll()
                .stream().filter(balance -> balance.getUser().equals(theUser))
                .filter(balance -> balance.getCurrencyCode().equals(currencyCode))
                .findFirst();
        if (optionalBalance.isPresent()) {
            theBalance = optionalBalance.get();
        } else {
            return null;
        }
        return theBalance;
    }

    @Override
    public Balance getByUserNameAndCurrencyCode(String userName, String currencyCode) {

        Balance theBalance;

        Optional<Balance> optionalBalance = balanceRepository.findAll()
                .stream().filter(balance -> balance.getUser().getUserName().equals(userName))
                .filter(balance -> balance.getCurrencyCode().equals(currencyCode))
                .findFirst();
        if (optionalBalance.isPresent()) {
            theBalance = optionalBalance.get();
        } else {
            return null;
        }
        return theBalance;
    }
}
