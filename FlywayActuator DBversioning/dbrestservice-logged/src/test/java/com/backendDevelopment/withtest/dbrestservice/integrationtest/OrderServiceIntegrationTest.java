package com.backendDevelopment.withtest.dbrestservice.integrationtest;

import com.backendDevelopment.withtest.dbrestservice.DbrestserviceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = DbrestserviceApplication.class
)
@AutoConfigureMockMvc
/*@Transactional*/
@TestPropertySource(locations="classpath:application-test.properties")
public class OrderServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void OrderReadRestIntegrationTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/view")
                .header("userId","JUnit_Test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        System.out.println(mvcResult.getResponse());
    }
}
