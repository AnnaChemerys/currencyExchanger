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

    // ToDo
//    @OneToOne
//    @JoinColumn(name = "currency_id")
//    private Currency typeOfCurrency;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    public SumUnit(BigDecimal amountOfMoney, String currencyCode, Transaction transaction) {
        this.amountOfMoney = amountOfMoney;
        this.currencyCode = currencyCode;
        this.transaction = transaction;
    }

    public SumUnit() {
    }

    //    public SumUnit(BigDecimal amountOfMoney, Currency typeOfCurrency, Transaction transaction) {
//        this.amountOfMoney = amountOfMoney;
//        this.typeOfCurrency = typeOfCurrency;
//        this.transaction = transaction;
//    }
}
