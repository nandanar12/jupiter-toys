package com.planittesting.Jupiter.utilities.config;

import org.apache.commons.lang3.StringUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestConfiguration {
    public static final String TEST_ENV_PROPERTY_NAME = "test.env";
    public static Properties config;
    public static TestConfiguration instance = new TestConfiguration();

    public TestConfiguration() throws RuntimeException{
        String env = System.getProperty(TEST_ENV_PROPERTY_NAME);
        if (StringUtils.isBlank(env))
            env = "dev";

        loadEnvironmentConfiguration(env);
    }

    public void loadEnvironmentConfiguration(String env) {

        Properties myProps = new Properties();
        FileInputStream MyInputStream = null;
        String configFileName = "src/test/resources/config/" + env + ".properties";

        try{
            MyInputStream = new FileInputStream(configFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not get config file: " + configFileName, e);
        }
        try {
            myProps.load(MyInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to Load config file: " + configFileName, e);
        }
        config = (Properties) myProps.clone();
        config.put("env", env.toLowerCase());

    }

    public String getStringNotNull(String name)
    {
        String result = config.getProperty(name);
        result = StringUtils.trimToNull(result);
        if(result == null){
            throw new RuntimeException("Please specify property name: " + name);
        }else {
            return result;
        }

    }

}
