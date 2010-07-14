package com.mycompany.scenario;

import com.thoughtworks.selenium.Selenium;
import org.junit.Assert;

import static org.junit.Assert.*;


public class SearchScenario implements SeleniumScenario {
    public void execute(Selenium selenium) {
        selenium.open("/");
		assertEquals("Google", selenium.getTitle());
		selenium.type("q", "albert einstein");
		selenium.click("link=Albert Einstein - Wikipedia, the free encyclopedia");
		selenium.waitForPageToLoad("30000");
		assertEquals("Albert Einstein - Wikipedia, the free encyclopedia", selenium.getTitle());
    }
}
