package com.pavel.workshop.base;

import com.pavel.DataBaseManager;
import com.pavel.enums.BreakdownType;
import com.pavel.enums.WorkType;
import com.pavel.models.DiagnosisResponse;
import com.pavel.models.Job;
import org.jetbrains.annotations.NotNull;

import static com.pavel.utils.Utils.randInt;
import static com.pavel.utils.Utils.randomEnum;

public abstract class Master {

    protected DataBaseManager dataBaseManager = new DataBaseManager();

    protected abstract @NotNull String masterName();

    protected abstract @NotNull Integer minPrice();

    protected abstract @NotNull Integer maxPrice();

    public DiagnosisResponse diagnosis(String subjectOfRepair) {
        dataBaseManager.addJob(new Job(masterName(), WorkType.DIAGNOSIS));
        return new DiagnosisResponse(
                subjectOfRepair,
                randInt(minPrice(), maxPrice()),
                randomEnum(BreakdownType.class)
        );
    }

    public Integer repairPrice(BreakdownType breakdownType) {
        switch (breakdownType) {
            case HARDWARE:
                return randInt(minPrice() * 2, maxPrice() * 2);
            case SOFTWARE:
                return randInt(minPrice(), maxPrice() / 2);
        }
        return 0;
    }

    public void repair(DiagnosisResponse diagnosisResponse) {
        dataBaseManager.addJob(new Job(masterName(), WorkType.REPAIR));
    }
}
