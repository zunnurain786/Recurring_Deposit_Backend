package com.recurring_deposit;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RecurringDepositApplication {

    private static final Logger logger =
            LoggerFactory.getLogger(
                    RecurringDepositApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(
                RecurringDepositApplication.class,
                args);

        logger.info(
                "Recurring Deposit Application Started Successfully");
    }

    @Bean
    CommandLineRunner checkDb(DataSource dataSource) {
        return args -> {

            System.out.println("====================================");
            System.out.println("Connected Database URL:");
            System.out.println(
                    dataSource.getConnection()
                            .getMetaData()
                            .getURL()
            );
            System.out.println("====================================");

        };
    }
}
