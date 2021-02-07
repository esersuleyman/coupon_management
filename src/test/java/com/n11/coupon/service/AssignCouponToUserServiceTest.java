package com.n11.coupon.service;

import com.n11.coupon.dao.Coupon;
import com.n11.coupon.datahelper.TestDAOFactory;
import com.n11.coupon.datahelper.TestDTOFactory;
import com.n11.coupon.dto.CouponDTO;
import com.n11.coupon.repository.CouponRepository;
import com.n11.coupon.service.impl.CouponServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync
public class AssignCouponToUserServiceTest {

    @Autowired
    private CouponServiceImpl couponService;

    @Autowired
    private CouponRepository couponRepository;


    @Autowired
    private AssignCouponToUserServiceClients assignCouponToUserServiceClients;

    @Test
    public void assign_coupon_to_users_with_100_clients_together_with_coupon_has_50() throws Exception {

        TestDTOFactory testDTOFactory = new TestDTOFactory();
        TestDAOFactory testDAOFactory = new TestDAOFactory();

        CouponDTO addedCouponDTO = testDTOFactory.couponDTO();


        couponService.createCoupon(addedCouponDTO);



        List<CompletableFuture> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture<String> future = assignCouponToUserServiceClients.call(1L);
            futures.add(future);
        }
        futures.stream().forEach(f -> CompletableFuture.allOf(f).join());
    }
}
