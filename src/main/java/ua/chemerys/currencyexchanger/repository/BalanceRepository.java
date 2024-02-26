package ua.chemerys.currencyexchanger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chemerys.currencyexchanger.entity.Balance;
import ua.chemerys.currencyexchanger.entity.User;

import java.util.List;
import java.util.Set;

public interface BalanceRepository extends JpaRepository<Balance, Long> {

//    List<Balance> findByUser(User user);

//    Set<Balance> updateUserBalances(User user);
//
//    Balance findBalanceByUsernameAndCurrencyCode(String username, String currencyCode);
}
