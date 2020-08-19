package com.hawk.cellphone.domain.repository;

import com.hawk.cellphone.domain.entity.CellphoneUse;

import java.io.IOException;
import java.util.function.Consumer;

public interface CellphoneRepository {
    void findByYear(final int year, Consumer<CellphoneUse> consumer) throws IOException;

}
