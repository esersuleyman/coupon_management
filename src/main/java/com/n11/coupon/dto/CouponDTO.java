package com.n11.coupon.dto;

public class CouponDTO {

    private Long id;
    private String name;
    private Double discountRate;
    private Boolean active;
    private Integer assignCount;

    public CouponDTO(){
    }

    public CouponDTO(String name, Double discountRate, Boolean active, Integer assignCount){
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
