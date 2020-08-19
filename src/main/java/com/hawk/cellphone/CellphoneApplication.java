package com.hawk.cellphone;

import com.hawk.cellphone.domain.report.CellphoneUsage;
import com.hawk.cellphone.domain.report.model.CellphoneUsageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CellphoneApplication implements CommandLineRunner {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CellphoneApplication.class);

    private final CellphoneUsage cellphoneUsage;

    @Value("${cellphoneusage.report.year}")
    private int year;

    @Autowired
    CellphoneApplication(CellphoneUsage report) {
        this.cellphoneUsage = report;
    }

    public static void main(String[] args) {
        SpringApplication.run(CellphoneApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            CellphoneUsageData data = cellphoneUsage.generateForYear(year);
            System.out.println("Cellphone Usage Report");
            System.out.println(data.toString());
        } catch(Exception e) {
            log.error("Error in CellphoneApplication.",e);
        }
    }

}
