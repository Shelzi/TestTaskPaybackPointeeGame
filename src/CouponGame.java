import entity.Coupon;
import entity.CouponBoard;
import service.CouponBoardService;
import service.impl.CouponBoardServiceImpl;

import java.util.List;

public class CouponGame {
    private static final int ROUNDS_25 = 25;
    private static final int ROUNDS_50 = 50;
    private static final int ROUNDS_100 = 100;
    private static final int BOARD_SIZE = 15;
    private static final CouponBoardService service = CouponBoardServiceImpl.getInstance();

    public static void main(String[] args) {
        CouponGame game = new CouponGame();
        game.startGame(ROUNDS_25);
        game.startGame(ROUNDS_50);
        game.startGame(ROUNDS_100);
    }

    public void startGame(int rounds) {
        CouponBoard couponBoard = new CouponBoard(BOARD_SIZE);
        System.out.printf("Start board:\n%s", couponBoard);
        for (int i = 0; i < rounds; i++) {
            service.attack(couponBoard);
        }
        System.out.printf("Final board after %d attacks:\n%s", rounds, couponBoard);
        List<Coupon> couponList = service.findMaxCoupons(couponBoard);
        System.out.printf("Max coupon score: %d.\nCoupons:\n%s\n\n", couponList.get(0).getScore(), couponList);
    }
}