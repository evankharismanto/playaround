package com.backendDevelopment.withtest.dbrestservice.unittest;

import com.backendDevelopment.withtest.dbrestservice.repositories.OrderRepository;
import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.reusable.RestMockMvc;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.web.servlet.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DisplayName("Test Restfull-Persistence")
public class OrderRestUnitTest extends RestMockMvc {
    @Autowired
    @Qualifier("mockRepository")
    MockInterface mockRepositoryInterface;
    @Autowired
    @Qualifier("mockService")
    MockInterface mockServiceInterface;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockRepositoryInterface.InitiateMockOrder();
        mockServiceInterface.InitiateMockOrder();
    }

    @Test
    @DisplayName("Test get all orders: \"/view\"")
    void OrderReadRestTest() throws Exception {
        OrderRepository ordRepository = (OrderRepository)mockRepositoryInterface.getServiceController();
        MvcResult mvcResult = assertMockMvcRead(mockRepositoryInterface);
        System.out.println(mvcResult.getResponse());
        Mockito.verify(ordRepository).findAll();
    }

    @Test
    @DisplayName("Test save new order: \"/order\"")
    void OrderSaveRestTest() throws Exception{
        OrderRepository ordRepository = (OrderRepository)mockRepositoryInterface.getServiceController();
        MvcResult mvcResult = assertMockMvcSave(mockRepositoryInterface);
        System.out.println(mvcResult.getResponse());
        Mockito.verify(ordRepository).save(Mockito.any(Order.class));
    }

    @Test
    @DisplayName("Test update order by id: \"/replace/{id}\"")
    void OrderUpdateRestTest() throws Exception{
        OrderRepository ordRepository = (OrderRepository)mockRepositoryInterface.getServiceController();
        MvcResult mvcResult = assertMockMvcUpdate(mockRepositoryInterface);
        System.out.println(mvcResult.getResponse());
        Mockito.verify(ordRepository).save(Mockito.any(Order.class));
    }

    @Test
    @DisplayName("Test delete order by id: \"/remove/{id}\"")
    void OrderDeleteRestTest() throws Exception{
        OrderRepository ordRepository = (OrderRepository)mockRepositoryInterface.getServiceController();
        mockRepositoryInterface.ClearMockOrder();
        MvcResult mvcResult = assertMockMvcDelete(mockRepositoryInterface);
        System.out.println(mvcResult.getResponse());
        verify(ordRepository).deleteById(any(Integer.class));
    }
}
