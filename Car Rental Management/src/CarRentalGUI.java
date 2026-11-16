import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarRentalGUI extends JFrame {

    // تخزين البيانات باستخدام نفس الكلاسات الخاصة بك
    private final List<Car> cars = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();

    // ألوان التصميم العصري (Modern Dark & Slate Theme)
    private final Color BG_DARK = new Color(24, 28, 36);
    private final Color CARD_BG = new Color(34, 40, 52);
    private final Color ACCENT_BLUE = new Color(56, 139, 253);
    private final Color ACCENT_GREEN = new Color(46, 160, 67);
    private final Color TEXT_LIGHT = new Color(240, 246, 252);
    private final Color TEXT_MUTED = new Color(139, 148, 158);

    // موديلات الجداول للتحديث التلقائي
    private DefaultTableModel carsTableModel;
    private DefaultTableModel customersTableModel;
    private JLabel statsLabel;

    public CarRentalGUI() {
        setTitle("AMS Car Rental Management System");
        setSize(1050, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG_DARK);
        setLayout(new BorderLayout(10, 10));

        // إضافة بيانات تجريبية جاهزة عشان التصوير والعرض
        seedSampleData();

        // الهيدر العلوي
        add(createHeader(), BorderLayout.NORTH);

        // التبويبات الرئيسية (Tabs)
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabbedPane.setBackground(CARD_BG);
        tabbedPane.setForeground(Color.BLACK);

        tabbedPane.addTab("  🚗 Fleet & Cars  ", createCarsPanel());
        tabbedPane.addTab("  👤 Customers  ", createCustomersPanel());
        tabbedPane.addTab("  🔑 Rent & Return  ", createRentReturnPanel());

        add(tabbedPane, BorderLayout.CENTER);

        refreshAllTables();
    }

    private void seedSampleData() {
        cars.add(new Car(101, "Toyota", "Corolla", 2023, 50.0));
        cars.add(new Car(102, "Hyundai", "Elantra", 2024, 55.0));
        cars.add(new LuxuryCar(201, "Mercedes", "S-Class", 2024, 200.0, 40.0f));
        cars.add(new LuxuryCar(202, "BMW", "X6", 2023, 180.0, 35.0f));

        customers.add(new Customer(1, "Ahmed Mohamed", "01012345678"));
        customers.add(new Customer(2, "Sara Mahmoud", "01198765432"));
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(CARD_BG);
        header.setBorder(new EmptyBorder(15, 25, 15, 25));

        JLabel title = new JLabel("AMS CAR RENTAL SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(TEXT_LIGHT);

        statsLabel = new JLabel();
        statsLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        statsLabel.setForeground(ACCENT_BLUE);

        header.add(title, BorderLayout.WEST);
        header.add(statsLabel, BorderLayout.EAST);
        return header;
    }

    // ==================== 1. تبويب السيارات ====================
    private JPanel createCarsPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(BG_DARK);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        // فورم إضافة سيارة (يسار)
        JPanel formPanel = new JPanel(new GridLayout(0, 1, 8, 8));
        formPanel.setBackground(CARD_BG);
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        formPanel.setPreferredSize(new Dimension(290, 0));

        JLabel formTitle = new JLabel("Add New Car");
        formTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        formTitle.setForeground(TEXT_LIGHT);

        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Regular Car", "Luxury Car"});
        JTextField idField = createStyledTextField();
        JTextField brandField = createStyledTextField();
        JTextField modelField = createStyledTextField();
        JTextField yearField = createStyledTextField();
        JTextField priceField = createStyledTextField();
        JTextField insuranceField = createStyledTextField();
        insuranceField.setEnabled(false);

        typeBox.addActionListener(e -> insuranceField.setEnabled(typeBox.getSelectedIndex() == 1));

        JButton addBtn = createStyledButton("Add Car to Fleet", ACCENT_GREEN);
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                for (Car c : cars) {
                    if (c.getId() == id) {
                        showError("Car ID already exists!");
                        return;
                    }
                }
                String brand = brandField.getText().trim();
                String model = modelField.getText().trim();
                int year = Integer.parseInt(yearField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());

                if (typeBox.getSelectedIndex() == 0) {
                    cars.add(new Car(id, brand, model, year, price));
                } else {
                    float insurance = Float.parseFloat(insuranceField.getText().trim());
                    cars.add(new LuxuryCar(id, brand, model, year, price, insurance));
                }

                idField.setText(""); brandField.setText(""); modelField.setText("");
                yearField.setText(""); priceField.setText(""); insuranceField.setText("");
                refreshAllTables();
                JOptionPane.showMessageDialog(this, "Car Added Successfully!");
            } catch (Exception ex) {
                showError("Please enter valid numeric values!");
            }
        });

        formPanel.add(formTitle);
        formPanel.add(createLabel("Car Type:")); formPanel.add(typeBox);
        formPanel.add(createLabel("Car ID:")); formPanel.add(idField);
        formPanel.add(createLabel("Brand:")); formPanel.add(brandField);
        formPanel.add(createLabel("Model:")); formPanel.add(modelField);
        formPanel.add(createLabel("Year:")); formPanel.add(yearField);
        formPanel.add(createLabel("Price / Day ($):")); formPanel.add(priceField);
        formPanel.add(createLabel("Insurance Fee (Luxury only):")); formPanel.add(insuranceField);
        formPanel.add(new JLabel(""));
        formPanel.add(addBtn);

        // شريط البحث والفلترة + الجدول (يمين)
        JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
        rightPanel.setBackground(BG_DARK);

        JPanel filterBar = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        filterBar.setBackground(CARD_BG);
        JTextField searchField = createStyledTextField();
        searchField.setPreferredSize(new Dimension(180, 30));
        JButton searchBtn = createStyledButton("Search (ID/Brand)", ACCENT_BLUE);
        JButton availableBtn = createStyledButton("Available Only", new Color(130, 80, 223));
        JButton showAllBtn = createStyledButton("Show All", new Color(90, 98, 106));

        filterBar.add(createLabel("Search:"));
        filterBar.add(searchField);
        filterBar.add(searchBtn);
        filterBar.add(availableBtn);
        filterBar.add(showAllBtn);

        String[] columns = {"ID", "Type", "Brand", "Model", "Year", "Price/Day", "Insurance", "Status"};
        carsTableModel = new DefaultTableModel(columns, 0);
        JTable carsTable = styleTable(new JTable(carsTableModel));

        searchBtn.addActionListener(e -> {
            String q = searchField.getText().trim().toLowerCase();
            carsTableModel.setRowCount(0);
            for (Car c : cars) {
                if (String.valueOf(c.getId()).equals(q) || c.getBrand().toLowerCase().contains(q)) {
                    addCarRow(c);
                }
            }
        });

        availableBtn.addActionListener(e -> {
            carsTableModel.setRowCount(0);
            for (Car c : cars) {
                if (c.isAvailability()) addCarRow(c);
            }
        });

        showAllBtn.addActionListener(e -> refreshAllTables());

        rightPanel.add(filterBar, BorderLayout.NORTH);
        rightPanel.add(new JScrollPane(carsTable), BorderLayout.CENTER);

        panel.add(formPanel, BorderLayout.WEST);
        panel.add(rightPanel, BorderLayout.CENTER);
        return panel;
    }

    // ==================== 2. تبويب العملاء ====================
    private JPanel createCustomersPanel() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(BG_DARK);
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel formPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        formPanel.setBackground(CARD_BG);
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        formPanel.setPreferredSize(new Dimension(290, 0));

        JLabel title = new JLabel("Register Customer");
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(TEXT_LIGHT);

        JTextField idField = createStyledTextField();
        JTextField nameField = createStyledTextField();
        JTextField phoneField = createStyledTextField();
        JButton addBtn = createStyledButton("Add Customer", ACCENT_GREEN);

        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                for (Customer c : customers) {
                    if (c.getId() == id) {
                        showError("Customer ID already exists!");
                        return;
                    }
                }
                customers.add(new Customer(id, nameField.getText().trim(), phoneField.getText().trim()));
                idField.setText(""); nameField.setText(""); phoneField.setText("");
                refreshAllTables();
                JOptionPane.showMessageDialog(this, "Customer Registered Successfully!");
            } catch (Exception ex) {
                showError("Invalid Customer ID!");
            }
        });

        formPanel.add(title);
        formPanel.add(createLabel("Customer ID:")); formPanel.add(idField);
        formPanel.add(createLabel("Full Name:")); formPanel.add(nameField);
        formPanel.add(createLabel("Phone Number:")); formPanel.add(phoneField);
        formPanel.add(new JLabel(""));
        formPanel.add(addBtn);
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        String[] cols = {"Customer ID", "Name", "Phone", "Rented Car ID", "Total Paid ($)"};
        customersTableModel = new DefaultTableModel(cols, 0);
        JTable table = styleTable(new JTable(customersTableModel));

        panel.add(formPanel, BorderLayout.WEST);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    // ==================== 3. تبويب التأجير والإرجاع ====================
    private JPanel createRentReturnPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 20));
        mainPanel.setBackground(BG_DARK);
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        // كارت التأجير (Rent)
        JPanel rentCard = new JPanel(new GridLayout(0, 1, 10, 10));
        rentCard.setBackground(CARD_BG);
        rentCard.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel rentTitle = new JLabel("🔑 Rent a Car");
        rentTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        rentTitle.setForeground(TEXT_LIGHT);

        JTextField custIdField = createStyledTextField();
        JTextField carIdField = createStyledTextField();
        JTextField daysField = createStyledTextField();
        JTextArea receiptArea = new JTextArea(5, 20);
        receiptArea.setEditable(false);
        receiptArea.setBackground(BG_DARK);
        receiptArea.setForeground(ACCENT_GREEN);
        receiptArea.setFont(new Font("Consolas", Font.BOLD, 13));
        receiptArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton rentBtn = createStyledButton("Confirm Rental & Generate Invoice", ACCENT_BLUE);
        rentBtn.addActionListener(e -> {
            try {
                int custId = Integer.parseInt(custIdField.getText().trim());
                int carId = Integer.parseInt(carIdField.getText().trim());
                int days = Integer.parseInt(daysField.getText().trim());

                Customer selectedCust = customers.stream()
                        .filter(c -> c.getId() == custId && c.getRentedCarId() == -1)
                        .findFirst().orElse(null);

                if (selectedCust == null) {
                    showError("Customer not found or already renting a car!");
                    return;
                }

                Car selectedCar = cars.stream()
                        .filter(c -> c.getId() == carId && c.isAvailability())
                        .findFirst().orElse(null);

                if (selectedCar == null) {
                    showError("Car not found or currently unavailable!");
                    return;
                }

                double finalCost;
                if (selectedCar instanceof LuxuryCar lux) {
                    if (days < lux.getMinimumNumber()) {
                        showError("Luxury cars require at least " + lux.getMinimumNumber() + " days!");
                        return;
                    }
                    finalCost = ((lux.getPrice() * days) + lux.getInsuranceFee()) * (1 + lux.getTaxRate());
                } else {
                    if (days <= 0) {
                        showError("Days must be greater than 0!");
                        return;
                    }
                    finalCost = (selectedCar.getPrice() * days) * (1 + selectedCar.getTaxRate());
                }

                selectedCust.setRentedCarId(selectedCar.getId());
                selectedCust.setTotalPaid(finalCost);
                selectedCar.setAvailability(false);

                receiptArea.setText(String.format(
                        "=== RENTAL INVOICE ===\nCustomer: %s\nCar: %s %s (ID: %d)\nDays: %d\nTotal (incl. 14%% Tax): $%.2f",
                        selectedCust.getName(), selectedCar.getBrand(), selectedCar.getModel(),
                        selectedCar.getId(), days, finalCost
                ));

                refreshAllTables();
            } catch (Exception ex) {
                showError("Please enter valid numbers!");
            }
        });

        rentCard.add(rentTitle);
        rentCard.add(createLabel("Customer ID:")); rentCard.add(custIdField);
        rentCard.add(createLabel("Car ID:")); rentCard.add(carIdField);
        rentCard.add(createLabel("Number of Days:")); rentCard.add(daysField);
        rentCard.add(rentBtn);
        rentCard.add(new JScrollPane(receiptArea));

        // كارت الإرجاع (Return)
        JPanel returnCard = new JPanel(new BorderLayout(15, 15));
        returnCard.setBackground(CARD_BG);
        returnCard.setBorder(new EmptyBorder(25, 25, 25, 25));

        JPanel topReturn = new JPanel(new GridLayout(0, 1, 12, 12));
        topReturn.setBackground(CARD_BG);

        JLabel returnTitle = new JLabel("↩️ Return a Rented Car");
        returnTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        returnTitle.setForeground(TEXT_LIGHT);

        JTextField returnCustIdField = createStyledTextField();
        JButton returnBtn = createStyledButton("Process Car Return", new Color(218, 54, 51));

        returnBtn.addActionListener(e -> {
            try {
                int custId = Integer.parseInt(returnCustIdField.getText().trim());
                Customer cust = customers.stream().filter(c -> c.getId() == custId).findFirst().orElse(null);

                if (cust == null || cust.getRentedCarId() == -1) {
                    showError("Customer not found or has no active rental!");
                    return;
                }

                int rentedId = cust.getRentedCarId();
                for (Car car : cars) {
                    if (car.getId() == rentedId) {
                        car.setAvailability(true);
                        break;
                    }
                }
                cust.setRentedCarId(-1);
                cust.setCount(1);
                returnCustIdField.setText("");
                refreshAllTables();
                JOptionPane.showMessageDialog(this, "Car (ID: " + rentedId + ") Returned Successfully!");
            } catch (Exception ex) {
                showError("Enter a valid Customer ID!");
            }
        });

        topReturn.add(returnTitle);
        topReturn.add(createLabel("Enter Customer ID to Return Car:"));
        topReturn.add(returnCustIdField);
        topReturn.add(returnBtn);

        returnCard.add(topReturn, BorderLayout.NORTH);

        mainPanel.add(rentCard);
        mainPanel.add(returnCard);
        return mainPanel;
    }

    // ==================== أدوات مساعدة للتصميم ====================
    private void refreshAllTables() {
        carsTableModel.setRowCount(0);
        long availableCount = 0;
        for (Car c : cars) {
            addCarRow(c);
            if (c.isAvailability()) availableCount++;
        }

        customersTableModel.setRowCount(0);
        for (Customer cust : customers) {
            customersTableModel.addRow(new Object[]{
                    cust.getId(),
                    cust.getName(),
                    cust.getPhone(),
                    cust.getRentedCarId() == -1 ? "None" : cust.getRentedCarId(),
                    String.format("$%.2f", cust.getTotalPaid())
            });
        }

        statsLabel.setText(String.format("Total Cars: %d  |  Available: %d  |  Customers: %d",
                cars.size(), availableCount, customers.size()));
    }

    private void addCarRow(Car c) {
        String type = (c instanceof LuxuryCar) ? "Luxury" : "Regular";
        String ins = (c instanceof LuxuryCar lux) ? "$" + lux.getInsuranceFee() : "-";
        carsTableModel.addRow(new Object[]{
                c.getId(), type, c.getBrand(), c.getModel(), c.getYear(),
                "$" + c.getPrice(), ins, c.isAvailability() ? "✅ Available" : "🔴 Rented"
        });
    }

    private JLabel createLabel(String text) {
        JLabel l = new JLabel(text);
        l.setForeground(TEXT_MUTED);
        l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        return l;
    }

    private JTextField createStyledTextField() {
        JTextField tf = new JTextField();
        tf.setBackground(BG_DARK);
        tf.setForeground(TEXT_LIGHT);
        tf.setCaretColor(TEXT_LIGHT);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(65, 75, 92)),
                new EmptyBorder(5, 8, 5, 8)));
        return tf;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JTable styleTable(JTable table) {
        table.setBackground(CARD_BG);
        table.setForeground(TEXT_LIGHT);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.getTableHeader().setBackground(BG_DARK);
        table.getTableHeader().setForeground(ACCENT_BLUE);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
        return table;
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarRentalGUI().setVisible(true));
    }
}