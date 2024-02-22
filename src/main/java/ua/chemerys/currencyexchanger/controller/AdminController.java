package ua.chemerys.currencyexchanger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.chemerys.currencyexchanger.entity.Balance;
import ua.chemerys.currencyexchanger.entity.Transaction;
import ua.chemerys.currencyexchanger.entity.User;
import ua.chemerys.currencyexchanger.service.AdminService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService theAdminService) {
        adminService = theAdminService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {

        List<User> users = adminService.getAllUsers();

        model.addAttribute("users", users);

        return "admin/list-users";
    }

    @GetMapping("/users-balances")
    public String allUsersBalances(Model theModel) {

        List<Balance> balances = adminService.getAllBalances();

        theModel.addAttribute("balances", balances);

        return "admin/list-balances";
    }

    @GetMapping("users-transactions")
    public String allUsersTransactions(Model theModel) {

        List<Transaction> allUsersTransactions = adminService.getAllTransactions();

        theModel.addAttribute("transactions", allUsersTransactions);

        return "admin/list-transactions";
    }

    @GetMapping("/user-balances")
    public String getTransactionsByUser(Model theModel) {

//        List<Transaction> transactionsByUser = adminService.getTransactionsByUser();
//
//        theModel.addAttribute("transactions", transactionsByUser);

        return "admin/user-transactions";
    }

    @GetMapping("/administration-page")
    public String getAdministrationPage(Model theModel) {

        return "admin/administration-page";
    }
}


