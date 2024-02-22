package ua.chemerys.currencyexchanger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "exchanger_details")
public class ExchangerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "commission_fee")
    private float commissionFee;

    @Column(name = "count_of_free_transactions")
    private int countOfFreeTransactions;

    public ExchangerDetails() {
    }

    public ExchangerDetails(float commissionFee, int countOfFreeTransactions) {
        this.commissionFee = commissionFee;
        this.countOfFreeTransactions = countOfFreeTransactions;
    }
}
