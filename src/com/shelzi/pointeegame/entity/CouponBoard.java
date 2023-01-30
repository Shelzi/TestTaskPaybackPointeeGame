package com.shelzi.pointeegame.entity;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.shelzi.pointeegame.constant.GameProperty.BOARD_SIZE;

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
        int[][] flippedBoard = new int[BOARD_SIZE][BOARD_SIZE];
        IntStream.range(0, BOARD_SIZE).forEach(i ->
                IntStream.range(0, BOARD_SIZE).forEach(j -> {
                    flippedBoard[BOARD_SIZE - 1 - j][i] = board[i][j];
                }));
        StringBuilder result = new StringBuilder(885);
        for (int[] row : flippedBoard) {
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
