package com.shelzi.pointeegame.service.impl;

import com.shelzi.pointeegame.entity.Coupon;
import com.shelzi.pointeegame.entity.CouponBoard;
import com.shelzi.pointeegame.entity.Direction;
import com.shelzi.pointeegame.service.CouponBoardService;

import java.awt.Point;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.shelzi.pointeegame.constant.Constants.MAX_COORDINATE_FOR_MOVEMENT;
import static com.shelzi.pointeegame.entity.Direction.*;
import static java.util.stream.Collectors.groupingBy;

public class CouponBoardServiceImpl implements CouponBoardService {
    private static final Lock locker = new ReentrantLock();
    private static volatile CouponBoardService instance;

    private CouponBoardServiceImpl() {
    }

    public static CouponBoardService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new CouponBoardServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public List<Coupon> findMaxCoupons(CouponBoard couponBoard) {
        int max = 0;
        List<Coupon> couponList = new ArrayList<>();
        int[][] board = couponBoard.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] > max) {
                    max = board[i][j];
                    couponList.clear();
                    couponList.add(new Coupon(max, new Point(i, j)));
                } else if (board[i][j] == max) {
                    couponList.add(new Coupon(max, new Point(i, j)));
                }
            }
        }
        return couponList;
/*        int max = Stream.of(couponBoard.getBoard())
                .flatMapToInt(IntStream::of)
                .summaryStatistics().getMax();
        couponBoard.getBoard().*/
    }

    @Override
    public void movePointee(CouponBoard board, int x, int y, List<Direction> directions) {
        Random random = new Random();
        int jumpAngle = random.nextInt(0, directions.size());
        switch (directions.get(jumpAngle)) {
            case RIGHT -> board.movePoint(x, y, 0, 1);
            case UP -> board.movePoint(x, y, -1, 0);
            case LEFT -> board.movePoint(x, y, 0, -1);
            case DOWN -> board.movePoint(x, y, 1, 0);
        }
    }

    private List<Direction> findAvailableDirections(int x, int y) {
        List<Direction> directions = new ArrayList<>();
        if (y < MAX_COORDINATE_FOR_MOVEMENT) {
            directions.add(RIGHT);
        }
        if (x < MAX_COORDINATE_FOR_MOVEMENT) {
            directions.add(DOWN);
        }
        if (x > 0) {
            directions.add(UP);
        }
        if (y > 0) {
            directions.add(LEFT);
        }
        return directions;
    }

    @Override
    public void attack(CouponBoard couponBoard) {
        int[][] board = couponBoard.getBoard();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] != 0) {
                    movePointee(couponBoard, x, y, findAvailableDirections(x, y));
                }
            }
        }
    }
}
