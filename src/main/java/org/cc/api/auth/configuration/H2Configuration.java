package org.cc.api.auth.configuration;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
@EnableR2dbcRepositories
@PropertySource(value = {"classpath:application.yaml"})
@ConfigurationProperties(prefix = "spring.r2dbc")
public class H2Configuration extends AbstractR2dbcConfiguration {

    @Getter
    @Setter
    private String url;

    @Override
    public ConnectionFactory connectionFactory() {
        return ConnectionFactories.get(url);
    }

    @Bean
    public ConnectionFactoryInitializer initializer() {
        var initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory());

        var databasePopulator = new CompositeDatabasePopulator();
        databasePopulator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));
        initializer.setDatabasePopulator(databasePopulator);
        return initializer;
    }

}