package io.reflectoring.rentAcar.domain.model;

import io.reflectoring.rentAcar.enums.BrandType;
import io.reflectoring.rentAcar.enums.ColorType;
import io.reflectoring.rentAcar.enums.SegmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "car")
@SQLDelete(sql = "UPDATE car SET deleted = true WHERE carUUID=?")
@Where(clause = "deleted=false")
public class Cars {

    @Id
    @GeneratedValue
    private UUID carUUID;
    private BrandType brand;
    private SegmentType segment;
    private String model;
    private ColorType color;
    private String plateNumber;
    private Integer year;
    private int pricePerHour;
    private boolean isAvailable = true;
    private boolean deleted = Boolean.FALSE;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branchUUID", nullable = true)
    private Branchs branch;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<Rentals> rental;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurancesUUID", nullable = true)
    private Insurances insurance;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}
