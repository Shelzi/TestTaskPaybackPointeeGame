package service.impl;

import entity.Coupon;
import entity.CouponBoard;
import entity.Direction;
import service.CouponBoardService;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
        int min = 0;
        List<Coupon> couponList = new ArrayList<>();
        int[][] board = couponBoard.getBoard();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] > min) {
                    min = board[i][j];
                    couponList.clear();
                    couponList.add(new Coupon(min, new Point(i, j)));
                } else if (board[i][j] == min) {
                    couponList.add(new Coupon(min, new Point(i, j)));
                }
            }
        }
        return couponList;
    }

    @Override
    public void movePointee(CouponBoard board, int x, int y, Direction... directions) {
        Random random = new Random();
        int jumpAngle = random.nextInt(0, directions.length);
        switch (directions[jumpAngle]) {
            case RIGHT -> changeCell(board, x, y, 0, 1);
            case UP -> changeCell(board, x, y, -1, 0);
            case LEFT -> changeCell(board, x, y, 0, -1);
            case DOWN -> changeCell(board, x, y, 1, 0);
        }
    }

    @Override
    public void attack(CouponBoard couponBoard) {
        int[][] board = couponBoard.getBoard(); //типа, толку от этого по итогу
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] != 0) {
                    if (x == 0) {
                        if (y == 0) {
                            movePointee(couponBoard, x, y, Direction.RIGHT, Direction.DOWN);
                        } else if (y < board.length - 1) {
                            movePointee(couponBoard, x, y, Direction.RIGHT, Direction.LEFT, Direction.DOWN);
                        } else {
                            movePointee(couponBoard, x, y, Direction.LEFT, Direction.DOWN);
                        }
                    } else if (x < board.length - 1) {
                        if (y == 0) {
                            movePointee(couponBoard, x, y, Direction.RIGHT, Direction.UP, Direction.DOWN);
                        } else if (y < board.length - 1) {
                            movePointee(couponBoard, x, y, Direction.RIGHT, Direction.UP, Direction.LEFT, Direction.DOWN);
                        } else {
                            movePointee(couponBoard, x, y, Direction.UP, Direction.LEFT, Direction.DOWN);
                        }
                    } else {
                        if (y == 0) {
                            movePointee(couponBoard, x, y, Direction.RIGHT, Direction.UP);
                        } else if (y < board.length - 1) {
                            movePointee(couponBoard, x, y, Direction.RIGHT, Direction.UP, Direction.LEFT);
                        } else {
                            movePointee(couponBoard, x, y, Direction.UP, Direction.LEFT);
                        }
                    }
                }
            }
        }
    }

    private void changeCell(CouponBoard board, int x, int y, int xOffset, int yOffset) {
        board.getBoard()[x + xOffset][y + yOffset] += 1; // возможно стоит сделать борду паблик?
        board.getBoard()[x][y] -= 1; //а то такое так явно никто не обращается, за такое меня нахуй отпиздят явно
    }
}