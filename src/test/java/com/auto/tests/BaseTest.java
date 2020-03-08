package com.auto.tests;

import common.utils.Config;
import org.testng.annotations.BeforeSuite;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    protected static final String propFileName = "application.properties";
    protected static final String sysProp = "env";
    protected static String browserToRun = "";
    protected static final String browser = "browser";
    protected static String someUrl = "";

    @BeforeSuite(alwaysRun = true)
    public void setupEnv() {
        System.out.println("Setting environment....");
        //reading passed value from console:  -Denv=dev
        String environment = System.getProperty(sysProp);

        //if we didn't pass smth, then environment =qa
        if (environment == null) {
            environment = "qa";
        }
        System.out.println("environment: "+environment);
        //System.out.println("Browser..." + TestRun.getBrowser()); - not null if we run testng.xml
        browserToRun = System.getProperty(browser);
        if (browserToRun == null) {
            System.out.println("--------------------in null if--------------");
            //taking value from property file, if we didn't pass -Dbrowser in cmd
            //driver = prop.getProperty("browser");
            //or we can take default value from testng.xml
            browserToRun = Config.ConfigProps.BROWSER.toUpperCase();
        }
        System.out.println("========driver===" + browserToRun);

        //Reading from properties file
        InputStream inputStream;
        Properties prop = new Properties();
        inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        try {
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (IOException ex) {
            System.out.println("some exception has occured!");
        }
        System.out.println("environment BaseTest: "+ environment);
        someUrl = prop.getProperty(environment);
        System.out.println("someUrl: " + someUrl);
    }
}
