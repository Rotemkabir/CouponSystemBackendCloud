package com.jb.couponsystem.utils;

public enum Colors {

    RESET("\033[0m"),
    BLACK("\033[30m"),
    RED("\033[31m"),
    GREEN("\033[32m"),
    CYAN("\033[36m"),
    YELLOW_BACKGROUND("\033[43m"),
    YELLOW_BACKGROUND_BRIGHT("\033[103m");

    private String ansiCode;

    public String getAnsiCode() {
        return ansiCode;
    }

    Colors(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    @Override
    public String toString() {
        return getAnsiCode();
    }
}
