package com.code.example.util;

/**
 * Created by veljko on 22.9.18.
 */
public enum UnitEnum {

    PIECE(1), HOUR(2), DAY(3);

    private final int number;

    private UnitEnum(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
