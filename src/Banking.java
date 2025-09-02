import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Banking implements ActionListener {

    private JFrame mainFrame;
    private JTextField inputField;
    private JLabel balanceLabel;
    private int balance = 0;

    public Banking() {
        mainFrame = new JFrame("🏦 Simple Bank Application");
        mainFrame.setSize(400, 200);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null); // Center the window

        inputField = new JTextField(10);
        balanceLabel = new JLabel("💰 Current Balance: ₹" + balance);
        JButton depositButton = new JButton("➕ Deposit");
        JButton withdrawButton = new JButton("➖ Withdraw");

        // Add components to frame
        mainFrame.add(new JLabel("Enter amount:"));
        mainFrame.add(inputField);
        mainFrame.add(depositButton);
        mainFrame.add(withdrawButton);
        mainFrame.add(balanceLabel);

        // Action Listeners
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputText = inputField.getText().trim();

        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter an amount.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int amount;
        try {
            amount = Integer.parseInt(inputText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainFrame, "Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (amount <= 0) {
            JOptionPane.showMessageDialog(mainFrame, "Amount must be greater than 0.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (e.getActionCommand().contains("Deposit")) {
            balance += amount;
        } else {
            if (amount > balance) {
                JOptionPane.showMessageDialog(mainFrame, "Insufficient balance!", "Transaction Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }
            balance -= amount;
        }

        balanceLabel.setText("💰 Current Balance: ₹" + balance);
        inputField.setText("");
    }

    public static void main(String[] args) {
        new Banking();
    }
}
