package com.nalbam.config.server;

import com.nalbam.config.server.ConfigServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigServerApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class ConfigServerApplicationTests {

    @Value("${local.server.port}")
    private int port = 0;

    @Test
    public void envPostAvailable() {
        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        @SuppressWarnings("rawtypes")
        ResponseEntity<Map> entity = new TestRestTemplate().postForEntity("http://localhost:" + port + "/admin/env", form, Map.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

}
