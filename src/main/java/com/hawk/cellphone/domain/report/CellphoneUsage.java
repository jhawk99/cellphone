package com.hawk.cellphone.domain.report;

import com.hawk.cellphone.domain.report.model.CellphoneUsageData;

import java.io.IOException;

public interface CellphoneUsage {
    CellphoneUsageData generateForYear(final int year) throws IOException;
}
