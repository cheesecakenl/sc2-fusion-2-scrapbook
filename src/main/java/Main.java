import screen.MainScreen;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        InputStream is = Main.class.getResourceAsStream("app.properties");
        Properties p = new Properties();
        try {
            p.load(is);
        } catch (IOException e) {
        }

        String title = "Default title";
        String version = "Default version";

        if (p != null && p.getProperty("app-title") != null) {
            title = p.getProperty("app-title");
            version = p.getProperty("app-version");
        }

        MainScreen ex = new MainScreen();

        ex.setTitle(title + " - " + "version " + version);
        ex.setSize(1024, 768);
        ex.setLocationRelativeTo(null);
        ex.setVisible(true);
    }
}
