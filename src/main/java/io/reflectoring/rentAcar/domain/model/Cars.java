package io.reflectoring.rentAcar.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "car")
@SQLDelete(sql = "UPDATE car SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Cars {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String brand;
    private String model;
    private String color;
    private String carStatus;
    private int year;
    private int pricePerHour;

    private boolean deleted = Boolean.FALSE;
    private boolean isAvailable = Boolean.FALSE;

    private LocalDateTime availableFrom;
    private LocalDateTime availableTo;

    @JsonManagedReference
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    private List<Rentals> rentals;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "insurances_id")
    private List<Insurances> insurances;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "locations_id")
    private Locations locations;


    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModified;

    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private String createBy;

    @LastModifiedBy
    @Column(insertable = false)
    private String lastModifiedBy;


}
