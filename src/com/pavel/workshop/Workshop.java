package com.pavel.workshop;

import com.pavel.enums.BreakdownType;
import com.pavel.models.DiagnosisResponse;
import com.pavel.workshop.base.Master;

import static com.pavel.utils.Utils.getRandom;

public class Workshop {

    private final Master[] masters = {new MasterAndrew(), new MasterGeorge(), new MasterKolya()};

    public String getAverageRepairPrice(BreakdownType breakdownType) {
        Double priceSum = 0.0;
        for (Master master : masters) {
            priceSum += master.repairPrice(breakdownType);
        }
        return String.format("%.2f", priceSum / masters.length);
    }

    public DiagnosisResponse makeDiagnosis(String subjectOfRepair) {
        return getRandom(masters).diagnosis(subjectOfRepair);
    }

    public void repair(DiagnosisResponse diagnosisResponse) {
        getRandom(masters).repair(diagnosisResponse);
    }
}
