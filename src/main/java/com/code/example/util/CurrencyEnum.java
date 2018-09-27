package com.code.example.util;

/**
 * Created by veljko on 26.9.18.
 */
public enum CurrencyEnum {

    EUR(0), USD(1), RSD(2);

    private final int number;

    private CurrencyEnum(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
