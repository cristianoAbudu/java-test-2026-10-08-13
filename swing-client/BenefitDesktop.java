import javax.swing.*;
import java.awt.*;

public class BenefitDesktop {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Benefit Request Viewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 160);
            frame.setLayout(new BorderLayout(6, 6));
            JTextField id = new JTextField();
            JButton button = new JButton("Show request ID");
            JLabel result = new JLabel("Enter a request ID", SwingConstants.CENTER);
            button.addActionListener(e -> result.setText("Selected request ID: " + id.getText().trim()));
            JPanel input = new JPanel(new BorderLayout(5, 5));
            input.add(new JLabel("Request ID:"), BorderLayout.WEST);
            input.add(id, BorderLayout.CENTER);
            frame.add(new JLabel("Benefit Request Quick View", SwingConstants.CENTER), BorderLayout.NORTH);
            frame.add(input, BorderLayout.CENTER);
            JPanel bottom = new JPanel(new GridLayout(2, 1));
            bottom.add(button);
            bottom.add(result);
            frame.add(bottom, BorderLayout.SOUTH);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}