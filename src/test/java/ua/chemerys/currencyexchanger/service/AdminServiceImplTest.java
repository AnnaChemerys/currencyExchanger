package ua.chemerys.currencyexchanger.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.chemerys.currencyexchanger.entity.Balance;
import ua.chemerys.currencyexchanger.entity.ExchangerDetails;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.repository.BalanceRepository;
import ua.chemerys.currencyexchanger.repository.ExchangerDetailsRepository;
import ua.chemerys.currencyexchanger.repository.TransactionRepository;
import ua.chemerys.currencyexchanger.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BalanceRepository balanceRepository;

    @Mock
    private ExchangerDetailsRepository exchangerDetailsRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    private ExchangerDetails exchangerDetails;
    private User user;
    private Transaction transaction;
    private Balance balance;

    @BeforeEach
    void setUp() {
        exchangerDetails = new ExchangerDetails(0.05f, 5);
        user = new User("testUser", "password", true);
        transaction = new Transaction();
        balance = new Balance("USD", null);
    }

    @Test
    void testChangeCommissionFeeWhenCalledThenCommissionFeeIsChanged() {
        float newCommissionFee = 0.1f;
        when(exchangerDetailsRepository.findAll()).thenReturn(Collections.singletonList(exchangerDetails));

        adminService.changeCommissionFee(newCommissionFee);

        assertEquals(newCommissionFee, exchangerDetails.getCommissionFee());
        verify(exchangerDetailsRepository).findAll();
    }

    @Test
    void testGetAllUsersWhenCalledThenAllUsersAreReturned() {
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> users = adminService.getAllUsers();

        assertEquals(Collections.singletonList(user), users);
        verify(userRepository).findAll();
    }

    @Test
    void testGetAllTransactionsWhenCalledThenAllTransactionsAreReturned() {
        when(transactionRepository.findAll()).thenReturn(Collections.singletonList(transaction));

        List<Transaction> transactions = adminService.getAllTransactions();

        assertEquals(Collections.singletonList(transaction), transactions);
        verify(transactionRepository).findAll();
    }

    @Test
    void testGetAllBalancesWhenCalledThenAllBalancesAreReturned() {
        when(balanceRepository.findAll()).thenReturn(Collections.singletonList(balance));

        List<Balance> balances = adminService.getAllBalances();

        assertEquals(Collections.singletonList(balance), balances);
        verify(balanceRepository).findAll();
    }

//    @Test
//    void testGetTransactionsByUserWhenCalledThenTransactionsByUserAreReturned() {
//        when(transactionRepository.findAll()).thenReturn(Collections.singletonList(transaction));
//        when(transaction.getUser()).thenReturn(user);
//
//        List<Transaction> transactions = adminService.getTransactionsByUser(user);
//
//        assertEquals(Collections.singletonList(transaction), transactions);
//        verify(transactionRepository).findAll();
//    }
//
//    @Test
//    void testGetBalanceByUserWhenCalledThenBalancesByUserAreReturned() {
//        when(balanceRepository.findAll()).thenReturn(Collections.singletonList(balance));
//        when(balance.getUser()).thenReturn(user);
//
//        List<Balance> balances = adminService.getBalanceByUser(user);
//
//        assertEquals(Collections.singletonList(balance), balances);
//        verify(balanceRepository).findAll();
//    }

    @Test
    void testChangeCountOfFreeTransactionsWhenCalledThenCountOfFreeTransactionsIsChanged() {
        int newCountOfFreeTransactions = 10;
        when(exchangerDetailsRepository.findAll()).thenReturn(Collections.singletonList(exchangerDetails));

        adminService.changeCountOfFreeTransactions(newCountOfFreeTransactions);

        assertEquals(newCountOfFreeTransactions, exchangerDetails.getCountOfFreeTransactions());
        verify(exchangerDetailsRepository).findAll();
    }
}
