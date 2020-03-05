package com.backendDevelopment.withtest.dbrestservice;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages({"com.backendDevelopment.withtest.dbrestservice.unittest","com.backendDevelopment.withtest.dbrestservice.integrationtest"})
@DisplayName("Test paint order CRUD")
public class DbrestserviceApplicationTests{
}
