package ua.chemerys.currencyexchanger.webDto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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

    public WebTransaction() {
    }
}
