package io.reflectoring.rentAcar.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "location")
@SQLDelete(sql = "UPDATE location SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Locations {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    private String street;
    private String district;
    private String city;
    private String country;

}
