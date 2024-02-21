package ua.chemerys.currencyexchanger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chemerys.currencyexchanger.entity.ExchangerDetails;

public interface ExchangerDetailsRepository extends JpaRepository<ExchangerDetails, Long> {
}
