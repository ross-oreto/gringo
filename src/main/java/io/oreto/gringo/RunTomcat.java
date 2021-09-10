package io.oreto.gringo;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

public class RunTomcat {
    private static final int PORT = getPort();

    private static int getPort() {
        String port = System.getenv("PORT");
        if (port != null) {
            return Integer.parseInt(port);
        }
        return 8080;
    }

    public static void main(String[] args) throws ServletException, LifecycleException {
//        String contextPath = "/gringo";
        String contextPath = "";
        String appBase = ".";
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(createTempDir());
        tomcat.setPort(PORT);
        tomcat.getHost().setAppBase(appBase);
        tomcat.addWebapp(contextPath, ".");
        tomcat.start();
        tomcat.getServer().await();
    }

    // based on AbstractEmbeddedServletContainerFactory
    private static String createTempDir() {
        try {
            File tempDir = File.createTempFile("tomcat.", "." + PORT);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();
            String path = tempDir.getAbsolutePath();
            System.out.printf("temp dir: %s%n", path);
            return path;
        } catch (IOException ex) {
            throw new RuntimeException("Unable to create tempDir. java.io.tmpdir is set to "
                    + System.getProperty("java.io.tmpdir"), ex);
        }
    }
}
