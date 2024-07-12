package io.reflectoring.rentAcar.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.reflectoring.rentAcar.enums.InsuranceCategory;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "insurance")
@SQLDelete(sql = "UPDATE insurance SET deleted = true WHERE insuranceUUID=?")
@Where(clause = "deleted=false")
public class Insurances {

    @Id
    @GeneratedValue
    private UUID insuranceUUID;
    private String provider;
    private InsuranceCategory category;
    private double cost;
    private boolean deleted = Boolean.FALSE;


    @OneToOne(mappedBy = "insurance",cascade = CascadeType.ALL)
    private Cars car;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

}
