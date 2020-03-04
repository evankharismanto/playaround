package com.backendDevelopment.withtest.dbrestservice.mockinjections;

import com.backendDevelopment.withtest.dbrestservice.controllers.CRUDController;
import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.services.OrderService;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.stereotype.*;
import java.util.stream.Collectors;
import java.util.List;
import org.mockito.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Controller
@Component("mockService")
public class MockOrderService implements MockInterface {
    @Getter
    InjectMock mockValue = new InjectMock();
    @Mock
    @Getter(AccessLevel.NONE)
    private OrderService orderService;
    @InjectMocks
    private CRUDController crudController;
    @Override
    public void InitiateMockOrder() throws JsonProcessingException {
        MockitoAnnotations.initMocks(this);
        mockValue.setMockMvc(MockMvcBuilders.standaloneSetup(crudController).build());
        mockValue.injectMockValue();
        List<Order> mockItems = mockValue.getOrders();
        Mockito.when(orderService.getAll()).thenReturn(
                mockItems
        );
        Mockito.when(orderService.store(Mockito.any(Order.class))).thenAnswer(
                i -> i.getArguments()[0]
        );
    }

    @Override
    public void ClearMockOrder() throws JsonProcessingException {
        mockValue.injectMockValue();
        List<Order> mockItems = mockValue.getOrders();
        List<Order> orders = mockItems.stream().filter(r -> r.getId() != mockItems.get(0).getId()).collect(Collectors.toList());
        Mockito.when(orderService.getAll()).thenReturn(
                orders
        );
    }

    @Override
    public Object getServiceController() {
        return orderService;
    }
}
