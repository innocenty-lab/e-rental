//package org.putra.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Data
//@Entity
//@Table(name = "TRANSACTION_DETAIL")
//public class TransactionDetail {
//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
//    @Column(name = "transaction_detail_id")
//    @Getter @Setter
//    private String transactionDetailId;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "transaction_id")
//    @JsonBackReference
//    @Getter @Setter
//    private Transaction transaction;
//
//    @Column(name = "start_rent", nullable = false)
//    @Temporal(TemporalType.DATE)
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Getter @Setter
//    private Date startRent;
//
//    @Column(name = "end_rent", nullable = false)
//    @Temporal(TemporalType.DATE)
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Getter @Setter
//    private Date endRent;
//
//    @ManyToOne
//    @JoinColumn(name = "price_id", referencedColumnName = "price_id", nullable = false)
//    @Getter @Setter
//    private Price price;
//}

package org.putra.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION_DETAIL")
public class TransactionDetail {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "transaction_detail_id")
    @Getter @Setter
    private String transactionDetailId;

    @ManyToOne()
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    @JsonBackReference
    @Getter @Setter
    private Transaction transaction;

    @Column(name = "start_rent", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Getter @Setter
    private Date startRent;

    @Column(name = "end_rent", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Getter @Setter
    private Date endRent;

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "price_id", nullable = false)
    @Getter @Setter
    private Price price;
}
