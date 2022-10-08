package com.example.v_expo.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product")
@Data
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "offer_price")
    private Long offer_price;

    @Column(name = "original_price")
    private Long original_price;

    @Column(name = "tenant_id")
    private Long tenantId;

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
