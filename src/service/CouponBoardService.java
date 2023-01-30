package service;

import entity.Coupon;
import entity.CouponBoard;
import entity.Direction;

import java.util.List;

public interface CouponBoardService {
    List<Coupon> findMaxCoupons(CouponBoard couponBoard); // передавать борду приходится везде, раз нет дб. Ну или делать её глобальной, что не круто

    void movePointee(CouponBoard couponBoard, int x, int y, Direction... directions);// название метода хуета возможно, надо подумать

    void attack(CouponBoard couponBoard);
}