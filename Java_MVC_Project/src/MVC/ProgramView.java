package MVC;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;



public class ProgramView extends JFrame implements IView, ActionListener {
    private JCheckBox showPassword;
    private JPasswordField passwordField;
    private JLabel itemName, chooseCat,chooseCat2, itemCost, userNameLabel, passwordLabel;
    private JFrame frame;
    private JTextField searchTF,itemNameTF, costTF, tfMessage, addCategoryTextField, dateTF, userNameTextField;
    private JComboBox<String> categoryList,categoryList2;
    private JButton btSearchCheck,btShowItems,btGetItems,btAddItem,btAddItem2, btDateCheck, btAddCategory,LoginSellerButton,LoginCustomerButton,signUpButton, resetButton;
    private JTextArea ta;
    private JPanel panelWest, panelNorth, panelCenter;
    private IController vm;
    private Font font1;
    private String userName, msg;
    private JTable table;
    private DefaultTableModel data;
    ImageLabel image;
    Container container = this.getContentPane();
    static int count=0;



    /**
     * initializes all the objects of the window
     */
    public ProgramView() {
        userNameLabel = new JLabel("USERNAME");
        passwordLabel = new JLabel("PASSWORD");
        userNameTextField = new JTextField();
        signUpButton = new JButton("SIGN UP");
        passwordField = new JPasswordField();
        showPassword = new JCheckBox("Show Password");
        LoginSellerButton = new JButton("Login Seller");
        LoginCustomerButton = new JButton("Login Customer");
        resetButton = new JButton("RESET");
        image = new ImageLabel(new ImageIcon("C:\\Users\\Bar\\Downloads\\Housewares Store.png"));
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }

    /**
     * sets the layout to null
     */
    public void setLayoutManager() {
        container.setLayout(null);
    }

    /**
     * sets the size of the objects
     */
    public void setLocationAndSize() {
        userNameLabel.setBounds(107, 150, 100, 30);
        passwordLabel.setBounds(107, 220, 100, 30);
        userNameTextField.setBounds(197, 150, 180, 30);
        passwordField.setBounds(197, 220, 180, 30);
        showPassword.setBounds(197, 250, 150, 30);
        LoginCustomerButton.setBounds(20, 300, 130, 30);
        LoginSellerButton.setBounds(170, 300, 130, 30);
        signUpButton.setBounds(440, 300, 100, 30);
        resetButton.setBounds(320, 300, 100, 30);
        image.setBounds(140, -426,1000,1000);
    }

    /**
     * adds the buttons and text fields to the login window
     *
     */
    public void addComponentsToContainer() {
        container.add(userNameLabel);
        container.add(passwordLabel);
        container.add(userNameTextField);
        container.add(signUpButton);
        container.add(passwordField);
        container.add(showPassword);
        container.add(LoginCustomerButton);
        container.add(LoginSellerButton);
        container.add(resetButton);
        container.add(image);
    }

    /**
     * sets action listners to the login window buttons
     */
    public void addActionEvent() {
        LoginSellerButton.addActionListener(this);
        LoginCustomerButton.addActionListener(this);
        resetButton.addActionListener(this);
        signUpButton.addActionListener(this);
        showPassword.addActionListener(this);
    }

