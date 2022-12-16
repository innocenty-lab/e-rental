//package org.putra.entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.List;
//
//@Data
//@Entity
//@Table(name = "TRANSACTION")
//public class Transaction {
//    @Id
//    @GeneratedValue(generator = "system-uuid")
//    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
//    @Column(name = "transaction_id")
//    private String transactionId;
//
//    @ManyToOne
//    @JoinColumn(name = "customer_nik", referencedColumnName = "customer_nik")
//    private Customer customer;
//
//    @Column(name = "booked_date", nullable = false)
//    @Temporal(TemporalType.DATE)
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private Date bookedDate;
//
//    @Column(name = "is_paid", nullable = false)
//    private Boolean isPaid = false;
//
//    @Column(name = "paid_date")
//    @Temporal(TemporalType.DATE)
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private Date paidDate;
//
//    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
//    @JsonManagedReference
//    private List<TransactionDetail> transactionDetails;
//}

package org.putra.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Data
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "transaction_id")
    @Getter @Setter
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "customer_nik", referencedColumnName = "customer_nik")
    @Getter @Setter
    private Customer customer;

    @Column(name = "booked_date", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Getter @Setter
    private Date bookedDate;

    @Column(name = "is_paid", nullable = false)
    @Getter @Setter
    private Boolean isPaid = false;

    @Column(name = "paid_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Getter @Setter
    private Date paidDate;

    @OneToMany(mappedBy = "transaction")
    @Getter @Setter
    private List<TransactionDetail> transactionDetails;
}
