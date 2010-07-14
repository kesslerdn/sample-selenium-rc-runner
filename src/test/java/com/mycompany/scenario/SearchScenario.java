package com.mycompany.scenario;

import com.thoughtworks.selenium.Selenium;
import org.junit.Assert;

import static org.junit.Assert.*;


public class SearchScenario implements SeleniumScenario {
    private Selenium selenium;

    
    public SearchScenario(Selenium selenium){
        this.selenium = selenium;
    }


    public void execute() {
        selenium.open("/");
		assertEquals("Wikipedia", selenium.getTitle());
		selenium.type("searchInput", "albert einstein");
		selenium.click("go");
		selenium.waitForPageToLoad("60000");
		assertEquals("Albert Einstein - Wikipedia, the free encyclopedia", selenium.getTitle());
    }
}
