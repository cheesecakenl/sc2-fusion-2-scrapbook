package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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

        txtScrapbook.setEditable(false);
        txtScrapbook.setBackground(new Color(255, 255, 204));

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
        if (txtScFusion.getText() != null && !txtScFusion.getText().isEmpty()) {
            createOutput();
        }
    }

    private void createOutput() {
        String input = shortenInput(txtScFusion.getText());

        String[] lines = input.split("\n");

        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            if (line.isEmpty()) {
                break;
            }

            String time;
            String mins;
            String gas;
            String supply;
            String description;

            try {
                time = line.substring(0, 8);
                mins = line.substring(9, 15);
                gas = line.substring(16, 21);

                // Zerg has extra fixed column for Larva
                if (line.charAt(24) == 'L' || line.charAt(25) == 'L') {
                    supply = line.substring(27, 35);
                    description = line.substring(37, line.length());
                } else {
                    supply = line.substring(22, 30);
                    description = line.substring(32, line.length());
                }
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                break;
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

        String output = sb.toString();

        if (output != null && !output.isEmpty()) {
            txtScrapbook.setText(output);
            copyToClipboard(output);
            JOptionPane.showMessageDialog(this, "Text is copied to clipboard", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
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

    private void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }
}
