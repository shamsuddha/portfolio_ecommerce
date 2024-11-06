package com.example.ecommerce_api.controller;

import com.example.ecommerce_api.controller.request_dto.CouponSearchDto;
import com.example.ecommerce_api.entity.Coupon;
import com.example.ecommerce_api.service.CouponService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
@AllArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/save")
    public ResponseEntity<Coupon> save(@RequestBody Coupon coupon) {
        return new ResponseEntity(couponService.saveCoupon(coupon), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/update")
    public ResponseEntity<Coupon> update(@RequestBody Coupon coupon) {
        return new ResponseEntity(couponService.updateCoupon(coupon), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Coupon coupon) {
        return new ResponseEntity(couponService.deleteCoupon(coupon), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Coupon>> search(@RequestBody CouponSearchDto couponSearchDto) {
        return new ResponseEntity(couponService.searchCoupon(couponSearchDto), HttpStatusCode.valueOf(200));
    }
}
