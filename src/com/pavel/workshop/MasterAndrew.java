package com.pavel.workshop;

import com.pavel.workshop.base.Master;
import org.jetbrains.annotations.NotNull;

public class MasterAndrew extends Master {

    @Override
    protected @NotNull String masterName() {
        return "Andrew";
    }

    @Override
    protected @NotNull Integer minPrice() {
        return 5;
    }

    @Override
    protected @NotNull Integer maxPrice() {
        return 20;
    }
}
