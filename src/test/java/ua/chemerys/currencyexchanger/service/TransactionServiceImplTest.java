//package ua.chemerys.currencyexchanger.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import ua.chemerys.currencyexchanger.entity.ExchangerDetails;
//import ua.chemerys.currencyexchanger.entity.User;
//import ua.chemerys.currencyexchanger.repository.ExchangerDetailsRepository;
//import ua.chemerys.currencyexchanger.repository.TransactionRepository;
//import ua.chemerys.currencyexchanger.repository.UserRepository;
//import ua.chemerys.currencyexchanger.webDto.WebTransaction;
//
//import java.util.Collections;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class TransactionServiceImplTest {
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private ExchangerDetailsRepository exchangerDetailsRepository;
//
//    @InjectMocks
//    private TransactionServiceImpl transactionService;
//
//    @Test
//    public void testAddTransactionWhenValidWebTransactionAndUserFoundThenSaveTransaction() {
//        WebTransaction webTransaction = new WebTransaction();
//        webTransaction.setUserName("testUser");
//        User user = new User();
//        user.setUserName("testUser");
//
//        when(userRepository.findByUserName(webTransaction.getUserName())).thenReturn(user);
//        when(exchangerDetailsRepository.findAll()).thenReturn(Collections.singletonList(new ExchangerDetails()));
//
//        transactionService.addTransaction(webTransaction);
//
//        verify(transactionRepository, times(1)).save(any());
//    }
//
//    @Test
//    public void testAddTransactionWhenUserNotFoundThenDoNotSaveTransaction() {
//        WebTransaction webTransaction = new WebTransaction();
//        webTransaction.setUserName("testUser");
//
//        when(userRepository.findByUserName(webTransaction.getUserName())).thenReturn(null);
//
//        transactionService.addTransaction(webTransaction);
//
//        verify(transactionRepository, times(0)).save(any());
//    }
//
//    @Test
//    public void testAddTransactionWhenExchangerDetailsNotFoundThenDoNotSaveTransaction() {
//        WebTransaction webTransaction = new WebTransaction();
//        webTransaction.setUserName("testUser");
//        User user = new User();
//        user.setUserName("testUser");
//
//        when(userRepository.findByUserName(webTransaction.getUserName())).thenReturn(user);
//        when(exchangerDetailsRepository.findAll()).thenReturn(Collections.emptyList());
//
//        transactionService.addTransaction(webTransaction);
//
//        verify(transactionRepository, times(0)).save(any());
//    }
//
//    @Test
//    public void testAddTransactionWhenValidWebTransactionThenSaveTransaction() {
//        WebTransaction webTransaction = new WebTransaction();
//        webTransaction.setUserName("testUser");
//        User user = new User();
//        user.setUserName("testUser");
//
//        when(userRepository.findByUserName(webTransaction.getUserName())).thenReturn(user);
//        when(exchangerDetailsRepository.findAll()).thenReturn(Collections.singletonList(new ExchangerDetails()));
//
//        transactionService.addTransaction(webTransaction);
//
//        verify(transactionRepository, times(1)).save(any());
//    }
//}