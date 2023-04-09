package com.digitalsettings.tms.util;

public enum CustomJwtClaims {
    SCOPE("scope");

    public final String value;

    CustomJwtClaims(String value) {
        this.value = value;
    }
}
