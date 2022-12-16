package org.putra.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//@Data
@Entity
@Table(name = "MST_PRICE")
public class Price {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "price_id")
    @Getter @Setter
    private String priceId;

    @Column(name = "daily_price", nullable = false)
    @Getter @Setter
    private Integer dailyPrice;

    @ManyToOne()
    @JoinColumn(name = "police_number", referencedColumnName = "police_number", nullable = false)
    @JsonManagedReference
    @Getter @Setter
    private Car car;

    @Column(name = "is_active", nullable = false)
    @Getter @Setter
    private Boolean isActive = false;
}
