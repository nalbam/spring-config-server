package com.nalbam.config.server;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigServerApplication.class, webEnvironment = RANDOM_PORT)
public class ConfigServerApplicationTests {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @LocalServerPort
    private final int port = 0;

    @Test
    public void health() {
        this.log.info("health port : " + this.port);

        final String url = "http://localhost:" + this.port + "/health";

        final RestTemplate restTemplate = new RestTemplate();

        final ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        this.log.info("health code : " + response.getStatusCode());
        this.log.info("health body : " + response.getBody());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

}
