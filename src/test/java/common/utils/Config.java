package common.utils;

import org.testng.Assert;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class Config {

    private static String SYSTEM_TEST_PROP = "system.test.";
    private static String USER_DIR_PROP = "user.dir";

    public interface ConfigProps {
        // RUNTIME CONFIG
        String BROWSER = Config.getString("Browser");
    }

    public static String getString(final String parameterName) {
        String parameterValue = System.getProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase());
        if (parameterValue != null) {
            return parameterValue;
        }
        String propFromXML = getXPathValueFromFile(getConfigFileLocation(), getParameterValue(parameterName));
        System.setProperty(SYSTEM_TEST_PROP + parameterName.toLowerCase(), propFromXML);
        return propFromXML;
    }

    private static String getXPathValueFromFile(final String fileLocation, final String xpathQuery) {
        String value = null;
        try {
            File file = new File(fileLocation);
            DocumentBuilderFactory xmlFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = xmlFactory.newDocumentBuilder();
            Document xmlDoc = docBuilder.parse(file);
            XPathFactory xpathFact = XPathFactory.newInstance();
            XPath xpath = xpathFact.newXPath();
            value = (String) xpath.evaluate(xpathQuery, xmlDoc, XPathConstants.STRING);
        } catch (Exception e) {
            Assert.fail("Failed to retrieve configuration value from Config File at '" + fileLocation
                    + "' with xpath query '" + xpathQuery + "'.", e);
        }
        return value;
    }

    private static String getConfigFileLocation() {
        String fileLoc = System.getProperty(USER_DIR_PROP) + "/src/test/resources/TestNGSuiteConfig.xml";
        return fileLoc.replace("/", File.separator);
    }

    private static String getParameterValue(String parameterName) {
        return "//parameter[@name='" + parameterName + "']/@value";
    }
}
