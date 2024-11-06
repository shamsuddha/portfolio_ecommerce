package com.example.ecommerce_api.service;


import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import com.example.ecommerce_api.controller.request_dto.CouponSearchDto;
import com.example.ecommerce_api.entity.Coupon;
import com.example.ecommerce_api.entity.QCoupon;
import com.example.ecommerce_api.repository.CouponRepository;
import com.example.ecommerce_api.service.common.EntityValidationService;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.ecommerce_api.service.predicate.CouponPredicate.makePredicate;

@Service
@AllArgsConstructor
public class CouponService {

  private final EntityManager entityManager;
  private final EntityValidationService entityValidationService;
  private final CouponRepository couponRepository;

  @org.springframework.transaction.annotation.Transactional
  public Coupon saveCoupon(Coupon coupon) {
    return this.couponRepository.save(coupon);
  }

  @org.springframework.transaction.annotation.Transactional
  public Coupon updateCoupon(Coupon coupon) {
    var couponDb = entityValidationService.validateCoupon(coupon.getId());
    couponDb.setDescription(coupon.getDescription());
    couponDb.setCode(coupon.getCode());
    couponDb.setAmount(coupon.getAmount());
    couponDb.setDiscountTypeKey(coupon.getDiscountTypeKey());
    couponDb.setDiscountTypeValue(coupon.getDiscountTypeValue());
    couponDb.setNumberOfUse(coupon.getNumberOfUse());
    couponDb.setStartAt(coupon.getStartAt());
    couponDb.setEndAt(coupon.getEndAt());
    couponDb.setBookingList(coupon.getBookingList());
    couponDb = couponRepository.save(couponDb);
    return couponDb;
  }

  @org.springframework.transaction.annotation.Transactional
  public String deleteCoupon(Coupon coupon) {
    var couponDb = entityValidationService.validateCoupon(coupon.getId());
    couponDb.setEnabled(Boolean.FALSE);
    couponRepository.save(couponDb);
    return "Coupon deleted successfully";
  }

  public Page<Coupon> searchCoupon(CouponSearchDto couponSearchDto) {
    Predicate predicate = makePredicate(couponSearchDto);
    Pageable pageable = PageRequest.of(couponSearchDto.getPage(), couponSearchDto.getSize());
    final QCoupon qCoupon = QCoupon.coupon;
    var query = new JPAQuery<Coupon>(entityManager)
            .from(qCoupon)
            .where(predicate)
            .limit(pageable.getPageSize())
            .offset(pageable.getOffset())
            .orderBy(qCoupon.createdDate.desc());
    return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
  }
}
