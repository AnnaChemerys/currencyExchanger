package ua.chemerys.currencyexchanger.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "balance")
@NoArgsConstructor
// ToDo
// One more constructor?
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "currency_code")
    private String currencyCode;

    // ToDo
//    @OneToOne
//    @JoinColumn(name = "currency_id")
//    private Currency typeOfCurrency;

    @Column(name = "sum_on_the_balance")
    private BigDecimal sumOnTheBalance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Balance(String currencyCode, BigDecimal sumOnTheBalance, User user) {
        this.currencyCode = currencyCode;
        this.sumOnTheBalance = sumOnTheBalance;
        this.user = user;
    }

    public Balance() {
    }

    //    public Balance(Currency typeOfCurrency, BigDecimal sumOnTheBalance, User user) {
//        this.typeOfCurrency = typeOfCurrency;
//        this.sumOnTheBalance = sumOnTheBalance;
//        this.user = user;
//    }

    //    private BigDecimal euro;
//
//    private BigDecimal usd;
//
//    private BigDecimal uah;
}
