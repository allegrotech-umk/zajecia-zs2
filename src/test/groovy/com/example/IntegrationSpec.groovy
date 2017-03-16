package com.example

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest(
        classes = DemoApplication,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
abstract class IntegrationSpec extends Specification {

    @Value('${local.server.port}')
    int port

    @Autowired
    TestRestTemplate restTemplate

    String localUrl(String endpoint) {
        "http://localhost:$port$endpoint"
    }
}
