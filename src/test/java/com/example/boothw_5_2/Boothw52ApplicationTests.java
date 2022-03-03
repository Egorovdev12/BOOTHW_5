package com.example.boothw_5_2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Boothw52ApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    private static GenericContainer<?> myPrivateApp = new GenericContainer<>("privateapp:latest")
            .withExposedPorts(8081);
    private static GenericContainer<?> myPublicApp = new GenericContainer<>("publicapp:latest")
            .withExposedPorts(8080);

    @BeforeAll
    public static void setUp() {
        myPrivateApp.start();
        myPublicApp.start();
    }

    @Test
    void contextLoads() {
        int portPrivateApp = myPrivateApp.getMappedPort(8081);
        int portPublicApp = myPublicApp.getMappedPort(8080);

        ResponseEntity<String> forPrivateEntity = testRestTemplate.getForEntity("http://localhost:" + portPrivateApp + "/hello", String.class);
        System.out.println(forPrivateEntity.getBody());
        ResponseEntity<String> forPublicEntity = testRestTemplate.getForEntity("http://localhost:" + portPublicApp + "/hello", String.class);
        System.out.println(forPublicEntity.getBody());
    }

}
