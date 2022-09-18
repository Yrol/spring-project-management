package com.yrol.pma.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Integration test for controllers and views
 * webEnvironment - uses a random port to run the tests
 * */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class HttpRequestTest {

    //Getting the random port allocated
    @LocalServerPort
    private int port;

    //TestRestTemplate used for mimicking HTTP requests. Can perform POST, GET & etc.
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void homePageReturnsVersionNumberCorrectly(){

        //getting the rendered HTML home page
        String renderedHtml = this.restTemplate.getForObject("http://localhost:" + port + "/", String.class);

        assertEquals(renderedHtml.contains("3.0.0"), true);
    }

}
