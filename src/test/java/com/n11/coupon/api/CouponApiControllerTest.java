package com.n11.coupon.api;

import com.n11.coupon.ResponseMessages;
import com.n11.coupon.datahelper.TestDTOFactory;
import com.n11.coupon.dto.CouponDTO;
import com.n11.coupon.service.impl.CouponServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
public class CouponApiControllerTest {

    @Spy
    @InjectMocks
    CouponApiController couponApiController;
    @Mock
    CouponServiceImpl couponService;


    @Test
    public void create_coupon() throws Exception {
        Mockito.when(this.couponService.createCoupon(Mockito.anyObject())).thenReturn(ResponseMessages.COUPON_CREATE_SUCCESS);
        TestDTOFactory testDTOFactory = new TestDTOFactory();

        String expected = ResponseMessages.COUPON_CREATE_SUCCESS;
        String actual = couponApiController.createCoupon(testDTOFactory.couponDTO());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void update_coupon() throws Exception {
        Mockito.when(this.couponService.updateCoupon(Mockito.anyObject())).thenReturn(ResponseMessages.COUPON_UPDATE_SUCCESS);
        TestDTOFactory testDTOFactory = new TestDTOFactory();

        String expected = ResponseMessages.COUPON_UPDATE_SUCCESS;
        String actual = couponApiController.updateCoupon(testDTOFactory.couponDTO());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void delete_coupon() throws Exception {
        Mockito.when(this.couponService.deleteCoupon(Mockito.anyLong())).thenReturn(ResponseMessages.COUPON_DELETE_SUCCESS);

        String expected = ResponseMessages.COUPON_DELETE_SUCCESS;
        String actual = couponApiController.deleteCoupon(1L);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get_coupons() throws Exception {
        TestDTOFactory testDTOFactory = new TestDTOFactory();
        List<CouponDTO> couponDTOS = testDTOFactory.couponDTOs();
        Mockito.when(this.couponService.getCoupons()).thenReturn(couponDTOS);

        List<CouponDTO> expected = couponDTOS;
        List<CouponDTO> actual = couponService.getCoupons();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get_coupon() throws Exception {
        TestDTOFactory testDTOFactory = new TestDTOFactory();
        CouponDTO couponDTO = testDTOFactory.couponDTO();
        Mockito.when(this.couponService.getCoupon(Mockito.anyLong())).thenReturn(couponDTO);

        CouponDTO expected = couponDTO;
        CouponDTO actual = couponService.getCoupon(1L);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void assign_coupon_to_user() throws Exception {
        TestDTOFactory testDTOFactory = new TestDTOFactory();
        CouponDTO couponDTO = testDTOFactory.couponDTO();
        Mockito.when(this.couponService.assignCouponToUser(Mockito.anyLong())).thenReturn(ResponseMessages.COUPON_ASSIGNED_TO_USER_SUCCCESS);

        String expected = ResponseMessages.COUPON_ASSIGNED_TO_USER_SUCCCESS;
        String actual = couponService.assignCouponToUser(1L);

        Assert.assertEquals(expected, actual);
    }

}
