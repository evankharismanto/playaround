package com.backendDevelopment.withtest.dbrestservice.unittest;

import com.backendDevelopment.withtest.dbrestservice.repositories.OrderRepository;
import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Test CRUDService")
public class OrderServiceUnitTest {
    @Autowired
    @Qualifier("mockRepository")
    MockInterface mockInterface;
    @Autowired
    OrderService ordService;

    @BeforeEach
    void setUp() throws JsonProcessingException {
        mockInterface.InitiateMockOrder();
    }

    @Test
    @DisplayName("Test get all orders: \"getAll()\"")
    void OrderReadServiceTest() throws Exception {
        assertEquals(ordService.getAll(),mockInterface.getMockValue().getOrders());
    }

    @Test
    @DisplayName("Test save new order: \"store(Order a)\"")
    void OrderSaveServiceTest() throws Exception {
        OrderRepository ordRepository = (OrderRepository)mockInterface.getServiceController();
        Order storedOrder = ordService.store(mockInterface.getMockValue().getOrders().get(0));
        verify(ordRepository).save(any(Order.class));
        assertEquals(storedOrder,mockInterface.getMockValue().getOrders().get(0));
    }

    @Test
    @DisplayName("Test update order by id: \"store(Integer id,Order a)\"")
    void OrderUpdateServiceTest() throws Exception {
        OrderRepository ordRepository = (OrderRepository)mockInterface.getServiceController();
        Order storedOrder = ordService.store(mockInterface.getMockValue().getOrders().get(0).getId(),mockInterface.getMockValue().getOrders().get(0));
        verify(ordRepository).save(any(Order.class));
        assertEquals(storedOrder,mockInterface.getMockValue().getOrders().get(0));
    }

    @Test
    @DisplayName("Test delete order by id: \"remove(Integer id)\"")
    void OrderDeleteServiceTest() throws Exception {
        OrderRepository ordRepository = (OrderRepository)mockInterface.getServiceController();
        mockInterface.ClearMockOrder();
        ordService.remove(mockInterface.getMockValue().getOrders().get(0).getId());
        verify(ordRepository).deleteById(any(Integer.class));
    }
}