    /**
     * sets action listeners for all the login buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LoginSellerButton) { // login button pressed
            userName = userNameTextField.getText();
            System.out.println("Login---"+ userName);
            Item item = new Item(userNameTextField.getText() + "," + passwordField.getText() + " ");
            try {
                boolean successful = vm.checkUserPass(item); //true if username and password are matching in DB
                if(successful) {
                    JOptionPane.showMessageDialog(this, "Welcome Back!");
                    System.out.println("Logged In!");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dispose();
                            init(userName);
                            start(false);
                        }
                    });

                }else{
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }
            } catch (eCommerceException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                ex.printStackTrace();
            }

        }
        if (e.getSource() == resetButton) {
            userNameTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }

        }
        if (e.getSource() == signUpButton){ // signup button pressed
            Item item = new Item("'" + userNameTextField.getText() + "', '" + passwordField.getText() + "'");
            this.userName = userNameTextField.getText();
            try {
                boolean successful = vm.addUser(item); // true if username doesnt exist in DB
                if(successful) {
                    System.out.println("UserName is available!");
                    JOptionPane.showMessageDialog(this, "Welcome!");

                    /** not using the function - > stops user from auto login after signup */
                    /*SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dispose();
                            init(userName);
                            start();
                        }
                    })*/;

                }
            } catch (eCommerceException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == LoginCustomerButton) { // login button pressed
            userName = userNameTextField.getText();
            System.out.println("Login---"+ userName);
            Item item = new Item(userNameTextField.getText() + "," + passwordField.getText() + " ");
            try {
                boolean successful = vm.checkUserPass(item); //true if username and password are matching in DB
                if(successful) {
                    JOptionPane.showMessageDialog(this, "Welcome Back!");
                    System.out.println("Logged In!");
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dispose();
                            initCustomer(userName);
                            start(true);
                        }
                    });

                }else{
                    JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                }
            } catch (eCommerceException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
                ex.printStackTrace();
            }

        }
    }

    /**
     * initializes all the objects in the main program window
     */
    public void init(String userName) {
        this.userName = userName;
        itemName = new JLabel("  item name:");
        dateTF =  new HintTextField("'yyyy-mm-dd' AND 'yyyy-mm-dd'");
        btDateCheck = new JButton("show dates between:");
        chooseCat = new JLabel("  choose category:");
        chooseCat2 = new JLabel("  choose ItemID:");
        /*chooseID = new JLabel("  choose ID:");*/
        /*IDList = new JComboBox<>();*/
        itemCost = new JLabel("  item cost:");
        frame = new JFrame();
        categoryList = new JComboBox<>();
        categoryList2 = new JComboBox<>();
        //currList = new JComboBox<>();
        itemNameTF = new JTextField(18);
        costTF = new JTextField(18);
        //currencyTF = new JTextField(18);
        addCategoryTextField = new HintTextField("  Category name");
        tfMessage = new JTextField();
        btAddItem = new JButton("Add item");
        btAddCategory = new JButton("add category");
        ta = new JTextArea();
        panelWest = new JPanel();
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        btShowItems = new JButton("Show All items");
        searchTF =  new HintTextField("Category or ItemName");
        btSearchCheck = new JButton("Search:");
        btAddItem2 = new JButton("Add item to Cart");
        btGetItems= new JButton("Purchase");
    }

    @Override
    public void initCustomer(String userName) {
        this.userName = userName;
        itemName = new JLabel("  item name:");
        searchTF =  new HintTextField("Category or ItemName");
        btSearchCheck = new JButton("Search:");
        chooseCat = new JLabel("  choose category:");
        chooseCat2 = new JLabel("  choose ItemID:");
        itemCost = new JLabel("  item cost:");
        frame = new JFrame();
        categoryList = new JComboBox<>();
        categoryList2 = new JComboBox<>();
        //currList = new JComboBox<>();
        itemNameTF = new JTextField(18);
        costTF = new JTextField(18);
        //currencyTF = new JTextField(18);
        addCategoryTextField = new HintTextField("  Category name");
        tfMessage = new JTextField();
        btAddItem = new JButton("Add item");
        btAddItem2 = new JButton("Add item to Cart");
        btAddCategory = new JButton("add category");
        ta = new JTextArea();
        panelWest = new JPanel();
        panelNorth = new JPanel();
        panelCenter = new JPanel();
        btShowItems = new JButton("Show All items");
        dateTF =  new HintTextField("'yyyy-mm-dd' AND 'yyyy-mm-dd'");
        btDateCheck = new JButton("show dates between:");
        btGetItems= new JButton("Purchase");
    }

    /**
     * adds all the objects to the panels and main frame of the program
     * sets action listeners to the buttons of the program
     */
    public void start(boolean X) {
        font1 = new Font("SansSerif", Font.BOLD, 18);
        Font fontBtn = new Font("SansSerif", Font.BOLD, 18);
        panelNorth.setBackground(Color.LIGHT_GRAY);
        panelNorth.setLayout(new FlowLayout());
        tfMessage.setColumns(30);
        panelNorth.add(tfMessage);
        panelWest.setLayout(new GridLayout(7,2,10, 10));
        if (X==false){ // Seller
            panelWest.add(addCategoryTextField);
            panelWest.add(btAddCategory);
            btDateCheck.setFont(fontBtn);
            dateTF.setFont(font1);
            panelWest.add(btDateCheck);
            panelWest.add(dateTF);

        }
        else{ // Buyer
            btSearchCheck.setFont(fontBtn);
            searchTF.setFont(font1);
            panelWest.add(btSearchCheck);
            panelWest.add(searchTF);
        }
        panelCenter.setBackground(Color.LIGHT_GRAY);
        panelCenter.setLayout(new GridLayout(1,1));
        costTF.setFont(font1);
        itemName.setFont(font1);
        itemCost.setFont(font1);
        if (X==false){ // Seller
            panelWest.add(chooseCat);
            panelWest.add(categoryList);

        }
        else{ // Customer
            panelWest.add(chooseCat2);
            panelWest.add(categoryList2);
        }

        if (X==false){ // Seller
            panelWest.add(itemName);
            panelWest.add(itemNameTF);
            panelWest.add(itemCost);
            panelWest.add(costTF);
            btAddItem.setFont(fontBtn);
            panelWest.add(btAddItem);
        }
        else { // Customer
            panelWest.add(itemNameTF);
            btAddItem2.setFont(fontBtn);
            panelWest.add(btAddItem2);
            btGetItems.setFont(fontBtn);
            panelWest.add(btGetItems);
        }
        panelWest.add(btShowItems);
        chooseCat.setFont(font1);
        chooseCat2.setFont(font1);
        itemNameTF.setFont(font1);
        panelWest.setBackground(Color.LIGHT_GRAY);
        addCategoryTextField.setFont(font1);
        addCategoryTextField.setToolTipText("Cost");
        btAddCategory.setFont(fontBtn);
        categoryList.setFont(fontBtn);
        categoryList2.setFont(fontBtn);
        frame.setLayout(new BorderLayout());
        frame.add(panelWest, BorderLayout.WEST);
        frame.add(panelCenter, BorderLayout.EAST);
        frame.add(panelNorth,BorderLayout.NORTH);
        frame.setBounds(10, 10, 1120, 450);
        if (X==true) {
            vm.getALLItems(); // Customer
            vm.getItems2(this.userName);
        }
        else
            vm.getItems(this.userName); // Seller
        frame.setVisible(true);
        frame.setResizable(false);
        ta.setFont(ta.getFont().deriveFont(25f));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);//positioning the window to center of the screen;
        btShowItems.setFont(fontBtn);

        btAddItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("inside actionPerformed... " + Thread.currentThread().getName());
                String selectedCategory = String.valueOf(categoryList.getSelectedItem());
                //String selectedCurr = String.valueOf(currList.getSelectedItem());
                boolean isNumeric = costTF.getText().chars().allMatch( Character::isDigit );//checks if cost value is a number
                System.out.println(e);
                if(categoryList.getSelectedIndex() == -1){
                    System.out.println("invalid category");
                    tfMessage.setText("please select a category");//works
                    return;
                }
                if(Objects.equals(itemNameTF.getText(), "")){
                    System.out.println("invalid item name");
                    tfMessage.setText("please enter a name for the item");
                    return;
                }
                if(!isNumeric || Objects.equals(costTF.getText(), "")){
                    System.out.println("invalid cost");
                    tfMessage.setText("please enter a valid cost");//works
                    return;
                }

                String temp = "'" + itemNameTF.getText() + "', '" + selectedCategory + "', '" + costTF.getText() + "'";
                Item item = new Item(temp);
                System.out.println(item.getText());
                vm.addItem(item);
            }
        });

        btAddItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("inside actionPerformed... " + Thread.currentThread().getName());
                String selectedCategory = String.valueOf(categoryList2.getSelectedItem());
                //String selectedCurr = String.valueOf(currList.getSelectedItem());
                boolean isNumeric = costTF.getText().chars().allMatch( Character::isDigit );//checks if cost value is a number
                System.out.println(e);
                if(categoryList2.getSelectedIndex() == -1){
                    tfMessage.setText("please select a category2");//works
                    return;
                }

                String temp = selectedCategory;
                Item item = new Item(temp);

                vm.addItem2(item);
            }
        });



        btDateCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(dateTF.getText());
                vm.getItemBetween(item, userName);
            }
        });

        btSearchCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(searchTF.getText());
                vm.getItemByName(item, userName);
            }
        });

        btShowItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(dateTF.getText());
                vm.getALLItems();
            }
        });

        btGetItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(dateTF.getText());
                vm.getItems3(userName);
            }
        });



        btAddCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Item(addCategoryTextField.getText());
                vm.addCategory(item);
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                panel.setOpaque(true);
                frame.setVisible(true);
            }
        });
    }

    /**
     * shows the category list from the DB
     */
    @Override
    public void showCategories(List<String> catItems) {
        categoryList.setModel(new DefaultComboBoxModel<String>(catItems.toArray(new String[0]))); // this will assign the list of categories with the array list from the model.
        categoryList.setRenderer(new MyComboBoxRenderer("  Category"));//puts hint "choose category" on the category list.
        categoryList.setSelectedIndex(-1);
    }

    @Override
    public void showCategories2(List<String> catItems2) {
        categoryList2.setModel(new DefaultComboBoxModel<String>(catItems2.toArray(new String[0]))); // this will assign the list of categories with the array list from the model.
        categoryList2.setRenderer(new MyComboBoxRenderer("  ItemID"));//puts hint "choose category" on the category list.
        categoryList2.setSelectedIndex(-1);
    }

    /**
     * shows the items from the main table from the DB.
     * this func is invoked when the program is starting and when a new item is added
     */
    @Override
    public void showItems(DefaultTableModel items) {
        this.data = items;
        System.out.println("programView showItems Log!  the table has " + data.getRowCount() + " rows.");
        if(table == null){
            table = new JTable(data);
            table.setFont(new Font("TRUETYPE_FONT", Font.BOLD, 15));
            panelCenter.add(new JScrollPane(table), BorderLayout.CENTER);
            showMessage(new Message("your items"));
            table.setRowHeight(27);
            resizeColumnWidth(table);//Auto resizing the JTable column widths
            frame.setVisible(true);
        }
        else {
            table.setModel(data);
            table.setRowHeight(27);
            resizeColumnWidth(table);//Auto resizing the JTable column widths
            frame.setVisible(true);
        }
    }

    /**
     * adjusting the columns widths according to the strings inside
     */
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width=300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }


    /**
     * this func gets a string message and shows it on top of the window
     */
    @Override
    public void showMessage(Message message) {
        this.msg = message.getText();
        tfMessage.setText(message.getText());
        tfMessage.setFont(font1);
        tfMessage.setColumns(30);
    }

    /**
     * method for the test
     * @return
     */
    public String getLatestMessage(){
        return this.msg;
    }

    /**
     * sets the viewModel
     * @param vm
     */
    @Override
    public void setIViewModel(IController vm) {
        this.vm = vm;
    }

}

