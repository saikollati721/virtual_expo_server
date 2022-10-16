package com.example.v_expo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tenant")
@Data
@ToString
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

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

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
//    private Subscription subscription;

    @OneToOne(mappedBy = "tenant")
    private Subscription subscription;

//    @OneToMany(mappedBy = "tenant", fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("tenant")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private List<Product> products;
    @OneToMany(mappedBy="tenant")
    private List<Product> products;
}
