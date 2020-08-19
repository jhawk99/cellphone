package com.hawk.cellphone.domain.report;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@Validated
@Component
@ConfigurationProperties("cellphoneusage.report")
public class CellphoneUsageProperties {

    @Min(value = 2000, message = "")
    @NumberFormat
    @Digits(integer = 4, fraction = 0)
    private int year;

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }
}
