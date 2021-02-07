package com.n11.coupon.repository;

import com.n11.coupon.dao.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{

    Coupon findOneById(Long id);
}
