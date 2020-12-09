package com.pavel.workshop;

import com.pavel.workshop.base.Master;
import org.jetbrains.annotations.NotNull;

public class MasterGeorge extends Master {

    @Override
    protected @NotNull String masterName() {
        return "George";
    }

    @Override
    protected @NotNull Integer minPrice() {
        return 1;
    }

    @Override
    protected @NotNull Integer maxPrice() {
        return 30;
    }
}
