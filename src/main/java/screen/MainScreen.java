package screen;

import domain.BuildOrderRecord;
import domain.DataBuilder;
import util.InputUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
        txtScFusion = new JTextArea();
        txtScrapbook = new JTextArea();
        btnConvert = new JButton();

        GridLayout layout = new GridLayout(3, 0);
        mainPanel.setLayout(layout);

        txtScrapbook.setEditable(false);
        txtScrapbook.setBackground(new Color(255, 255, 204));

        JScrollPane scrollScFusion = new JScrollPane(txtScFusion, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane
                .HORIZONTAL_SCROLLBAR_NEVER);
        JScrollPane scrollScrapbook = new JScrollPane(txtScrapbook, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        btnConvert.setText("Convert");
        btnConvert.addActionListener(this);

        add(mainPanel);
        mainPanel.add(scrollScFusion);
        mainPanel.add(scrollScrapbook);
        mainPanel.add(btnConvert);
    }

    public void actionPerformed(ActionEvent e) {
        if (txtScFusion.getText() != null && !txtScFusion.getText().isEmpty()) {

            DataBuilder builder = new DataBuilder(txtScFusion.getText());
            List<BuildOrderRecord> records = builder.getRecords();
            String output = createOutput(records);

            if (output != null && !output.isEmpty()) {
                txtScrapbook.setText(output);
                InputUtil.copyToClipboard(output);
                JOptionPane.showMessageDialog(this, "Text is copied to clipboard", "Success", JOptionPane
                        .INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid input", "Error", JOptionPane
                        .ERROR_MESSAGE);
            }
        }
    }

    private String createOutput(List<BuildOrderRecord> records) {
        StringBuilder sb = new StringBuilder();
        for (BuildOrderRecord record : records) {
            sb.append("{supply}");
            sb.append(record.getSupply());
            sb.append(" - ");
            sb.append(record.getDescription());
            sb.append(" ");
            sb.append("(");
            sb.append(record.getTime());
            sb.append(")");
            sb.append("\n");
        }

        return sb.toString();
    }


}
