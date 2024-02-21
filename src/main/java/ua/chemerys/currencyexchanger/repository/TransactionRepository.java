package ua.chemerys.currencyexchanger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chemerys.currencyexchanger.entity.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUsername(String username);
}
