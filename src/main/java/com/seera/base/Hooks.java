package com.seera.base;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class  Hooks {

    @After
    public void afterScenario(Scenario scenario){
        scenario.write(CustomLogFilter.getRequestBuilder().toString());
        scenario.write(CustomLogFilter.getResponseBuilder().toString());
    }

    @Before
    public void beforeScenario(){
        System.out.println("Test");
    }
}
