package entity;

import java.util.Arrays;

/*
Только что понял, что борда у меня хоть и имеет постоянный адрес в рамках объекта,
    но её внутреннее состояние меняется кем захочешь. Так что толку от этого класса
    особо никакого. Но если запихать все методы сервиса сюда, то это уже будет
    не энтити нихуя. А также при каждом изменении придётся возвращать новую доску.
    Какая-то залупа по итогу выходит
*/
public class CouponBoard {
    private final int[][] board;

    public CouponBoard(int size) {
        this.board = createBoard(size);
    }

    private int[][] createBoard(int size) {
        if (size != 0) {
            int[][] board = new int[size][size];
            for (int[] row : board)
                Arrays.fill(row, 1);
            return board;
        } else
            throw new RuntimeException("Invalid size"); //Подумать над тем, нужно ли здесь вообще заморачиваться с проверками и параметром size
    }

    public int[][] getBoard() {
        return board;
    }

    private String printBoardRows() {
        StringBuilder resultString = new StringBuilder(); // не хотелось бы использоать стрблд, но он недоволен конкатенацией в лупе. Типо, при обычном стринге он же будет каждый раз генерить новую строку
        for (int[] row : board) {
            resultString.append(String.format("%s\n", Arrays.toString(row)));
        }
        return resultString.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouponBoard that = (CouponBoard) o;
        return Arrays.equals(board, that.board);// хочет сделать deepequals
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(board); // хочет сделать deephashcode
    }

    @Override
    public String toString() {
        return String.format("Board: \n%s", printBoardRows());
    }
}