package com.mycompany.runner;

import com.mycompany.scenario.SearchScenario;
import com.mycompany.scenario.SeleniumScenario;
import com.mycompany.server.SeleniumRcServer;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.server.RemoteControlConfiguration;

import java.util.ArrayList;
import java.util.List;


public class GoogleRunnerTest {
    private static Selenium selenium;
    private List<SeleniumScenario> scenarios = new ArrayList<SeleniumScenario>();
    private SeleniumRcServer seleniumRcServer;

    @BeforeClass
    public static void startSeleniumRcServer(){
        SeleniumRcServer.startSeleniumServer();
        selenium = new DefaultSelenium("localhost", RemoteControlConfiguration.DEFAULT_PORT, "*chrome", "http://www.google.com");
    }

    @Before
    public void setup(){
        selenium.start();
        scenarios.add(new SearchScenario());
    }

    @Test
    public void testSearch(){
        for (SeleniumScenario scenario : scenarios) {
            scenario.execute(selenium);
        }
    }

    @AfterClass
    public static void stopSeleniumRcServer(){
        SeleniumRcServer.stopSeleniumServer(selenium);
    }
    
}
