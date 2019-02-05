package com.test.ishara.newsfeed.type;

public enum CountryType {
    us("United States"), de("Germany"), fr("France");

    private String action;

    public String getAction() {
        return this.action;
    }
    // enum constructor - cannot be public or protected
    private CountryType(String action) {
        this.action = action;
    }
}
