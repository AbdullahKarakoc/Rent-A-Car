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
    private String cardNumber;
    private double amount;
    private String paymentType;
    private boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "rentalUUID", nullable = false)
    private Rentals rental;



}
