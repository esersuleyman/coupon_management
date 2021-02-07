package com.n11.coupon.dao;

import javax.persistence.*;

@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "discountRate")
    private Double discountRate;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "assignCount")
    private Integer assignCount;

    public Coupon(){
    }

    public Coupon(String name, Double discountRate, Boolean active, Integer assignCount){
        this.name = name;
        this.discountRate = discountRate;
        this.active = active;
        this.assignCount = assignCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getAssignCount() {
        return assignCount;
    }

    public void setAssignCount(Integer assignCount) {
        this.assignCount = assignCount;
    }
}
