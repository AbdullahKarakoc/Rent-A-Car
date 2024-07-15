package io.reflectoring.rentAcar.domain.model;

import io.reflectoring.rentAcar.enums.PaymentType;
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
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment")
@SQLDelete(sql = "UPDATE payment SET deleted = true WHERE paymentUUID=?")
@Where(clause = "deleted=false")
public class Payments {

    @Id
    @GeneratedValue
    private UUID paymentUUID;
    private int totalAmount;
    private PaymentType paymentType;
    private boolean deleted = Boolean.FALSE;

    @OneToOne(mappedBy = "payment",cascade = CascadeType.ALL)
    private Rentals rental;

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
