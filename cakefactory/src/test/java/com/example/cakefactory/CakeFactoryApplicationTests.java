package com.example.cakefactory;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids = "com.example:warehouse:+:stubs", stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class CakeFactoryApplicationTests {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    private final String cakeId = "5b48bb1aa873178c033a3c07";

    @Autowired
    private CakesRepository cakesRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        setupRestAssured();
        setupRepository();
    }

    private void setupRestAssured() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(documentationConfiguration(restDocumentation).uris()
                        .withScheme("https")
                        .withHost("cakefactory.pcfbeta.io")
                        .withPort(443)
                        .and()
                        .operationPreprocessors()
                        .withResponseDefaults(prettyPrint())
                        .withRequestDefaults(prettyPrint()))
                .build();

        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.resultHandlers(document("{class-name}/{method-name}"));
    }

    private void setupRepository() {
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
