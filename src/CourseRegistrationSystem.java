import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

class CourseRegistration {
    String studentName, studentId, courseName, email;

    CourseRegistration(String studentName, String studentId, String courseName, String email) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.courseName = courseName;
        this.email = email;
    }
}

public class CourseRegistrationSystem extends JFrame {
    private JTextField tfName, tfId, tfCourse, tfEmail;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<CourseRegistration> registrations = new ArrayList<>();

    public CourseRegistrationSystem() {
        setTitle("🎓 Online Course Registration System");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("🎓 Online Course Registration", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setOpaque(true);
        title.setBackground(new Color(44, 62, 80));
        title.setForeground(Color.white);
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        add(title, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 12, 12));
        formPanel.setBorder(BorderFactory.createTitledBorder("Register a Student"));
        formPanel.setBackground(new Color(245, 245, 245));
        tfName = new JTextField();
        tfId = new JTextField();
        tfCourse = new JTextField();
        tfEmail = new JTextField();

        formPanel.add(new JLabel("Student Name:"));
        formPanel.add(tfName);
        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(tfId);
        formPanel.add(new JLabel("Course Name:"));
        formPanel.add(tfCourse);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(tfEmail);

        JButton btnRegister = new JButton("📘 Register");
        JButton btnClear = new JButton("❌ Clear");
        styleButton(btnRegister);
        styleButton(btnClear);
        formPanel.add(btnRegister);
        formPanel.add(btnClear);
        add(formPanel, BorderLayout.WEST);

        model = new DefaultTableModel(new String[]{"Student Name", "ID", "Course", "Email"}, 0);
        table = new JTable(model);
        table.setRowHeight(22);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane tablePane = new JScrollPane(table);
        add(tablePane, BorderLayout.CENTER);

        btnRegister.addActionListener(e -> registerStudent());
        btnClear.addActionListener(e -> clearForm());

        // Sample Data
        registrations.add(new CourseRegistration("Aditya", "S101", "Java Programming", "adityamahajan567@gmail.com"));
        registrations.add(new CourseRegistration("Golu", "C102", "Web Design", "bob@mail.com"));
        showAllRegistrations();
    }

    private void registerStudent() {
        String name = tfName.getText().trim();
        String id = tfId.getText().trim();
        String course = tfCourse.getText().trim();
        String email = tfEmail.getText().trim();

        if (name.isEmpty() || id.isEmpty() || course.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        registrations.add(new CourseRegistration(name, id, course, email));
        showAllRegistrations();
        clearForm();
    }

    private void showAllRegistrations() {
        model.setRowCount(0);
        for (CourseRegistration cr : registrations) {
            model.addRow(new Object[]{cr.studentName, cr.studentId, cr.courseName, cr.email});
        }
    }

    private void clearForm() {
        tfName.setText("");
        tfId.setText("");
        tfCourse.setText("");
        tfEmail.setText("");
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.white);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CourseRegistrationSystem().setVisible(true));
    }
}
