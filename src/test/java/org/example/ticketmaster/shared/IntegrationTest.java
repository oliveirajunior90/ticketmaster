package org.example.ticketmaster.shared;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
public abstract class IntegrationTest {

    private static final String DB_NAME = "testdb";
    private static final String DB_USERNAME = "test";
    private static final String DB_PASSWORD = "test";
    private static final int POSTGRES_PORT = 5432;

    static GenericContainer<?> postgreSQLContainer = new GenericContainer<>(DockerImageName.parse("postgres:16"))
            .withEnv("POSTGRES_DB", DB_NAME)
            .withEnv("POSTGRES_USER", DB_USERNAME)
            .withEnv("POSTGRES_PASSWORD", DB_PASSWORD)
            .withExposedPorts(POSTGRES_PORT)
            .waitingFor(Wait.forLogMessage(".*database system is ready to accept connections.*\\n", 1))
            .withReuse(true);

    @BeforeAll
    static void startContainer() {
        postgreSQLContainer.start();
    }

    @AfterAll
    static void stopContainer() {
        postgreSQLContainer.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", IntegrationTest::jdbcUrl);
        registry.add("spring.datasource.username", () -> DB_USERNAME);
        registry.add("spring.datasource.password", () -> DB_PASSWORD);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    private static String jdbcUrl() {
        return "jdbc:postgresql://"
                + postgreSQLContainer.getHost()
                + ":"
                + postgreSQLContainer.getMappedPort(POSTGRES_PORT)
                + "/"
                + DB_NAME;
    }
}

