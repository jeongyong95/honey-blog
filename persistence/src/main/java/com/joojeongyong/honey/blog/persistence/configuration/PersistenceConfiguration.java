package com.joojeongyong.honey.blog.persistence.configuration;

import com.joojeongyong.honey.blog.persistence.PersistenceMarker;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EntityScan(basePackageClasses = {PersistenceMarker.class})
@EnableJpaRepositories(basePackageClasses = {PersistenceMarker.class})
@Configuration
public class PersistenceConfiguration {

}
