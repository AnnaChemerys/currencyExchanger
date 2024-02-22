package ua.chemerys.currencyexchanger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chemerys.currencyexchanger.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

}
