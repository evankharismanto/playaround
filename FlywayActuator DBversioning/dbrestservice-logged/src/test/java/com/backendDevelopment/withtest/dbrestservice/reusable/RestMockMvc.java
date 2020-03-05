package com.backendDevelopment.withtest.dbrestservice.reusable;

import com.backendDevelopment.withtest.dbrestservice.interfaces.MockInterface;
import com.backendDevelopment.withtest.dbrestservice.mockinjections.InjectMock;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.*;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.*;
import java.text.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RestMockMvc {
    protected MvcResult assertMockMvcRead(MockInterface mockInterface) throws Exception{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        InjectMock mockEntity = mockInterface.getMockValue();
        //region assertion
        MvcResult mvcResult = mockEntity.getMockMvc().perform(MockMvcRequestBuilders.get("/view")
                .header("userId","JUnit_Test")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line1 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line2 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line3 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*][?(@.date == '%s')]",
                        dateFormat.format(mockEntity.getOrders().get(0).getDate())).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer[?(@.name == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getName()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer[?(@.phoneNumber == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getPhoneNumber()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line1 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line2 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line3 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*][?(@.amount == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.color == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.type == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.litre == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getLitre()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*][?(@.amount == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.color == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.type == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.litre == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getLitre()).exists())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //endregion
        return mvcResult;
    }

    protected MvcResult assertMockMvcSave(MockInterface mockInterface) throws Exception{
        ObjectWriter objectWriter = (new ObjectMapper()).writerWithDefaultPrettyPrinter();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        InjectMock mockEntity = mockInterface.getMockValue();
        //region assertion
        MvcResult mvcResult = mockEntity.getMockMvc().perform(
                MockMvcRequestBuilders.post("/order")
                        .header("userId","JUnit_Test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(mockEntity.getOrders().get(0)))
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address[?(@.line1 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address[?(@.line2 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address[?(@.line3 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[?(@.date == '%s')]",
                        dateFormat.format(mockEntity.getOrders().get(0).getDate())).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer[?(@.name == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getName()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer[?(@.phoneNumber == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getPhoneNumber()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.address[?(@.line1 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.address[?(@.line2 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customer.address[?(@.line3 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*][?(@.amount == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.color == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.type == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.litre == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getLitre()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*][?(@.amount == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.color == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.type == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.items.[*].paint[?(@.litre == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getLitre()).exists())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //endregion
        return mvcResult;
    }

    protected MvcResult assertMockMvcUpdate(MockInterface mockInterface) throws Exception{
        ObjectWriter objectWriter = (new ObjectMapper()).writerWithDefaultPrettyPrinter();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        InjectMock mockEntity = mockInterface.getMockValue();
        String id = "1";
        //region assertion
        MvcResult mvcResult = mockEntity.getMockMvc().perform(
                MockMvcRequestBuilders.put("/replace/"+id)
                        .header("userId","JUnit_Test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectWriter.writeValueAsString(mockEntity.getOrders().get(0)))
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line1 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line2 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line3 == '%s')]",
                        mockEntity.getOrders().get(0).getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*][?(@.date == '%s')]",
                        dateFormat.format(mockEntity.getOrders().get(0).getDate())).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer[?(@.name == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getName()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer[?(@.phoneNumber == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getPhoneNumber()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line1 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine1()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line2 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine2()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line3 == '%s')]",
                        mockEntity.getOrders().get(0).getCustomer().getAddress().getLine3()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*][?(@.amount == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.color == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.type == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.litre == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(0).getPaint().getLitre()).exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*][?(@.amount == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getAmount()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.color == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getColor()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.type == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getType()).exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.litre == '%s')]",
                        mockEntity.getOrders().get(0).getItems().get(1).getPaint().getLitre()).exists())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //endregion
        return mvcResult;
    }

    protected MvcResult assertMockMvcDelete(MockInterface mockInterface) throws Exception{
        ObjectWriter objectWriter = (new ObjectMapper()).writerWithDefaultPrettyPrinter();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        InjectMock mockEntity = mockInterface.getMockValue();
        String id = "1";
        //region assertion
        MvcResult mvcResult = mockEntity.getMockMvc().perform(MockMvcRequestBuilders
            .delete("/remove/{id}", id)
            .header("userId","JUnit_Test")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line1 == '%s')]",
                    mockEntity.getOrders().get(0).getAddress().getLine1()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line2 == '%s')]",
                    mockEntity.getOrders().get(0).getAddress().getLine2()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].address[?(@.line3 == '%s')]",
                    mockEntity.getOrders().get(0).getAddress().getLine3()).doesNotExist())

            .andExpect(MockMvcResultMatchers.jsonPath("$[*][?(@.date == '%s')]",
                    dateFormat.format(mockEntity.getOrders().get(0).getDate())).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer[?(@.name == '%s')]",
                    mockEntity.getOrders().get(0).getCustomer().getName()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer[?(@.phoneNumber == '%s')]",
                    mockEntity.getOrders().get(0).getCustomer().getPhoneNumber()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line1 == '%s')]",
                    mockEntity.getOrders().get(0).getCustomer().getAddress().getLine1()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line2 == '%s')]",
                    mockEntity.getOrders().get(0).getCustomer().getAddress().getLine2()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].customer.address[?(@.line3 == '%s')]",
                    mockEntity.getOrders().get(0).getCustomer().getAddress().getLine3()).doesNotExist())

            .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*][?(@.amount == '%s')]",
                    mockEntity.getOrders().get(0).getItems().get(0).getAmount()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.color == '%s')]",
                    mockEntity.getOrders().get(0).getItems().get(0).getPaint().getColor()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.type == '%s')]",
                    mockEntity.getOrders().get(0).getItems().get(0).getPaint().getType()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.litre == '%s')]",
                    mockEntity.getOrders().get(0).getItems().get(0).getPaint().getLitre()).doesNotExist())

            .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*][?(@.amount == '%s')]",
                    mockEntity.getOrders().get(0).getItems().get(1).getAmount()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.color == '%s')]",
                    mockEntity.getOrders().get(0).getItems().get(1).getPaint().getColor()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.type == '%s')]",
                    mockEntity.getOrders().get(0).getItems().get(1).getPaint().getType()).doesNotExist())
            .andExpect(MockMvcResultMatchers.jsonPath("$[*].items.[*].paint[?(@.litre == '%s')]",
                    mockEntity.getOrders().get(0).getItems().get(1).getPaint().getLitre()).doesNotExist())
            .andDo(MockMvcResultHandlers.print())
            .andReturn();
        //endregion
        return mvcResult;
    }
}
