package com.example.Repository;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ivan on 03/06/16.
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example"})
@EnableJpaRepositories(basePackages ={"com.example.Repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
