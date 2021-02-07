package com.n11.coupon.service;

import com.n11.coupon.ResponseMessages;
import com.n11.coupon.dao.Coupon;
import com.n11.coupon.datahelper.TestDAOFactory;
import com.n11.coupon.datahelper.TestDTOFactory;
import com.n11.coupon.dto.CouponDTO;
import com.n11.coupon.repository.CouponRepository;
import com.n11.coupon.service.impl.CouponServiceImpl;
import com.n11.coupon.util.ModelMapperUtil;
import org.apache.coyote.Response;
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
public class CouponServiceTest {

    @Spy
    @InjectMocks
    private CouponServiceImpl couponService;

    @Mock
    private CouponRepository couponRepository;

    @Mock
    private ModelMapperUtil modelMapperUtil;

    @Test
    public void add_coupon() {

        TestDTOFactory testDTOFactory = new TestDTOFactory();
        TestDAOFactory testDAOFactory = new TestDAOFactory();
        Mockito.when(this.couponRepository.save(Mockito.anyObject())).thenReturn(testDAOFactory.coupon());
        String expected = ResponseMessages.COUPON_CREATE_SUCCESS;
        String actual = couponService.createCoupon(testDTOFactory.couponDTO());

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void update_coupon() throws Exception {

        TestDTOFactory testDTOFactory = new TestDTOFactory();
        TestDAOFactory testDAOFactory = new TestDAOFactory();

        CouponDTO updatedCouponDTO = testDTOFactory.couponDTO();

        couponService.createCoupon(updatedCouponDTO);

        updatedCouponDTO.setAssignCount(1000);
        updatedCouponDTO.setName("Updated Test Name");

        Mockito.when(this.couponRepository.findOneById(Mockito.anyLong())).thenReturn(testDAOFactory.coupon());
        Mockito.when(this.couponRepository.save(Mockito.anyObject())).thenReturn(testDAOFactory.coupon());

        String expected = ResponseMessages.COUPON_UPDATE_SUCCESS;
        String actual = couponService.updateCoupon(updatedCouponDTO);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void delete_coupon() throws Exception {

        TestDTOFactory testDTOFactory = new TestDTOFactory();
        TestDAOFactory testDAOFactory = new TestDAOFactory();

        CouponDTO updatedCouponDTO = testDTOFactory.couponDTO();

        couponService.createCoupon(updatedCouponDTO);

        Long deletedCouponId = 1L;

        Mockito.when(this.couponRepository.findOneById(Mockito.anyLong())).thenReturn(testDAOFactory.coupon());
        Mockito.when(this.couponRepository.save(Mockito.anyObject())).thenReturn(testDAOFactory.coupon());

        String expected = ResponseMessages.COUPON_DELETE_SUCCESS;
        String actual = couponService.deleteCoupon(deletedCouponId);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void get_coupons() {
        TestDAOFactory testDAOFactory = new TestDAOFactory();
        List<Coupon> coupons = testDAOFactory.coupons();
        TestDTOFactory testDTOFactory = new TestDTOFactory();

        Mockito.when(this.couponRepository.findAll()).thenReturn(coupons);

        List<CouponDTO> expectedProductList = modelMapperUtil.convertCouponEntityListtoCouponDTOList(coupons);
        List<CouponDTO> actualProductList = couponService.getCoupons();

        Assert.assertEquals(actualProductList, expectedProductList);

    }

    @Test
    public void get_coupon() throws Exception {
        TestDAOFactory testDAOFactory = new TestDAOFactory();
        Coupon coupon = testDAOFactory.coupon();
        TestDTOFactory testDTOFactory = new TestDTOFactory();

        Mockito.when(this.couponRepository.findOneById(Mockito.anyLong())).thenReturn(coupon);

        CouponDTO expectedCoupon = modelMapperUtil.convertCouponEntitytoCouponDTO(coupon);
        CouponDTO actualCoupon = couponService.getCoupon(coupon.getId());

        Assert.assertEquals(actualCoupon, expectedCoupon);

    }

    @Test
    public void assign_coupon_to_user() throws Exception {
        TestDTOFactory testDTOFactory = new TestDTOFactory();
        TestDAOFactory testDAOFactory = new TestDAOFactory();

        Coupon coupon = testDAOFactory.coupon();

        CouponDTO addedCouponDTO = testDTOFactory.couponDTO();

        couponService.createCoupon(addedCouponDTO);

        Long assignedCouponId = 1L;

        Mockito.when(this.couponRepository.findOneById(Mockito.anyLong())).thenReturn(coupon);

        couponService.assignCouponToUser(assignedCouponId);
        Integer expectedRemainingAssignCount = 49;
        Integer actualRemainingAssignCount = couponRepository.findOneById(assignedCouponId).getAssignCount();

        Assert.assertEquals(expectedRemainingAssignCount, actualRemainingAssignCount);

    }

}
