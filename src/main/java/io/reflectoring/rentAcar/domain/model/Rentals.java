package io.reflectoring.rentAcar.domain.model;

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
@Table(name = "rental")
@SQLDelete(sql = "UPDATE rental SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Rentals {

    @Id
    @GeneratedValue
    private UUID rentalUUID;

    @ManyToOne
    @JoinColumn(name = "carUUID", nullable = false)
    private Cars car;

    @ManyToOne
    @JoinColumn(name = "customerUUID", nullable = false)
    private Customers customer;

    @OneToMany(mappedBy = "rental")
    private List<Payments> payments;


    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;


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
