package ua.chemerys.currencyexchanger.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.chemerys.currencyexchanger.entity.Balance;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.user.WebUser;

import java.util.Set;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(WebUser webUser);

//    void setMainBalanceToNewUser(User user);

//    Set<Balance> setMainBalanceToNewUser(User newUser);
}
