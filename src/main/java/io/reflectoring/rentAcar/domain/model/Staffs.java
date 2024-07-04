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
@Table(name = "staff")
@SQLDelete(sql = "UPDATE staff SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Staffs {

    @Id
    @GeneratedValue
    private UUID staffUUID;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private boolean deleted = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "branchUUID", nullable = false)
    private Branchs branch;


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
