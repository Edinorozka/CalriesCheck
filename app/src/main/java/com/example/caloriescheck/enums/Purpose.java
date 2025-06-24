package com.example.caloriescheck.enums;

import lombok.Getter;

@Getter
public enum Purpose {
    DROP_WEIGHT((byte) 1),
    ADD_MUSCLES((byte) 2),
    SAVE_WEIGHT((byte) 3);

    private final byte value;

    Purpose(byte value) {
        this.value = value;
    }
}
