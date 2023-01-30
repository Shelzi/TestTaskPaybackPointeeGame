package com.shelzi.pointeegame.service;

import com.shelzi.pointeegame.entity.Coupon;
import com.shelzi.pointeegame.entity.CouponBoard;
import com.shelzi.pointeegame.entity.Direction;

import java.util.List;

public interface CouponBoardService {
    List<Coupon> findMaxCoupons(CouponBoard couponBoard);

    void movePointee(CouponBoard couponBoard, int x, int y, List<Direction> directions);

    void attack(CouponBoard couponBoard);
}