package io.reflectoring.rentAcar.domain.model;

import io.reflectoring.rentAcar.auth._auth_customer.customer.Customer;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "rental")
@SQLDelete(sql = "UPDATE rental SET deleted = true WHERE rentalUUID=?")
@Where(clause = "deleted=false")
public class Rentals {

    @Id
    @GeneratedValue
    private UUID rentalUUID;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private boolean deleted = Boolean.FALSE;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carUUID", nullable = false)
    private Cars car;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerUUID", nullable = false)
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "paymentUUID", nullable = false)
    private Payments payment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "staffUUID", nullable = false)
    private Staffs staff;

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
