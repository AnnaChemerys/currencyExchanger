package ua.chemerys.currencyexchanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.chemerys.currencyexchanger.entity.Balance;
//import ua.chemerys.currencyexchanger.entity.Currency;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.repository.BalanceRepository;
//import ua.chemerys.currencyexchanger.repository.CurrencyRepository;
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

    //    private CurrencyRepository currencyRepository;

//    @Autowired
//    public BalanceServiceImpl(BalanceRepository balanceRepository, UserRepository userRepository,
//                              CurrencyRepository currencyRepository) {
//        this.balanceRepository = balanceRepository;
//        this.userRepository = userRepository;
//        this.currencyRepository = currencyRepository;
//    }

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
    public void addBalance(Long theUserId, String currencyCode, BigDecimal sumOfReceive) {

        Optional<User> optionalUser = userRepository.findById(theUserId);

        optionalUser.ifPresent(user -> {
            Balance balance = new Balance();
            balance.setSumOnTheBalance(sumOfReceive);

            balance.setCurrencyCode(currencyCode);
            //balance.setTypeOfCurrency(currencyRepository.findByCurrencyCode(currencyCode));
            balance.setUser(user);
            balanceRepository.save(balance);
        });
    }

    // ToDo
    @Override
    public void update(long theId, Balance theBalance) {

        Balance balanceToBeUpdated = findById(theId);
        balanceToBeUpdated.setSumOnTheBalance(theBalance.getSumOnTheBalance());
        balanceRepository.save(balanceToBeUpdated);

    }

    @Override
    public Balance findByTypeOfCurrency(String currencyCode) {
        return null;
    }

//    @Override
//    public Balance findByTypeOfCurrency(Currency typeOfCurrency) {
//        return null;
//    }

    @Override
    public Set<Balance> findByUser(User theUser) {
        return balanceRepository.findByUser(theUser);
    }

    @Override
    public Balance findByUserAndTypeOfCurrency(long theUserId, String currencyCode) {
        return null;
    }

//    @Override
//    public Balance findByUserAndTypeOfCurrency(User theUser, Currency theTypeOfCurrency) {
//        return null;
//    }

    @Override
    public Balance getByUserIdAndCurrencyCode(long theUserId, String currencyCode) {
        return null;
    }
}
