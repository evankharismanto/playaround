package com.backendDevelopment.withtest.dbrestservice.mockinjections;

import com.backendDevelopment.withtest.dbrestservice.repositories.OrderRepository;
import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.models.Order;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.stereotype.*;
import org.mockito.stubbing.Answer;
import org.mockito.Mockito;
import java.util.List;
import java.util.stream.Collectors;
import lombok.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@NoArgsConstructor
@Controller @Setter
@Component("mockRepository")
public class MockOrderRepository implements MockInterface {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Getter
    InjectMock mockValue = new InjectMock();
    @MockBean
    @Getter(AccessLevel.NONE)
    private OrderRepository orderRepository;
    @Override
    public void InitiateMockOrder(){
        mockValue.setMockMvc(MockMvcBuilders.webAppContextSetup(webApplicationContext).build());
        mockValue.injectMockValue();
        List<Order> mockItems = mockValue.getOrders();
        Mockito.when(orderRepository.findAll()).thenReturn(
                mockItems
        );
        Mockito.when(orderRepository.save(Mockito.any(Order.class)))
        .thenAnswer(
                i -> i.getArguments()[0]
        )
        .then(new Answer<Void>() {
              @Override
              public Void answer(final InvocationOnMock invocation) {
                  Order orderActual = (Order) invocation.getArguments()[0];
                  assertEquals(mockValue.getOrders().get(0), orderActual);
                  return null;
              }
        });
        Mockito.when(orderRepository.findById(Mockito.any(Integer.class)))
        .thenAnswer(invocation ->
                mockItems.stream().filter(r -> r.getId() == invocation.getArgument(0)).findFirst()
        );
    }

    @Override
    public void ClearMockOrder(){
        mockValue.injectMockValue();
        List<Order> mockItems = mockValue.getOrders();
        List<Order> orders = mockItems.stream().filter(r -> r.getId() != mockItems.get(0).getId()).collect(Collectors.toList());
        Mockito.when(orderRepository.findAll()).thenReturn(
                orders
        );
    }

    @Override
    public Object getServiceController() {
        return orderRepository;
    }
}
