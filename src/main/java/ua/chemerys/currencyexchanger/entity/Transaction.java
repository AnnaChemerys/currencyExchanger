package ua.chemerys.currencyexchanger.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "sell_sum_unit_id")
    private SumUnit sell;

    @OneToOne
    @JoinColumn(name = "receive_sum_unit_id")
    private SumUnit receive;

    @OneToOne
    @JoinColumn(name = "exchanger_details")
    private ExchangerDetails exchangerDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "calculated_commission_fee")
    private BigDecimal calculatedCommissionFee;

    public Transaction() {
    }

    public Transaction(SumUnit sell, SumUnit receive, ExchangerDetails exchangerDetails, User user,
                       LocalDateTime creationTime, BigDecimal calculatedCommissionFee) {
        this.sell = sell;
        this.receive = receive;
        this.exchangerDetails = exchangerDetails;
        this.user = user;
        this.creationTime = creationTime;
        this.calculatedCommissionFee = calculatedCommissionFee;
    }
}
