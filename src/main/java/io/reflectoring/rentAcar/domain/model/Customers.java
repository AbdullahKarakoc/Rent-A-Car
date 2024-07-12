package io.reflectoring.rentAcar.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer")
@SQLDelete(sql = "UPDATE customer SET deleted = true WHERE customerUUID=?")
@Where(clause = "deleted=false")
public class Customers {

    @Id
    @GeneratedValue
    private UUID customerUUID;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String phone;
    private String email;
    private boolean deleted = Boolean.FALSE;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private List<Rentals> rental;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;


}
