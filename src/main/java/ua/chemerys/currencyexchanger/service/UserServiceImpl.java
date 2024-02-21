package ua.chemerys.currencyexchanger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.chemerys.currencyexchanger.entity.Balance;
//import ua.chemerys.currencyexchanger.entity.Currency;
import ua.chemerys.currencyexchanger.entity.Role;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.repository.BalanceRepository;
//import ua.chemerys.currencyexchanger.repository.CurrencyRepository;
import ua.chemerys.currencyexchanger.repository.RoleRepository;
import ua.chemerys.currencyexchanger.repository.UserRepository;
import ua.chemerys.currencyexchanger.user.WebUser;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //    private CurrencyRepository currencyRepository;
//
//    private BalanceRepository balanceRepository;

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
//                           BCryptPasswordEncoder passwordEncoder, CurrencyRepository currencyRepository,
//                           BalanceRepository balanceRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.currencyRepository = currencyRepository;
//        this.balanceRepository = balanceRepository;
//    }


    @Override
    public User findByUserName(String userName) {
        // check the database if the user already exists
        return userRepository.findByUserName(userName);
    }

    @Override
    public void save(WebUser webUser) {
        User user = new User();

//        Set<Balance> userMainBalance = setMainBalanceToNewUser(user);

//        Currency euro = currencyRepository.findByCurrencyCode("EUR");
//        Balance userMainBalance = new Balance(euro, BigDecimal.valueOf(1000), user);

        // assign user details to the user object
        user.setUserName(webUser.getUserName());
        user.setPassword(passwordEncoder.encode(webUser.getPassword()));
        user.setFirstName(webUser.getFirstName());
        user.setLastName(webUser.getLastName());
        user.setEmail(webUser.getEmail());

        // ToDo
        //
        //  //user.setUserBalances(setMainBalanceToNewUser(user));

        // give user default role of "user"
        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));

        // save user in the database
        userRepository.save(user);

        // save user's balance in the database
//        balanceRepository.save(userMainBalance);
    }

//    @Override
//    public void setMainBalanceToNewUser(User user) {
//
//        Currency euro = currencyRepository.findByCurrencyCode("EUR");
//        Balance userMainBalance = new Balance(euro, BigDecimal.valueOf(1000), user);
//
//        Set<Balance> userBalances = new HashSet<>();
//        userBalances.add(userMainBalance);
//
//    }

//    @Override
//    public Set<Balance> setMainBalanceToNewUser(User newUser) {
//
//        Currency euro = currencyRepository.findByCurrencyCode("EUR");
//        Balance userMainBalance = new Balance(euro, BigDecimal.valueOf(1000), newUser);
//
//        Set<Balance> userBalances = new HashSet<>();
//        userBalances.add(userMainBalance);
//
//        return userBalances;
//    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(user.getRoles());

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                authorities);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role tempRole : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
            authorities.add(tempAuthority);
        }

        return authorities;
    }
}
