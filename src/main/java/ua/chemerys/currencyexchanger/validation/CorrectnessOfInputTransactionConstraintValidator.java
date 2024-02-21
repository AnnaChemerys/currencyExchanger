package ua.chemerys.currencyexchanger.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.chemerys.currencyexchanger.service.TransactionService;

import java.math.BigDecimal;

public class CorrectnessOfInputTransactionConstraintValidator
        implements ConstraintValidator<CorrectnessOfInputTransaction, BigDecimal> {

    private TransactionService exchangerService;

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        return false;
    }

    public boolean isCorrectInput(BigDecimal theSumForTransaction) {
//        return exchangerService.findById(id).getSell().getCountOfMoney().add(commissionFee()).compareTo(getBalance()) >= 0;

        return exchangerService.validateTransaction(theSumForTransaction);
    }
}
