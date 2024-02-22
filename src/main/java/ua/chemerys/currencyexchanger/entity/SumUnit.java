package ua.chemerys.currencyexchanger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "sum_unit")
public class SumUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount_of_money")
    private BigDecimal amountOfMoney;

    @Column(name = "currency_code")
    private String currencyCode;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public SumUnit() {
    }

    public SumUnit(BigDecimal amountOfMoney, String currencyCode) {
        this.amountOfMoney = amountOfMoney;
        this.currencyCode = currencyCode;
    }
}
