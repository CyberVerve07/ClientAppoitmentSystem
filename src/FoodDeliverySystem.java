import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class FoodOrder {
    String orderId, customerName, foodItem, quantity, address, deliveryBoy;

    FoodOrder(String orderId, String customerName, String foodItem, String quantity, String address, String deliveryBoy) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.foodItem = foodItem;
        this.quantity = quantity;
        this.address = address;
        this.deliveryBoy = deliveryBoy;
    }
}

public class FoodDeliverySystem extends JFrame {

    private JTextField tfOrderId, tfCustomer, tfFoodItem, tfQuantity, tfAddress, tfDeliveryBoy, tfSearch;
    private DefaultTableModel model;
    private JTable table;
    private ArrayList<FoodOrder> orderList = new ArrayList<>();

    public FoodDeliverySystem() {
        setTitle("🍽️ Food Delivery Management System");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("🍽️ Food Delivery Management System", JLabel.CENTER);
        heading.setFont(new Font("Verdana", Font.BOLD, 24));
        heading.setOpaque(true);
        heading.setBackground(new Color(0, 153, 76));
        heading.setForeground(Color.white);
        heading.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(heading, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(7, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Enter Order Details"));
        form.setBackground(new Color(240, 255, 250));
        tfOrderId = new JTextField();
        tfCustomer = new JTextField();
        tfFoodItem = new JTextField();
        tfQuantity = new JTextField();
        tfAddress = new JTextField();
        tfDeliveryBoy = new JTextField();

        form.add(new JLabel("Order ID:"));
        form.add(tfOrderId);
        form.add(new JLabel("Customer Name:"));
        form.add(tfCustomer);
        form.add(new JLabel("Food Item:"));
        form.add(tfFoodItem);
        form.add(new JLabel("Quantity:"));
        form.add(tfQuantity);
        form.add(new JLabel("Address:"));
        form.add(tfAddress);
        form.add(new JLabel("Delivery Person:"));
        form.add(tfDeliveryBoy);

        JButton btnAdd = new JButton("➕ Add Order");
        JButton btnSearch = new JButton("🔍 Search Customer");
        styleButton(btnAdd);
        styleButton(btnSearch);
        form.add(btnAdd);
        form.add(btnSearch);
        add(form, BorderLayout.WEST);

        model = new DefaultTableModel(new String[]{"Order ID", "Customer", "Food Item", "Qty", "Address", "Delivery Boy"}, 0);
        table = new JTable(model);
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(220, 220, 220));
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.setBackground(new Color(240, 255, 250));
        tfSearch = new JTextField(20);
        JButton btnDelete = new JButton("🗑️ Delete Selected");
        JButton btnShowAll = new JButton("📄 Show All");
        styleButton(btnDelete);
        styleButton(btnShowAll);
        bottom.add(new JLabel("Search Customer:"));
        bottom.add(tfSearch);
        bottom.add(btnShowAll);
        bottom.add(btnDelete);
        add(bottom, BorderLayout.SOUTH);

        // Action listeners
        btnAdd.addActionListener(e -> addOrder());
        btnShowAll.addActionListener(e -> showAllOrders());
        btnSearch.addActionListener(e -> searchOrders());
        btnDelete.addActionListener(e -> deleteSelectedOrder());

        // Sample data
        orderList.add(new FoodOrder("F001", "John", "Burger", "2", "123 Main St", "Alex"));
        orderList.add(new FoodOrder("F002", "Emily", "Pizza", "1", "456 Elm St", "Brian"));
        showAllOrders();
    }

    private void addOrder() {
        String id = tfOrderId.getText().trim();
        String cust = tfCustomer.getText().trim();
        String food = tfFoodItem.getText().trim();
        String qty = tfQuantity.getText().trim();
        String addr = tfAddress.getText().trim();
        String boy = tfDeliveryBoy.getText().trim();

        if (id.isEmpty() || cust.isEmpty() || food.isEmpty() || qty.isEmpty() || addr.isEmpty() || boy.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Optional: Check for duplicate Order ID
        for (FoodOrder o : orderList) {
            if (o.orderId.equals(id)) {
                JOptionPane.showMessageDialog(this, "Order ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        orderList.add(new FoodOrder(id, cust, food, qty, addr, boy));
        showAllOrders();
        tfOrderId.setText("");
        tfCustomer.setText("");
        tfFoodItem.setText("");
        tfQuantity.setText("");
        tfAddress.setText("");
        tfDeliveryBoy.setText("");
    }

    private void showAllOrders() {
        model.setRowCount(0);
        for (FoodOrder o : orderList) {
            model.addRow(new Object[]{o.orderId, o.customerName, o.foodItem, o.quantity, o.address, o.deliveryBoy});
        }
    }

    private void searchOrders() {
        String name = tfSearch.getText().trim().toLowerCase();
        model.setRowCount(0);
        for (FoodOrder o : orderList) {
            if (o.customerName.toLowerCase().contains(name)) {
                model.addRow(new Object[]{o.orderId, o.customerName, o.foodItem, o.quantity, o.address, o.deliveryBoy});
            }
        }
        tfSearch.setText("");
    }

    private void deleteSelectedOrder() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
            return;
        }
        String id = model.getValueAt(row, 0).toString();
        orderList.removeIf(o -> o.orderId.equals(id));
        showAllOrders();
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(0, 204, 153));
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 13));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FoodDeliverySystem().setVisible(true));
    }
}
