package ua.chemerys.currencyexchanger.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.chemerys.currencyexchanger.service.BalanceService;
import ua.chemerys.currencyexchanger.service.TransactionService;
import ua.chemerys.currencyexchanger.webDto.WebTransaction;

@Component
public class CorrectnessOfInputTransactionConstraintValidator
        implements ConstraintValidator<CorrectnessOfInputTransaction, WebTransaction> {

    private TransactionService transactionService;
    private BalanceService balanceService;

    @Autowired
    public CorrectnessOfInputTransactionConstraintValidator(TransactionService transactionService, BalanceService balanceService) {
        this.transactionService = transactionService;
        this.balanceService = balanceService;
    }

    @Override
    public boolean isValid(WebTransaction webTransaction, ConstraintValidatorContext constraintValidatorContext) {

        return validateTransaction(webTransaction);
    }

    public boolean validateTransaction(WebTransaction webTransaction) {

        return webTransaction.getSellSum()
                .add(transactionService.calculateCommissionFee(webTransaction.getUserName(), webTransaction))
                .compareTo(balanceService
                        .getByUserNameAndCurrencyCode(webTransaction.getUserName(), webTransaction.getSellCurrencyCode())
                        .getSumOnTheBalance()) >= 0;
    }

}