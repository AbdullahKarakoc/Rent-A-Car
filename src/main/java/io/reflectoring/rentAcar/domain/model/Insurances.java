package io.reflectoring.rentAcar.domain.model;

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
@SQLDelete(sql = "UPDATE insurance SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Insurances {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String provider;
    private LocalDateTime start;
    private LocalDateTime end;
    private int cost;
    private InsuranceCategory insuranceCategory;


    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "insurance_id")
    private List<Insurances> insurances;



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
