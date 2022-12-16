package org.putra.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "MST_CUSTOMER")
public class Customer {
    @Id
    @Column(name = "customer_nik", nullable = false, unique = true)
    private String customerNik;

    @Column(name = "customer_name", nullable = false, length = 50)
    private String customerName;

    @Column(name = "customer_address", nullable = false)
    private String customerAddress;

    @Column(name = "customer_age", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date customerBirthDate;

    @Column(name = "customer_phone_number", nullable = false, unique = true)
    private String customerPhoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email", referencedColumnName = "email", nullable = false)
    private UserCredential userCredential;
}
