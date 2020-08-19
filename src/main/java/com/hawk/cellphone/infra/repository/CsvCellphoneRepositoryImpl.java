package com.hawk.cellphone.infra.repository;

import com.hawk.cellphone.domain.entity.CellphoneUse;
import com.hawk.cellphone.domain.repository.CellphoneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Repository
@Slf4j
public class CsvCellphoneRepositoryImpl implements CellphoneRepository {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("M/d/yyyy");

    @Value("${cellphoneusage.csv.file}")
    String cellphoneCsvFile;

    Function<String,CellphoneUse> toCellPhoneUse = line -> {
        String[] rec = line.split(",");
        Integer id = Integer.parseInt(rec[0]);
        LocalDate date = LocalDate.parse(rec[1],DATE_FORMAT);
        double minutesUsed = Double.parseDouble(rec[2]);
        double dataUsed = Double.parseDouble(rec[3]);

        return new CellphoneUse(id,date,minutesUsed,dataUsed);
    };

    @Override
    public void findByYear(final int year,final Consumer<CellphoneUse> consumer) throws IOException {
        Predicate<CellphoneUse> filterByYear = cellPhoneUse -> {
            if(year == cellPhoneUse.getYear())
                return true;
            return false;

        };

        cellPhoneUsageData(readFile(),filterByYear,consumer);

    }

    void cellPhoneUsageData(final Stream<String> lines,final Predicate<CellphoneUse> filter,
                            final Consumer<CellphoneUse> consumer) throws IOException {
        lines.map(toCellPhoneUse).filter(filter).forEach(consumer);

    }

    Stream<String> readFile() throws IOException {
        return Files.lines(Paths.get(cellphoneCsvFile)).skip(1);
    }


}

