package ua.chemerys.currencyexchanger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "balance")

public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "sum_on_the_balance")
    private BigDecimal sumOnTheBalance;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public Balance(String currencyCode, BigDecimal sumOnTheBalance, User user) {
        this.currencyCode = currencyCode;
        this.sumOnTheBalance = sumOnTheBalance;
        this.user = user;

    }

    public Balance(String currencyCode, BigDecimal sumOnTheBalance) {
        this.currencyCode = currencyCode;
        this.sumOnTheBalance = sumOnTheBalance;
    }

    public Balance() {
    }
}
