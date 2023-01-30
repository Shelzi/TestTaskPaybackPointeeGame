package com.shelzi.pointeegame;

import com.shelzi.pointeegame.entity.Coupon;
import com.shelzi.pointeegame.entity.CouponBoard;
import com.shelzi.pointeegame.service.CouponBoardService;
import com.shelzi.pointeegame.service.impl.CouponBoardServiceImpl;

import java.util.List;

import static com.shelzi.pointeegame.constant.Constants.*;

public class CouponGame {
    private static final CouponBoardService service = CouponBoardServiceImpl.getInstance();

    public static void main(String[] args) {
        CouponGame game = new CouponGame();
        game.startGame(ROUNDS_25);
        game.startGame(ROUNDS_50);
        game.startGame(ROUNDS_100);
    }

    public void startGame(int rounds) {
        CouponBoard couponBoard = new CouponBoard();
        System.out.printf("Start board:\n%s", couponBoard);
        for (int i = 0; i < rounds; i++) {
            service.attack(couponBoard);
        }
        System.out.printf("Final board after %d attacks:\n%s", rounds, couponBoard);
        List<Coupon> couponList = service.findMaxCoupons(couponBoard);
        System.out.printf("Max coupon score: %d.\nCoupons:\n%s\n\n", couponList.get(0).getScore(), couponList);
    }
}