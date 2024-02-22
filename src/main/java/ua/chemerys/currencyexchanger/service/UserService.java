package ua.chemerys.currencyexchanger.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.webDto.WebUser;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(WebUser webUser);

    void incrementUserCountTransactions(User theUser);
}
