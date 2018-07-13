package com.example.demo;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

    @Autowired
    private CakesRepository cakesRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        cakesRepository.deleteAll();

        Cake cake = new Cake("5b48bb1aa873178c033a3c07","Passion Fizz");
        cakesRepository.saveAll(
                List.of(new Cake("Red Velvet"),new Cake("Curly Whirly"), cake)
        );
    }

    @Test
    public void contextLoads() {
    }
}
