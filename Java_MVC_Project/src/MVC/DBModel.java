package MVC;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DBModel implements IModel {

    String driverFullQualifiedName = "com.mysql.jdbc.Driver";
    String connectionString = "jdbc:mysql://localhost:3306/seProj";
    Connection connection = null;
    String userName;

    /**
     * method returns the Class object associated with the class or interface with the given string name, using the given class loader.
     *
     * @throws eCommerceException
     */
    public DBModel() throws eCommerceException {
        try {
            Class.forName(driverFullQualifiedName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new eCommerceException("problem with registering driver to the driver manager", e);
        }
    }

    /**
     * retrieves the relevant items according to the dates from the DB
     *
     * @param item     is the dates
     * @param userName is currently using user
     * @return items between the 'item' dates
     * @throws eCommerceException
     */
    @Override
    public DefaultTableModel getItemsDates(Item item, String userName) throws eCommerceException {
        try {
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            //getting a statement object
            Statement statement = connection.createStatement();
            //perform simple query
            ResultSet rs = statement.executeQuery("SELECT * FROM items WHERE `Date` BETWEEN " + item.getText() + "AND UserName LIKE '" + userName + "'");
            List<String[]> values = new LinkedList<>();
            String[] columnNames = {"ItemID", "UserName", "ItemName", "Category", "Cost", "Date"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            // row contains all the columns details
            while (rs.next()) {
                Object[] row = new String[6];

                String temp = rs.getString("ItemID");
                row[0] = temp;
                temp = rs.getString("UserName");
                row[1] = temp;
                temp = rs.getString("ItemName");
                row[2] = temp;
                temp = rs.getString("Category");
                row[3] = temp;
                temp = rs.getString("Cost");
                row[4] = temp;
                temp = rs.getString("Date");
                row[5] = temp;

                model.addRow(row);
            }
            System.out.println("model getItemsDates Log!" + " the table has " + model.getRowCount() + " rows.");
            return model;
        } catch (SQLException e) {
            throw new eCommerceException("problem with getting items", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }
    }


    public DefaultTableModel getItemsCategory(Item item, String userName) throws eCommerceException {
        try {
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            //getting a statement object
            Statement statement = connection.createStatement();
            //perform simple query
            ResultSet rs = statement.executeQuery("SELECT * FROM `items` WHERE `Category`='"+item.getText()+"'OR `ItemName`='"+item.getText()+"'");
            List<String[]> values = new LinkedList<>();
            String[] columnNames = {"ItemID", "UserName", "ItemName", "Category", "Cost", "Date"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            // row contains all the columns details
            while (rs.next()) {
                Object[] row = new String[6];

                String temp = rs.getString("ItemID");
                row[0] = temp;
                temp = rs.getString("UserName");
                row[1] = temp;
                temp = rs.getString("ItemName");
                row[2] = temp;
                temp = rs.getString("Category");
                row[3] = temp;
                temp = rs.getString("Cost");
                row[4] = temp;
                temp = rs.getString("Date");
                row[5] = temp;
                model.addRow(row);
            }
            System.out.println("model getItemsDates Log!" + " the table has " + model.getRowCount() + " rows.");
            return model;
        } catch (SQLException e) {
            throw new eCommerceException("problem with getting items", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }
    }


    /**
     * retrieves all the user's items from the DB.
     *
     * @param userName
     * @return the table of items from the DB.
     * @throws eCommerceException
     */
    @Override
    public DefaultTableModel getItems(String userName) throws eCommerceException {
        try {
            this.userName = userName;
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            //getting a statement object
            Statement statement = connection.createStatement();
            //perform simple query
            ResultSet rs = statement.executeQuery("SELECT * FROM items WHERE UserName LIKE '" + userName + "'");
            String[] columnNames = {"ItemID", "UserName", "ItemName", "Category", "Cost", "Date"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0); // this DefaultTableModel will hold all the data from the main items table.
            while (rs.next()) {

                Object[] row = new String[6];

                String temp = rs.getString("ItemID");
                row[0] = temp;
                temp = rs.getString("UserName");
                row[1] = temp;
                temp = rs.getString("ItemName");
                row[2] = temp;
                temp = rs.getString("Category");
                row[3] = temp;
                temp = rs.getString("Cost");
                row[4] = temp;
                temp = rs.getString("Date");
                row[5] = temp;

                model.addRow(row);
            }

            System.out.println("model getItems Log!" + " the table has " + model.getRowCount() + " rows.");
            return model;

        } catch (SQLException e) {
            throw new eCommerceException("problem with getting items", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }
    }
    @Override
    public DefaultTableModel getItems2(String userName) throws eCommerceException {
        try {
            this.userName = userName;
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            //getting a statement object
            Statement statement = connection.createStatement();
            //perform simple query
            ResultSet rs = statement.executeQuery("SELECT * FROM cart WHERE Customer LIKE '" + userName+"'");
            String[] columnNames = {"ItemID", "UserName", "ItemName", "Category", "Cost", "Date"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0); // this DefaultTableModel will hold all the data from the main items table.
            while (rs.next()) {

                Object[] row = new String[6];

                String temp = rs.getString("ItemID");
                row[0] = temp;
                temp = rs.getString("UserName");
                row[1] = temp;
                temp = rs.getString("ItemName");
                row[2] = temp;
                temp = rs.getString("Category");
                row[3] = temp;
                temp = rs.getString("Cost");
                row[4] = temp;
                temp = rs.getString("Date");
                row[5] = temp;

                model.addRow(row);
            }

            System.out.println("model getItems Log!" + " the table has " + model.getRowCount() + " rows.");
            return model;

        } catch (SQLException e) {
            throw new eCommerceException("problem with getting items", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }
    }

    @Override
    public DefaultTableModel getitems3(String userName) throws eCommerceException {
        try {
            this.userName = userName;
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            //getting a statement object
            Statement statement = connection.createStatement();
            //perform simple query

            String sql1="insert into myorders (select * from cart where Customer='"+this.userName+"')";
            String sql2="delete from items where `itemID` in (select itemID from cart where Customer='"+this.userName+"')";
            String sql3="delete from cart where `itemID` in (select itemID from cart where Customer='"+this.userName+"') and Customer='"+this.userName+"'";


            String[] List={sql1,sql2,sql3};
            for(int i=0;i<List.length;i++) {
                PreparedStatement ps = connection.prepareStatement(List[i]);
                System.out.println(List[i]);
                System.out.println(ps.executeUpdate());
                ps.clearParameters();
            }


            ResultSet rs = statement.executeQuery("SELECT * FROM myorders WHERE Customer LIKE '" + userName+"'");
            String[] columnNames = {"ItemID", "UserName", "ItemName", "Category", "Cost", "Date"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0); // this DefaultTableModel will hold all the data from the main items table.
            while (rs.next()) {

                Object[] row = new String[6];

                String temp = rs.getString("ItemID");
                row[0] = temp;
                temp = rs.getString("UserName");
                row[1] = temp;
                temp = rs.getString("ItemName");
                row[2] = temp;
                temp = rs.getString("Category");
                row[3] = temp;
                temp = rs.getString("Cost");
                row[4] = temp;
                temp = rs.getString("Date");
                row[5] = temp;

                model.addRow(row);
            }

            System.out.println("model getItems Log!" + " the table has " + model.getRowCount() + " rows.");
            return model;

        } catch (SQLException e) {
            throw new eCommerceException("problem with getting items", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }
    }

    @Override
    public DefaultTableModel getALLItems() throws eCommerceException {
        try {
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            //getting a statement object
            Statement statement = connection.createStatement();
            //perform simple query
            ResultSet rs = statement.executeQuery("SELECT * FROM items");
            String[] columnNames = {"ItemID", "UserName", "ItemName", "Category", "Cost", "Date"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0); // this DefaultTableModel will hold all the data from the main items table.
            while (rs.next()) {

                Object[] row = new String[6];

                String temp = rs.getString("ItemID");
                row[0] = temp;
                temp = rs.getString("UserName");
                row[1] = temp;
                temp = rs.getString("ItemName");
                row[2] = temp;
                temp = rs.getString("Category");
                row[3] = temp;
                temp = rs.getString("Cost");
                row[4] = temp;
                temp = rs.getString("Date");
                row[5] = temp;

                model.addRow(row);
            }

            System.out.println("model getItems Log!" + " the table has " + model.getRowCount() + " rows.");
            return model;

        } catch (SQLException e) {
            throw new eCommerceException("problem with getting items", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }
    }


    /**
     * adds an item to the items table
     *
     * @param item is the item to be added
     * @throws eCommerceException
     */
    @Override
    public void addItem(Item item) throws eCommerceException {
        try {
            System.out.println(item.getText());
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            String sql = "INSERT INTO items (UserName, ItemName, Category, Cost) VALUES " +
                    "('" + this.userName + "', " + item.getText() + ")";
            //getting a statement object
            PreparedStatement ps = connection.prepareStatement(sql);
            int check = ps.executeUpdate();// executes the query and adds the item to the DB.
            if (check != 1) {
                throw new eCommerceException("cant add item!");
            }
        } catch (SQLException e) {
            throw new eCommerceException("cant add item!");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }
    }
    @Override
    public void addItem2(Item item) throws eCommerceException {
        try {
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            String sql1="CREATE TABLE TEMP AS SELECT * FROM items where 1=2";
            String sql2="insert into TEMP (SELECT * FROM items WHERE itemID="+item.getText()+" AND UserName NOT LIKE '"+this.userName+"')";
            String sql3="alter table TEMP add column Customer varchar(11) not null";
            String sql4="UPDATE TEMP\nset Customer='"+this.userName+"'\nWHERE itemID="+item.getText();
            String sql5="insert into cart (SELECT * FROM TEMP)";
            String sql6="drop table temp";
            String sql7="CREATE TABLE TEMP AS SELECT * FROM cart where 1=2";
            String sql8="insert into temp (select DISTINCT * from cart)";
            String sql9="drop table cart";
            String sql10="CREATE TABLE cart AS SELECT * FROM TEMP where 1=2";
            String sql11="insert into cart (select * from temp)";
            String sql12="drop table temp";

            String[] List={sql1,sql2,sql3,sql4,sql5,sql6,sql7,sql8,sql9,sql10,sql11,sql12};
            for(int i=0;i<List.length;i++) {
                PreparedStatement ps = connection.prepareStatement(List[i]);
                System.out.println(List[i]);
                System.out.println(ps.executeUpdate());
                ps.clearParameters();
            }

            //getting a statement object
           /* int check = s;// executes the query and adds the item to the DB.
            if (check != 1) {
                System.out.println("first");
                throw new CostManagerException("cant add item!");
            }*/
        } catch (SQLException e) {
            throw new eCommerceException("cant add item!");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }
    }

    /**
     * invoked when sign-up button is pressed
     * in case the user doesn't exist in the users DB then a new user is added to the table
     *
     * @param item is the username and password
     * @return true if the username doesn't exist
     * @throws eCommerceException
     * @throws SQLException
     */
    public boolean addUser(Item item) throws eCommerceException {
        boolean result = false;
        try {
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            String sql = "INSERT INTO users (UserName, Password) VALUES (" + item.getText() + ")";
            //getting a statement object
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            result = false;
            throw new eCommerceException("UserName already exist!");
        } finally {
            try {
                connection.close();
                return result;
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }

        //return result;
    }

    /**
     * invoked when log-in button is pressed
     * checks if there's a user with a matching password in the users table in the DB.
     *
     * @param userNamePass is the username and password
     * @return true if there's a username and a matching password
     * @throws SQLException
     * @throws eCommerceException
     */
    public boolean checkUserPass(Item userNamePass) throws eCommerceException {
        boolean result = false;
        Statement statement = null;
        try {
            String userNamePassword = userNamePass.getText();
            String userName = userNamePassword.substring(0, userNamePassword.indexOf(","));
            String password = userNamePassword.substring(userNamePassword.indexOf(",") + 1, userNamePassword.lastIndexOf(" "));
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            String sqlQuery = "SELECT * FROM users WHERE UserName ='" + userName + "' AND Password = '" + password + "'";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (!resultSet.isBeforeFirst()) {//this line checks if the query found something, if a username with that password exist.
                System.out.println("invalid UserName and password");
                throw new eCommerceException("invalid UserName and password");
            }
            result = true;
        } catch (SQLException e) {
            result = false;
            throw new eCommerceException("Invalid UserName and Password!");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
            return result;
        }

    }

    /**
     * adds a new category name to the category list in the DB.
     *
     * @param item is the name of the new category
     * @throws eCommerceException
     */
    @Override
    public void addCategory(Item item) throws eCommerceException {
        try {
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            String sql = "INSERT INTO `Category` (`name`) VALUES ('" + item.getText() + "')";
            //getting a statement object
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new eCommerceException("cant add item!");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }
    }
    @Override
    public void addCategory2(Item item) throws eCommerceException {
        try {
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            String sql = "INSERT INTO `Category` (`name`) VALUES ('" + item.getText() + "')";
            //getting a statement object
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new eCommerceException("cant add item!");
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new eCommerceException("problem with the connection...");
            }
        }
    }

    /**
     * retrieves the latest category list from the category table in the DB
     *
     * @return the list of categories
     * @throws eCommerceException
     */
    @Override
    public List<String> getCatItems() throws eCommerceException {
        try {
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            //getting a statement object
            Statement statement = connection.createStatement();
            //perform simple query
            ResultSet rs = statement.executeQuery("SELECT * FROM Category");
            List<String> values = new ArrayList<String>();
            int numOfCategory = 0;
            while (rs.next()) {
                numOfCategory++;
                values.add(rs.getString("name"));
            }

            //System.out.println("the category table has " + numOfCategory + " items.");
            return values;

        } catch (SQLException e) {
            throw new eCommerceException("problem with getting items", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> getCatItems2() throws eCommerceException {
        try {
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            //getting a statement object
            Statement statement = connection.createStatement();
            //perform simple query
            ResultSet rs = statement.executeQuery("SELECT ItemID FROM `items`");
            List<String> values = new ArrayList<String>();
            int numOfCategory = 0;
            while (rs.next()) { // Combo box for itemID at Customer page
                numOfCategory++;
                values.add(rs.getString("ItemID"));
            }

            //System.out.println("the category table has " + numOfCategory + " items.");
            return values;

        } catch (SQLException e) {
            throw new eCommerceException("problem with getting items", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int getCatItems3() throws eCommerceException {
        try {
            //creating a connection object
            connection = DriverManager.getConnection(connectionString, "seProj", "123");
            //getting a statement object
            Statement statement = connection.createStatement();
            //perform simple query
            ResultSet rs = statement.executeQuery("SELECT * FROM Cart");
            List<String> values = new ArrayList<String>();
            int numOfCategory = 0;
            while (rs.next()) {
                numOfCategory++;
            }

            //System.out.println("the category table has " + numOfCategory + " items.");
            return numOfCategory;

        } catch (SQLException e) {
            throw new eCommerceException("problem with getting items", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}