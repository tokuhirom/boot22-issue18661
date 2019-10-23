package com.example.boot22issue18661;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class Boot22Issue18661Application {

    public static void main(String[] args) {
        SpringApplication.run(Boot22Issue18661Application.class, args);
    }

    @Bean
    public AbstractRoutingDataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:test");
        HikariDataSource dataSource = new HikariDataSource(config);

        // This is a damn AbstractRoutingDataSource for demonstrate the issue.
        AbstractRoutingDataSource abstractRoutingDataSource =
                new AbstractRoutingDataSource() {
                    @Override
                    protected Object determineCurrentLookupKey() {
                        return dataSource;
                    }
                };

        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put("test", dataSource);
        abstractRoutingDataSource.setTargetDataSources(dataSources);

        return abstractRoutingDataSource;
    }
}
