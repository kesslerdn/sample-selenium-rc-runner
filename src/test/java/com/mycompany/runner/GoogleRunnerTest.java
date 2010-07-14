package com.mycompany.runner;

import com.mycompany.scenario.SearchScenario;
import com.mycompany.scenario.SeleniumScenario;
import com.mycompany.server.SeleniumRcServer;
import org.junit.*;
import org.openqa.selenium.server.RemoteControlConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static com.thoughtworks.selenium.grid.tools.ThreadSafeSeleniumSessionStorage.closeSeleniumSession;
import static com.thoughtworks.selenium.grid.tools.ThreadSafeSeleniumSessionStorage.session;
import static com.thoughtworks.selenium.grid.tools.ThreadSafeSeleniumSessionStorage.startSeleniumSession;

public class GoogleRunnerTest {
    private static final int SEARCH_COUNT = 1;
    private List<SeleniumScenario> scenarios = new ArrayList<SeleniumScenario>();
    private SeleniumRcServer seleniumRcServer;

    @BeforeClass
    public static void startSeleniumRcServer(){
        SeleniumRcServer.startSeleniumServer();
    }

    @Before
    public void setUp(){
        startSeleniumSession("localhost", RemoteControlConfiguration.DEFAULT_PORT, "*firefox", "http://www.wikipedia.org");
        session().setTimeout("60000");
        for (int i = 0; i < SEARCH_COUNT; i++){
            scenarios.add(new SearchScenario(session()));
        }
        Collections.shuffle(scenarios);
    }

    @Test
    public void testSearch(){
        session().open("/");
        for (SeleniumScenario scenario : scenarios) {
            scenario.execute();
        }
    }

    @After
    public void tearDown(){
        closeSeleniumSession();
    }

    @AfterClass
    public static void stopSeleniumRcServer(){
        SeleniumRcServer.stopSeleniumServer();
    }

    
}
