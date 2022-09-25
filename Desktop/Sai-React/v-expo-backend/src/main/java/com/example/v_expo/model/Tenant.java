package com.example.v_expo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tenant")
@Data
@ToString
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "comapany_email")
    private String companyEMail;

    @Column(name = "personal_email")
    private String personalEMail;

    @Column(name = "place")
    private String place;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private Category category;

    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private Timestamp updatedDate;

//    @OneToOne(mappedBy = "id", fetch = FetchType.LAZY)
////    @JoinColumn(name = "subscription_id", insertable = false, updatable = false)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private Subscription subscription;

}
