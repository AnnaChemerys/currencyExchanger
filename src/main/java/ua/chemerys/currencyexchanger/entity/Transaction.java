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

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private SumUnit sell;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.ALL)
    private SumUnit receive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exchanger_details_id")
    private ExchangerDetails exchangerDetails;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Column(name = "calculated_commission_fee")
    private BigDecimal calculatedCommissionFee;

    public Transaction() {
    }

    public Transaction(LocalDateTime creationTime, BigDecimal calculatedCommissionFee) {
        this.creationTime = creationTime;
        this.calculatedCommissionFee = calculatedCommissionFee;
    }
}
