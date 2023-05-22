package de.hsb.counter.counting.api;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
public class DatabaseProvider {

    private static final String POSTGRES_VERSION = "postgres:13.3";

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>(POSTGRES_VERSION)
            .withDatabaseName("database")
            .withUsername("username")
            .withPassword("password");

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        container.start();
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }
}
