package ua.chemerys.currencyexchanger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chemerys.currencyexchanger.entity.SumUnit;

public interface SumUnitRepository extends JpaRepository<SumUnit, Long> {
}
