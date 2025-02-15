package io.reflectoring.rentAcar.domain.model;

import io.reflectoring.rentAcar.enums.CountryType;
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
@Table(name = "branch_address")
@SQLDelete(sql = "UPDATE branch_address SET deleted = true WHERE addressUUID=?")
@Where(clause = "deleted=false")
public class BranchAddress {

    @Id
    @GeneratedValue
    private UUID addressUUID;
    private String street;
    private String city;
    private CountryType country;
    private String zipCode;
    private boolean deleted = Boolean.FALSE;

    @OneToOne(mappedBy = "address",cascade = CascadeType.ALL)
    private Branchs branch;

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
