package com.example.cakefactory;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(secure = false)
@AutoConfigureStubRunner(ids = "com.example:warehouse:+:stubs", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class CakefactoryApplicationTests {

    private final String cakeId = "5b48bb1aa873178c033a3c07";

    @Autowired
    private CakesRepository cakesRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);

        cakesRepository.deleteAll();

        Cake cake = new Cake(cakeId,"Passion Fizz");
        cakesRepository.saveAll(
                Arrays.asList(new Cake("Red Velvet"), new Cake("Curly Whirly"), cake)
        );
    }

    @Test
    public void shouldFetchStockInformation() throws Exception {
        int expectedValueFromWarehouseStub = 2;
        mockMvc
                .perform(get("/cake/{cakeId}", cakeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.qty", is(expectedValueFromWarehouseStub)));
    }
}
