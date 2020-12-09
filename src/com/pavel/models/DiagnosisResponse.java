package com.pavel.models;

import com.pavel.enums.BreakdownType;

public class DiagnosisResponse {

    public final String name;
    public final Integer price;
    public final BreakdownType breakdownType;

    public DiagnosisResponse(String name, Integer price, BreakdownType breakdownType) {
        this.name = name;
        this.price = price;
        this.breakdownType = breakdownType;
    }
}
