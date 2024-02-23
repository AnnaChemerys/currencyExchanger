package ua.chemerys.currencyexchanger.webDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ua.chemerys.currencyexchanger.validation.CorrectnessOfInputTransaction;

import java.math.BigDecimal;

@Getter
@Setter
public class WebTransaction {

    @NotNull
    @Size(min = 3, max = 3)
    private String sellCurrencyCode;

    private BigDecimal sellSum;

    @NotNull
    @Size(min = 3, max = 3)
    private String receiveCurrencyCode;

    private BigDecimal receiveSum;

    private String userName;

    private BigDecimal calculatedCommissionFee;

    public WebTransaction() {
    }

    @CorrectnessOfInputTransaction
    public WebTransaction(@NotNull String sellCurrencyCode, BigDecimal sellSum, @NotNull String receiveCurrencyCode,
                          BigDecimal receiveSum, String userName) {
        this.sellCurrencyCode = sellCurrencyCode;
        this.sellSum = sellSum;
        this.receiveCurrencyCode = receiveCurrencyCode;
        this.receiveSum = receiveSum;
        this.userName = userName;
    }
}
