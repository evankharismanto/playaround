package com.backendDevelopment.withtest.dbrestservice.unittest;

import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import com.backendDevelopment.withtest.dbrestservice.reusable.RestMockMvc;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.web.servlet.MvcResult;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

@SpringBootTest
@DisplayName("Test CRUDController")
public class OrderControllerUnitTest extends RestMockMvc {
    @Autowired
    @Qualifier("mockService")
    MockInterface mockInterface;
    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockInterface.InitiateMockOrder();
    }

    @Test
    @DisplayName("Test get all orders: \"/view\"")
    void OrderReadControllerTest() throws Exception {
        MvcResult mvcResult = assertMockMvcRead(mockInterface);
        System.out.println(mvcResult.getResponse());
        Mockito.verify((OrderService)mockInterface.getServiceController()).getAll();
    }

    @Test
    @DisplayName("Test save new order: \"/order\"")
    void OrderSaveControllerTest() throws Exception {
        MvcResult mvcResult = assertMockMvcSave(mockInterface);
        System.out.println(mvcResult.getResponse());
        OrderService ordService = (OrderService)mockInterface.getServiceController();
        Mockito.verify(ordService).store(Mockito.any(Order.class));
    }

    @Test
    @DisplayName("Test update order by id: \"/replace/{id}\"")
    void OrderUpdateControllerTest() throws Exception {
        MvcResult mvcResult = assertMockMvcUpdate(mockInterface);
        System.out.println(mvcResult.getResponse());
        OrderService ordService = (OrderService)mockInterface.getServiceController();
        Mockito.verify(ordService).store(Mockito.any(Integer.class),Mockito.any(Order.class));
    }

    @Test
    @DisplayName("Test delete order by id: \"/remove/{id}\"")
    void OrderDeleteControllerTest() throws Exception {
        OrderService ordService = (OrderService)mockInterface.getServiceController();
        mockInterface.ClearMockOrder();
        MvcResult mvcResult = assertMockMvcDelete(mockInterface);
        System.out.println(mvcResult.getResponse());
        Mockito.verify(ordService).remove(Mockito.any(Integer.class));
    }
}
