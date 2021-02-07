package com.n11.coupon.util;
import com.n11.coupon.dao.Coupon;
import com.n11.coupon.datahelper.TestDAOFactory;
import com.n11.coupon.datahelper.TestDTOFactory;
import com.n11.coupon.dto.CouponDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync
public class ModelMapperUtilTest {

    @Spy
    @InjectMocks
    ModelMapperUtil modelMapperUtil;

    @Test
    public void convert_entity_to_dto(){
        TestDTOFactory testDTOFactory = new TestDTOFactory();
        TestDAOFactory testDAOFactory = new TestDAOFactory();
        Coupon coupon = testDAOFactory.coupon();
        CouponDTO couponDTO = testDTOFactory.couponDTO();

        Mockito.when(this.modelMapperUtil.convertCouponEntitytoCouponDTO(coupon)).thenReturn(couponDTO);

        CouponDTO expected = couponDTO;
        CouponDTO actual = modelMapperUtil.convertCouponEntitytoCouponDTO(coupon);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void convert_dto_to_entity(){
        TestDTOFactory testDTOFactory = new TestDTOFactory();
        TestDAOFactory testDAOFactory = new TestDAOFactory();
        Coupon coupon = testDAOFactory.coupon();
        CouponDTO couponDTO = testDTOFactory.couponDTO();

        Mockito.when(this.modelMapperUtil.convertCouponDTOtoCouponEntity(couponDTO)).thenReturn(coupon);

        Coupon expected = coupon;
        Coupon actual = modelMapperUtil.convertCouponDTOtoCouponEntity(couponDTO);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void convert_entity_list_to_dto_list(){
        TestDTOFactory testDTOFactory = new TestDTOFactory();
        TestDAOFactory testDAOFactory = new TestDAOFactory();
        List<Coupon> coupons = testDAOFactory.coupons();
        List<CouponDTO> couponDTOs = testDTOFactory.couponDTOs();

        Mockito.when(this.modelMapperUtil.convertCouponEntityListtoCouponDTOList(coupons)).thenReturn(couponDTOs);

        List<CouponDTO> expected = couponDTOs;
        List<CouponDTO> actual = modelMapperUtil.convertCouponEntityListtoCouponDTOList(coupons);

        Assert.assertEquals(expected, actual);
    }
}
