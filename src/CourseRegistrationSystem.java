import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientAppointmentSystem extends JFrame {
    private JTextField tfName, tfId, tfEmail, tfDate, tfTime, tfSearch;
    private JComboBox<String> cbService;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private final String DATA_FILE = "appointments.txt";

    public ClientAppointmentSystem() {
        setTitle("🏢 Premium Client Appointment System");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel title = new JLabel("🏢 Appointments Management", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setOpaque(true);
        title.setBackground(new Color(41, 128, 185));
        title.setForeground(Color.white);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        // Sidebar Form
        JPanel sidePanel = new JPanel(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(350, 0));
        sidePanel.setBackground(new Color(236, 240, 241));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formPanel.setBackground(new Color(236, 240, 241));
        formPanel.setBorder(BorderFactory.createTitledBorder("Schedule Appointment"));

        tfName = new JTextField();
        tfId = new JTextField();
        String[] services = {"Consultation", "Support", "Maintenance", "Sales", "Other"};
        cbService = new JComboBox<>(services);
        tfEmail = new JTextField();
        tfDate = new JTextField("YYYY-MM-DD");
        tfTime = new JTextField("HH:MM");

        formPanel.add(new JLabel("Client Name:"));
        formPanel.add(tfName);
        formPanel.add(new JLabel("Client ID:"));
        formPanel.add(tfId);
        formPanel.add(new JLabel("Service Type:"));
        formPanel.add(cbService);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(tfEmail);
        formPanel.add(new JLabel("Date:"));
        formPanel.add(tfDate);
        formPanel.add(new JLabel("Time:"));
        formPanel.add(tfTime);

        JButton btnAdd = new JButton("➕ Book Appointment");
        JButton btnClear = new JButton("🧹 Clear Form");
        styleButton(btnAdd, new Color(46, 204, 113));
        styleButton(btnClear, new Color(149, 165, 166));

        formPanel.add(btnAdd);
        formPanel.add(btnClear);
        sidePanel.add(formPanel, BorderLayout.NORTH);
        add(sidePanel, BorderLayout.WEST);

        // Center Table and Search
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tfSearch = new JTextField(20);
        JButton btnSearch = new JButton("🔍 Search");
        JButton btnDelete = new JButton("🗑️ Delete Selected");
        styleButton(btnSearch, new Color(52, 152, 219));
        styleButton(btnDelete, new Color(231, 76, 60));

        topBar.add(new JLabel("Search Name/ID: "));
        topBar.add(tfSearch);
        topBar.add(btnSearch);
        topBar.add(btnDelete);
        centerPanel.add(topBar, BorderLayout.NORTH);

        String[] columns = {"Name", "ID", "Service", "Email", "Date", "Time", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        JScrollPane tablePane = new JScrollPane(table);
        centerPanel.add(tablePane, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Actions
        btnAdd.addActionListener(e -> addAppointment());
        btnClear.addActionListener(e -> clearForm());
        btnSearch.addActionListener(e -> searchAppointments());
        btnDelete.addActionListener(e -> deleteAppointment());

        loadData();
        showAllAppointments();
    }

    private void addAppointment() {
        String name = tfName.getText().trim();
        String id = tfId.getText().trim();
        String service = (String) cbService.getSelectedItem();
        String email = tfEmail.getText().trim();
        String date = tfDate.getText().trim();
        String time = tfTime.getText().trim();

        if (name.isEmpty() || id.isEmpty() || email.isEmpty() || date.equals("YYYY-MM-DD") || time.equals("HH:MM")) {
            JOptionPane.showMessageDialog(this, "Please fill in all details.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Appointment appt = new Appointment(name, id, service, email, date, time, "Confirmed");
        appointments.add(appt);
        saveData();
        showAllAppointments();
        clearForm();
    }

    private void deleteAppointment() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select an appointment to delete.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this appointment?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String id = (String) model.getValueAt(row, 1);
            appointments.removeIf(a -> a.getClientId().equals(id));
            saveData();
            showAllAppointments();
        }
    }

    private void searchAppointments() {
        String query = tfSearch.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            showAllAppointments();
            return;
        }
        List<Appointment> filtered = appointments.stream()
                .filter(a -> a.getClientName().toLowerCase().contains(query) || a.getClientId().toLowerCase().contains(query))
                .collect(Collectors.toList());
        
        updateTable(filtered);
    }

    private void showAllAppointments() {
        updateTable(appointments);
    }

    private void updateTable(List<Appointment> list) {
        model.setRowCount(0);
        for (Appointment a : list) {
            model.addRow(new Object[]{a.getClientName(), a.getClientId(), a.getServiceType(), a.getEmail(), a.getDate(), a.getTime(), a.getStatus()});
        }
    }

    private void clearForm() {
        tfName.setText("");
        tfId.setText("");
        tfEmail.setText("");
        tfDate.setText("YYYY-MM-DD");
        tfTime.setText("HH:MM");
        cbService.setSelectedIndex(0);
    }

    private void styleButton(JButton button, Color bg) {
        button.setBackground(bg);
        button.setForeground(Color.white);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void saveData() {
        try (PrintWriter out = new PrintWriter(new FileWriter(DATA_FILE))) {
            for (Appointment a : appointments) {
                out.println(a.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        File f = new File(DATA_FILE);
        if (!f.exists()) return;
        try (BufferedReader in = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = in.readLine()) != null) {
                Appointment a = Appointment.fromString(line);
                if (a != null) appointments.add(a);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientAppointmentSystem().setVisible(true));
    }
}
