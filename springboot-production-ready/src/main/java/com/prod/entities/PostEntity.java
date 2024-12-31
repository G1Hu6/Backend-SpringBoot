package com.prod.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Audited
public class PostEntity extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //@NotAudited       // to do not tack by hibernate-envers
    private String description;

    @PrePersist
    void beforeSave(){
        System.out.println("Before Saving New Data");
    }

    @PreUpdate
    void beforeUpdate(){
        System.out.println("Before Update Old Data");
    }

    @PostRemove
    void beforeDelete(){
        System.out.println("Before Deleting Data");
    }
}
