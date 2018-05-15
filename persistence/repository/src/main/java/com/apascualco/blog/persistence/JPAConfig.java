package com.apascualco.blog.persistence;

import com.apascualco.blog.persistence.model.Audit;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackageClasses = Audit.class)
@EnableJpaRepositories(basePackageClasses = JPAConfig.class)
@EnableTransactionManagement
public class JPAConfig {

}