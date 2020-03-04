package com.backendDevelopment.withtest.dbrestservice.mockinjections;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.backendDevelopment.withtest.dbrestservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import java.sql.Date;
import java.util.*;
import lombok.*;

@Getter @Setter
@AutoConfigureMockMvc
public class InjectMock{
    @Autowired
    private MockMvc mockMvc;
    private List<Order> orders;
    public void injectMockValue(){
        //region init mock order list
        orders = new ArrayList<>(Arrays.asList(
                Order.builder()
                        .id(1)
                        .date(Date.valueOf("2020-2-21"))
                        .delivery(1)
                        .address(
                                Address.builder()
                                        .id(1)
                                        .line1("address1.line1")
                                        .line2("address1.line2")
                                        .line3("address1.line3")
                                        .build()
                        )
                        .customer(
                                Customer.builder()
                                        .id(1)
                                        .name("taylor")
                                        .phoneNumber("012151435")
                                        .address(
                                                Address.builder()
                                                        .id(2)
                                                        .line1("cust1.address1.line1")
                                                        .line2("cust1.address1.line2")
                                                        .line3("cust1.address1.line3")
                                                        .build()
                                        )
                                        .build()
                        )
                        .items(new ArrayList<>(Arrays.asList(
                                Item.builder()
                                        .id(1)
                                        .amount(2)
                                        .paint(
                                                Paint.builder()
                                                        .id(1)
                                                        .type("shiny")
                                                        .color("green")
                                                        .litre(5)
                                                        .build()
                                        )
                                        .build()
                                ,
                                Item.builder()
                                        .id(2)
                                        .amount(5)
                                        .paint(
                                                Paint.builder()
                                                        .id(2)
                                                        .type("weather")
                                                        .color("blue")
                                                        .litre(5)
                                                        .build()
                                        )
                                        .build()
                        )))
                        .build()
        ));
        //endregion
    }
}
