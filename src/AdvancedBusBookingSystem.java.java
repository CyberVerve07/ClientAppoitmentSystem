import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

class AdvancedBusBookingSystem extends JFrame {
    private JTabbedPane tabs;
    private JTextField loginUserField, nameField, seatField, dateField;
    private JPasswordField loginPassField;
    private JComboBox<String> busCombo, paymentCombo;
    private JTextArea viewArea;
    private String currentUser = "";
    private final int MAX_SEATS = 40;
    private final int FARE_PER_SEAT = 200;
    private Map<String, Integer> seatMap = new HashMap<>();

    public AdvancedBusBookingSystem() {
        setTitle("Advanced Bus Booking System");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabs = new JTabbedPane();

        tabs.add("Login", loginPanel());
        tabs.add("Book Ticket", bookingPanel());
        tabs.add("View Tickets", viewPanel());
        tabs.add("Cancel Ticket", cancelPanel());

        add(tabs);
        tabs.setEnabledAt(1, false);
        tabs.setEnabledAt(2, false);
        tabs.setEnabledAt(3, false);

        setVisible(true);
    }

    private JPanel loginPanel() {
        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("Login to Book", JLabel.CENTER);
        title.setBounds(200, 30, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(title);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(200, 100, 100, 25);
        panel.add(userLabel);

        loginUserField = new JTextField();
        loginUserField.setBounds(300, 100, 150, 25);
        panel.add(loginUserField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(200, 140, 100, 25);
        panel.add(passLabel);

        loginPassField = new JPasswordField();
        loginPassField.setBounds(300, 140, 150, 25);
        panel.add(loginPassField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(300, 180, 100, 30);
        loginBtn.addActionListener(e -> {
            String user = loginUserField.getText();
            String pass = new String(loginPassField.getPassword());
            if (user.equals("admin") && pass.equals("1234")) {
                currentUser = user;
                tabs.setEnabledAt(1, true);
                tabs.setEnabledAt(2, true);
                tabs.setEnabledAt(3, true);
                tabs.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }
        });
        panel.add(loginBtn);

        return panel;
    }

    private JPanel bookingPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 40, 100, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 40, 200, 25);
        panel.add(nameField);

        JLabel busLabel = new JLabel("Bus No:");
        busLabel.setBounds(100, 80, 100, 25);
        panel.add(busLabel);

        busCombo = new JComboBox<>(new String[]{"BUS101", "BUS202", "BUS303"});
        busCombo.setBounds(200, 80, 200, 25);
        panel.add(busCombo);

        JLabel seatLabel = new JLabel("Seats:");
        seatLabel.setBounds(100, 120, 100, 25);
        panel.add(seatLabel);

        seatField = new JTextField();
        seatField.setBounds(200, 120, 200, 25);
        panel.add(seatField);

        JLabel dateLabel = new JLabel("Travel Date (dd-mm-yyyy):");
        dateLabel.setBounds(100, 160, 200, 25);
        panel.add(dateLabel);

        dateField = new JTextField();
        dateField.setBounds(300, 160, 100, 25);
        panel.add(dateField);

        JLabel payLabel = new JLabel("Payment Method:");
        payLabel.setBounds(100, 200, 150, 25);
        panel.add(payLabel);

        paymentCombo = new JComboBox<>(new String[]{"Cash", "UPI", "Card"});
        paymentCombo.setBounds(250, 200, 150, 25);
        panel.add(paymentCombo);

        JButton bookBtn = new JButton("Book Ticket");
        bookBtn.setBounds(200, 250, 150, 30);
        bookBtn.addActionListener(e -> handleBooking());
        panel.add(bookBtn);

        return panel;
    }

    private JPanel viewPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        viewArea = new JTextArea();
        viewArea.setEditable(false);
        panel.add(new JScrollPane(viewArea), BorderLayout.CENTER);

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> loadTickets());
        panel.add(refresh, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel cancelPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label = new JLabel("Enter Ticket ID to cancel:");
        label.setBounds(150, 100, 200, 25);
        panel.add(label);

        JTextField cancelField = new JTextField();
        cancelField.setBounds(150, 130, 200, 25);
        panel.add(cancelField);

        JButton cancelBtn = new JButton("Cancel Ticket");
        cancelBtn.setBounds(180, 170, 150, 30);
        cancelBtn.addActionListener(e -> {
            String id = cancelField.getText();
            File file = new File("ticket_" + id + ".txt");
            if (file.exists()) {
                file.delete();
                JOptionPane.showMessageDialog(this, "Ticket " + id + " cancelled.");
                loadTickets();
            } else {
                JOptionPane.showMessageDialog(this, "Ticket ID not found.");
            }
        });
        panel.add(cancelBtn);

        return panel;
    }

    private void handleBooking() {
        String name = nameField.getText();
        String bus = (String) busCombo.getSelectedItem();
        String seatStr = seatField.getText();
        String date = dateField.getText();
        String payment = (String) paymentCombo.getSelectedItem();

        if (name.isEmpty() || seatStr.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        int seats;
        try {
            seats = Integer.parseInt(seatStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid seat number.");
            return;
        }

        int booked = seatMap.getOrDefault(bus, 0);
        if (booked + seats > MAX_SEATS) {
            JOptionPane.showMessageDialog(this, "Not enough seats on " + bus);
            return;
        }

        int fare = seats * FARE_PER_SEAT;
        String ticketID = "TICK" + new Random().nextInt(9999);

        String ticket = "Ticket ID: " + ticketID +
                "\nName: " + name +
                "\nBus: " + bus +
                "\nSeats: " + seats +
                "\nDate: " + date +
                "\nPayment: " + payment +
                "\nFare: ₹" + fare;

        JOptionPane.showMessageDialog(this, ticket, "Ticket Preview", JOptionPane.INFORMATION_MESSAGE);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ticket_" + ticketID + ".txt"))) {
            writer.write(ticket);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving ticket!");
        }

        seatMap.put(bus, booked + seats);
        clearFields();
        loadTickets();
    }

    private void clearFields() {
        nameField.setText("");
        seatField.setText("");
        dateField.setText("");
    }

    private void loadTickets() {
        viewArea.setText("");
        File dir = new File(".");
        File[] files = dir.listFiles((d, name) -> name.startsWith("ticket_") && name.endsWith(".txt"));

        if (files != null) {
            for (File f : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        viewArea.append(line + "\n");
                    }
                    viewArea.append("\n----------------------------\n");
                } catch (IOException e) {
                    viewArea.append("Error reading " + f.getName() + "\n");
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AdvancedBusBookingSystem::new);
    }
}
