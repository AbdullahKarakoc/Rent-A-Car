package io.reflectoring.rentAcar.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "branch")
@SQLDelete(sql = "UPDATE branch SET deleted = true WHERE branchUUID=?")
@Where(clause = "deleted=false")
public class Branchs {

    @Id
    @GeneratedValue
    private UUID branchUUID;
    private String branchName;
    private boolean deleted = Boolean.FALSE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressUUID", referencedColumnName = "addressUUID")
    private BranchAddress address;

    @OneToMany(mappedBy = "branch")
    private List<Cars> cars;

    @OneToMany(mappedBy = "branch")
    private List<Staffs> staff;



}
