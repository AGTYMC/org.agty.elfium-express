package org.agty.elfiumexpress.utils;

/*import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;*/

import java.io.IOException;

/**
 * import java.io.File;
 * import java.io.IOException;
 *
 * import org.apache.commons.io.FileUtils;
 * import org.openqa.selenium.OutputType;
 * import org.openqa.selenium.TakesScreenshot;
 * import org.openqa.selenium.WebDriver;
 * import org.openqa.selenium.firefox.FirefoxBinary;
 * import org.openqa.selenium.firefox.FirefoxDriver;
 *
 * public class CaptureScreenshotTest
 * {
 *     private static int      DISPLAY_NUMBER  = 99;
 *     private static String   XVFB            = "/usr/bin/Xvfb";
 *     private static String   XVFB_COMMAND    = XVFB + " :" + DISPLAY_NUMBER;
 *     private static String   URL             = "http://www.google.com/";
 *     private static String   RESULT_FILENAME = "/tmp/screenshot.png";
 *
 *     public static void main ( String[] args ) throws IOException
 *     {
 *         Process p = Runtime.getRuntime().exec(XVFB_COMMAND);
 *         FirefoxBinary firefox = new FirefoxBinary();
 *         firefox.setEnvironmentProperty("DISPLAY", ":" + DISPLAY_NUMBER);
 *         WebDriver driver = new FirefoxDriver(firefox, null);
 *         driver.get(URL);
 *         File scrFile = ( (TakesScreenshot) driver ).getScreenshotAs(OutputType.FILE);
 *         FileUtils.copyFile(scrFile, new File(RESULT_FILENAME));
 *         driver.close();
 *         p.destroy();
 *     }
 * }
 *
 * Firefox will not run at all without the following libraries or packages:
 *      glibc 2.17 or higher
 *      GTK+ 3.14 or higher
 *      libglib 2.42 or higher
 *      libstdc++ 4.8.1 or higher
 *      X.Org 1.0 or higher (1.7 or higher is recommended)
 * For optimal functionality, we recommend the following libraries or packages:
 *      DBus 1.0 or higher
 *      NetworkManager 0.7 or higher
 *      PulseAudio
 *
 *      if (expressPanel.hasUri()) {
 *             PageScreenshot pageScreenshot = new PageScreenshot(expressPanel.getUri(), "content/images/express/screenshots/sample.png");
 *             pageScreenshot.setWidth(1024);
 *             pageScreenshot.setHeight(1024);
 *             try {
 *                 pageScreenshot.getScreenshot();
 *             } catch (IOException e) {
 *                 throw new RuntimeException(e);
 *             }
 *         }
 */
public class PageScreenshot {
    private final String uri;
    private final String fileName;
    private int width = 1024;
    private int height = 768;
    //private String binaryFilePath = "/home/agarty/blank/firefox/firefox";
    private String binaryFilePath = "firefox/firefox";
    private static int      DISPLAY_NUMBER  = 99;
    private static String   XVFB            = "/usr/bin/Xvfb";
    private static String   XVFB_COMMAND    = XVFB + " :" + DISPLAY_NUMBER;
    private static String   URL             = "http://www.google.com/";
    private static String   RESULT_FILENAME = "/tmp/screenshot.png";

    public PageScreenshot(String uri, String fileName) {
        this.uri = uri;
        this.fileName = fileName;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setBinaryFilePath(String binaryFilePath) {
        this.binaryFilePath = binaryFilePath;
    }

    public void getScreenshot() throws IOException  {
        /*FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        options.setBinary(binaryFilePath);
        options.addArguments("--width=" + width, "--height=" + height);

        WebDriver driver = new FirefoxDriver(options);
        driver.get(uri);

        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File(fileName));

        driver.quit();*/
    }
}
