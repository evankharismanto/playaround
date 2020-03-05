package com.backendDevelopment.withtest.dbrestservice.interfaces;

import com.backendDevelopment.withtest.dbrestservice.mockinjections.InjectMock;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MockInterface{
    InjectMock getMockValue();
    Object getServiceController();
    void InitiateMockOrder() throws JsonProcessingException;
    void ClearMockOrder() throws JsonProcessingException;
}
