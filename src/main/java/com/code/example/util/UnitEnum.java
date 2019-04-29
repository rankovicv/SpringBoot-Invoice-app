package com.code.example.util;

/**
 * Created by veljko on 22.9.18.
 */
public enum UnitEnum {

    PIECE(0), HOUR(1), DAY(2);

    private final int number;

    private UnitEnum(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
