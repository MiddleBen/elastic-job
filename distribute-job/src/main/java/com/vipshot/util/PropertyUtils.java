package com.vipshot.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

	public static Properties loadLocalProperties(String path) throws IOException {
        Properties properties = new Properties();
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
            properties.load(input);
        } catch (final IOException ex) {
            throw ex;
        }
        return properties;
    }
	
}
