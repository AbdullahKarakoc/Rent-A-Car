package io.reflectoring.rentAcar.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.reflectoring.rentAcar.enums.Brand;
import io.reflectoring.rentAcar.enums.Color;
import io.reflectoring.rentAcar.enums.Segment;
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
import java.util.Date;
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
    private Brand brand;
    private Segment segment;
    private String model;
    private Color color;
    private String plateNumber;
    private Date year;
    private boolean isAvailable;
    private int pricePerHour;
    private boolean deleted = Boolean.FALSE;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branchUUID", nullable = false)
    private Branchs branch;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<Rentals> rental;

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    private List<Insurances> insurance;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

}
