package com.pavel.workshop;

import com.pavel.workshop.base.Master;
import org.jetbrains.annotations.NotNull;

public class MasterKolya extends Master {

    @Override
    protected @NotNull String masterName() {
        return "Kolya";
    }

    @Override
    protected @NotNull Integer minPrice() {
        return 1;
    }

    @Override
    protected @NotNull Integer maxPrice() {
        return 10;
    }
}
