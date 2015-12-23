import screen.MainScreen;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class Fusion2Scrapbook {

    public static void main(String[] args) {
        InputStream is = Fusion2Scrapbook.class.getResourceAsStream("app.properties");
        Properties p = new Properties();
        try {
            p.load(is);
        } catch (IOException e) {
        }

        MainScreen ex = new MainScreen();

        URL iconURL = Fusion2Scrapbook.class.getResource("beaker-8x.png");
        ImageIcon icon = new ImageIcon(iconURL);
        ex.setIconImage(icon.getImage());

        ex.setTitle(p.getProperty("app-title") + " - " + "version " + p.getProperty("app-version"));
        ex.setSize(1024, 768);
        ex.setLocationRelativeTo(null);
        ex.setVisible(true);
    }
}
