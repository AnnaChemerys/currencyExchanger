package ua.chemerys.currencyexchanger.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.chemerys.currencyexchanger.entity.Balance;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.service.BalanceService;
import ua.chemerys.currencyexchanger.service.TransactionService;
import ua.chemerys.currencyexchanger.service.UserService;
import ua.chemerys.currencyexchanger.util.RatesParser;
import ua.chemerys.currencyexchanger.webDto.WebTransaction;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/exchanger")

public class UserExchangerController {

    private UserService userService;
    private BalanceService balanceService;
    private TransactionService transactionService;
    private RatesParser ratesParser;

    public UserExchangerController(UserService userService, BalanceService balanceService,
                                   TransactionService transactionService, RatesParser ratesParser) {
        this.userService = userService;
        this.balanceService = balanceService;
        this.transactionService = transactionService;
        this.ratesParser = ratesParser;
    }

    @GetMapping("/showFormForTransaction")
    private String showFormForTransaction(Principal principal, ModelMap modelMap) {

        String userName = principal.getName();

        // get the user from the service

        User theUser = userService.findByUserName(userName);

        List<String> currencyCode = ratesParser.getListCurrenciesCodes();

        WebTransaction webTransaction = new WebTransaction();

        List<Balance> balances = balanceService.findByUser(theUser);

        // set user in the model prepopulate form

        modelMap.addAttribute("user", theUser);

        modelMap.addAttribute("currencyCode", currencyCode);

        modelMap.addAttribute("webTransaction", webTransaction);

        modelMap.addAttribute("balances", balances);

        // send over to our form

        return "user/user-show";
    }

    @PostMapping("/processFormForTransaction")
    public String processFormForTransaction(@Valid @ModelAttribute("webTransaction") WebTransaction webTransaction,
                                      BindingResult theBindingResult,
                                      Principal principal,
                                      ModelMap theModelMap) {

        User theUser = userService.findByUserName(principal.getName());

        if (theBindingResult.hasErrors()) {
            return "user/user-exchanger";
        } else {

            Transaction newTransaction = transactionService.addTransaction(webTransaction);
            userService.incrementUserCountTransactions(theUser);

            balanceService.updateUserBalances(theUser, newTransaction);
        }
        return "redirect:/user/user-show";
    }

    @GetMapping("/transactions")
    public String showAllUserTransactions(Principal principal, Model theModel) {

        String username = principal.getName();

        List<Transaction> userTransactions = transactionService.findByUsername(username);

        theModel.addAttribute("transactions", userTransactions);

        return "user/user-transactions";
    }
}
