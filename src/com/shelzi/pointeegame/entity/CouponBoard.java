package com.shelzi.pointeegame.entity;

import java.util.Arrays;

import static com.shelzi.pointeegame.constant.Constants.BOARD_SIZE;

public class CouponBoard {
    private final int[][] board;

    public CouponBoard() {
    }

    {
        board = new int[BOARD_SIZE][BOARD_SIZE];
        for (int[] row : board)
            Arrays.fill(row, 1);
    }

    public int[][] getBoard() {
        return board;
    }

    public void movePoint(int x, int y, int xOffset, int yOffset) {
        board[x + xOffset][y + yOffset]++;
        board[x][y]--;
    }

    private String printBoardRows() {
        StringBuilder result = new StringBuilder(885);
        for (int[] row : board) {
            result.append(Arrays.toString(row)).append("\n");
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponBoard that = (CouponBoard) o;
        return Arrays.deepEquals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public String toString() {
        return printBoardRows();
    }
}
