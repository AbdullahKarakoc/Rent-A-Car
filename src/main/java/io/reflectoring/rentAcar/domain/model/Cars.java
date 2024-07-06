package io.reflectoring.rentAcar.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@SQLDelete(sql = "UPDATE car SET deleted = true WHERE carUUID=?")
@Where(clause = "deleted=false")
public class Cars {

    @Id
    @GeneratedValue
    private UUID carUUID;
    private String model;
    private String brand;
    private int year;
    private String color;
    private String registrationNumber;
    private boolean available;
    private boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "locationUUID", nullable = false)
    private Branchs branch;

    @OneToMany(mappedBy = "car")
    private List<Rentals> rentals;

    @OneToOne(mappedBy = "car")
    private Insurances insurance;

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
