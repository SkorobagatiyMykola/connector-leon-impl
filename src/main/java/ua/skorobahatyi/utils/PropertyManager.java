package ua.skorobahatyi.utils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class PropertyManager {
    private static final String PROPERTIES_FILE = "application.properties";
    private Properties props = null;

    public PropertyManager() {
        loadProperties(PROPERTIES_FILE);
    }

    private void loadProperties(String filename) {
        props = new Properties();
        InputStream instream = null;
        // find the file as application resource
        ClassLoader loader = this.getClass().getClassLoader();
        instream = loader.getResourceAsStream(filename);
        if (instream == null) {
            System.err.println("Unable to open properties file " + filename);
            return;
        }
        try {
            props.load(instream);
        } catch (IOException e) {
            System.err.println("Error reading properties file " + filename);
            System.err.println(e.getMessage());
        }

        try {
            instream.close();
        } catch (IOException ioe) {

        }
    }

    public String getProperty(String name) {
        return props.getProperty(name);
    }

    public List<String> getPropertyList(String name) {
        String valueStr = props.getProperty(name);
        String[] array = valueStr.split(",");

        return Arrays.asList(array);
    }

    public String[] getPropertyArray(String name) {
        String valueStr = props.getProperty(name);
        String[] array = valueStr.split(",");
        return array;
    }
}
