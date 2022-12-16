package org.putra.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Data
@Entity
@Table(name = "MST_CAR")
public class Car {
    @Id
    @Column(name = "police_number", nullable = false, unique = true)
    @Getter @Setter
    private String policeNumber;

    @Column(name = "car_brand", nullable = false)
    @Getter @Setter
    private String carBrand;

    @Column(name = "max_seat", nullable = false)
    @Getter @Setter
    private Integer maxSeat;

    @Column(name = "car_year", nullable = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Getter @Setter
    private Date carYear;

    @Column(name = "is_available", nullable = false)
    @Getter @Setter
    private Boolean isAvailable = false;

    @OneToMany(mappedBy = "car")
    @JsonBackReference
    private List<Price> priceList;
}
