package ua.chemerys.currencyexchanger.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.service.BalanceService;
import ua.chemerys.currencyexchanger.service.TransactionService;
import ua.chemerys.currencyexchanger.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/exchanger")
@AllArgsConstructor
public class UserExchangerController {

    private UserService userService;
    private BalanceService balanceService;
    private TransactionService transactionService;

    @GetMapping("/showFormForTransaction")
    private String showFormForTransaction(@RequestParam("username") String username, Model theModel) {

        // get the user from the service

        User theUser = userService.findByUserName(username);

        // set user in the model prepopulate form

        theModel.addAttribute("user", theUser);

        // send over to our form

        return "user/user-exchanger";
    }

    @PostMapping("/save")
    public String saveUserTransaction(@Valid @ModelAttribute("transaction") Transaction transaction,
                                      BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            return "user/user-exchanger";
        } else {
            transactionService.save(transaction);

            balanceService.update();
        } return "redirect:/user/homepage";
    }

    @GetMapping("/transactions")
    public String showAllUserTransactions(Principal principal, Model theModel) {

        String username = principal.getName();

        List<Transaction> userTransactions = transactionService.findByUsername(username);

        theModel.addAttribute("transactions", userTransactions);

        return "user/user-transactions";
    }

//    @PostMapping("/save")
//    public String saveUserTransaction(@Valid @ModelAttribute("user") User theUser,
//                                      BindingResult theBindingResult) {
//
//        if (theBindingResult.hasErrors()) {
//            return "user/user-exchanger";
//        } else {
//            userService.save(theUser);
//        } return "redirect:/user/homepage";
//    }
}
