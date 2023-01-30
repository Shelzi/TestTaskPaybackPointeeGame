package com.shelzi.pointeegame.entity;

import java.awt.Point;
import java.util.Objects;

import static com.shelzi.pointeegame.constant.GameProperty.INDEX_TO_COORDINATES_OFFSET;

public class Coupon {
    private final int score;
    private final Point coordinates;

    public Coupon(int score, Point coordinates) {
        this.score = score;
        this.coordinates = coordinates;
    }

    public int getScore() {
        return score;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return score == coupon.score && coordinates.equals(coupon.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, coordinates);
    }

    @Override
    public String toString() {
        return String.format("Coupon value: %d. Coupon coordinates: x = %d, y = %d",
                score, coordinates.x + INDEX_TO_COORDINATES_OFFSET, coordinates.y + INDEX_TO_COORDINATES_OFFSET);
    }
}