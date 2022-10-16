package com.example.v_expo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
//    @Transient
    private Long tenantId;

    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private Timestamp updatedDate;

//    @ManyToOne
//    @Fetch(value = FetchMode.SELECT)
//    @JoinColumn(name = "tenant_id", referencedColumnName = "id", insertable = false, updatable = false)
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnoreProperties("product")
//    private Tenant tenant;

//    @ManyToOne
//    @JoinColumn(name="tenant_id", nullable=false)
//    private Tenant tenant;
    @ManyToOne
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Tenant tenant;

}
