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
@Table(name = "subscription")
@Data
@ToString
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_type")
    private PlanType planType;

    @Column(name = "plan_mode")
    private PlanMode planMode;

    @Column(name = "tenant_id")
    private Long tenantId;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "next_billing_date")
    private Timestamp nextBillingDate;

    @Column(name = "created_date")
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private Timestamp updatedDate;

    @OneToOne
    @Fetch(value = FetchMode.SELECT)
    @JoinColumn(name = "tenant_id", referencedColumnName = "id", insertable = false, updatable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Tenant tenant;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @JsonIgnoreProperties("subscription")
//    private Tenant tenant;
}
