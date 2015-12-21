package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame implements ActionListener {

    private JPanel mainPanel;
    private JTextArea txtScFusion;
    private JTextArea txtScrapbook;
    private JButton btnConvert;

    public MainScreen() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createGUI();
    }

    private void createGUI() {
        mainPanel = new JPanel();
        txtScFusion  = new JTextArea();
        txtScrapbook = new JTextArea();
        btnConvert = new JButton();

        GridLayout layout = new GridLayout(3,0);
        mainPanel.setLayout(layout);

        JScrollPane scrollScFusion = new JScrollPane (txtScFusion, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollScrapbook = new JScrollPane (txtScrapbook, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnConvert.setText("Convert");
        btnConvert.addActionListener(this);

        add(mainPanel);
        mainPanel.add(scrollScFusion);
        mainPanel.add(scrollScrapbook);
        mainPanel.add(btnConvert);
    }

    public void actionPerformed(ActionEvent e) {
        createOutput();
    }

    private void createOutput() {
        String input = shortenInput(txtScFusion.getText());

        String[] lines = input.split("\n");

        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            if (line.isEmpty()) {
                break;
            }

            String time = line.substring(0, 8);
            String mins = line.substring(9, 15);
            String gas = line.substring(16, 21);

            String supply;
            String description;

            // zerg
            if (line.charAt(24) == 'L' || line.charAt(25) == 'L') {
                supply = line.substring(27, 35);
                description = line.substring(37, line.length());
            } else {
                supply = line.substring(22, 30);
                description = line.substring(32, line.length());
            }


            time = time.replaceAll(" ", "");
            int dot = time.indexOf('.');
            time = time.substring(0, dot);

            mins = mins.replaceAll(" ", "");
            gas = gas.replaceAll(" ", "");

            supply = supply.replaceAll(" ", "");
            supply = supply.replaceAll("S", "");

            sb.append("{supply}");
            sb.append(supply);
            sb.append(" -");
            sb.append(description);
            sb.append(" ");
            sb.append("(");
            sb.append(time);
            sb.append(")");
            sb.append("\n");
        }

        txtScrapbook.setText(sb.toString());
    }

    private String shortenInput(String input) {
        input = input.replaceAll("Build ", "");
        input = input.replaceAll("Morph ", "");
        input = input.replaceAll("From Barracks With Reactor", "from reactor");
        input = input.replaceAll("From Barracks With Tech Lab", "from tech lab");
        input = input.replaceAll("From Naked Barracks", "from barracks");
        input = input.replaceAll("Move Chrono Boost From Nexus To", "Chrono boost");

        return input;
    }
}
