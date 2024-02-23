package ua.chemerys.currencyexchanger.controller;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ua.chemerys.currencyexchanger.service.BalanceService;
import ua.chemerys.currencyexchanger.service.UserService;
import ua.chemerys.currencyexchanger.webDto.WebUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RegistrationControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private BalanceService balanceService;

    @Mock
    private Model model;

    @Mock
    private HttpSession session;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RegistrationController registrationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowMyLoginPageWhenCalledWithModelThenReturnsCorrectView() {
        when(model.addAttribute("webUser", new WebUser())).thenReturn(model);

        String viewName = registrationController.showMyLoginPage(model);

        assertEquals("register/registration-form", viewName);
    }

    @Test
    void testProcessRegistrationFormWhenCalledWithMockObjectsThenReturnsCorrectView() {
        WebUser webUser = new WebUser();
        webUser.setUserName("testUser");
        when(bindingResult.hasErrors()).thenReturn(false);
        when(userService.findByUserName("testUser")).thenReturn(null);

        String viewName = registrationController.processRegistrationForm(webUser, bindingResult, session, model);

        assertEquals("register/registration-confirmation", viewName);
        verify(userService).save(any(WebUser.class));
        verify(balanceService).addBalance(eq("testUser"), eq("EUR"), any());
        verify(session).setAttribute(eq("user"), any(WebUser.class));
    }
}
