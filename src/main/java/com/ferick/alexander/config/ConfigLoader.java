package com.ferick.alexander.config;

import org.apache.commons.configuration2.CombinedConfiguration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.MapConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigLoader {

    private static final Logger log = LoggerFactory.getLogger(ConfigLoader.class);
    private static final String PROPERTIES_FILE_NAME = "local.properties";
    private static ClassLoader classLoader = ConfigLoader.class.getClassLoader();

    private static CombinedConfiguration configuration = loadConfigurations();

    public static CombinedConfiguration getConfiguration() {
        return configuration;
    }

    private static CombinedConfiguration loadConfigurations() {
        Parameters params = new Parameters();

        FileBasedConfigurationBuilder<FileBasedConfiguration> localProperties =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class, null, true)
                .configure(params.properties().setURL(classLoader.getResource(PROPERTIES_FILE_NAME)));

        CombinedConfiguration configuration = new CombinedConfiguration();

        try {
            configuration.addConfiguration(new MapConfiguration(System.getProperties()));
            configuration.addConfiguration(localProperties.getConfiguration());
        } catch (ConfigurationException e) {
            log.error("It cannot load configurations for testing", e);
            System.exit(1);
        }
        return configuration;
    }
}
