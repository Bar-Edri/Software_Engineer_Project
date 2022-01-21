package MVC;

import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * this interface represents the Model in the MVC architecture.
 * this will be the class that is sending queries to the database
 * and sends back data to the view threw the Controller.
 */
public interface IModel {
    void addItem(Item item) throws eCommerceException;
    void addItem2(Item item) throws eCommerceException;
    DefaultTableModel getItems(String userName) throws eCommerceException;
    DefaultTableModel getItems2(String userName) throws eCommerceException;
    DefaultTableModel getitems3(String userName) throws eCommerceException;
    DefaultTableModel getALLItems() throws eCommerceException;
    DefaultTableModel getItemsDates(Item item, String userName) throws eCommerceException;
    DefaultTableModel getItemsCategory(Item item, String userName) throws eCommerceException;
    boolean addUser(Item item) throws eCommerceException;
    boolean checkUserPass(Item item) throws eCommerceException;
    void addCategory(Item item) throws eCommerceException;
    void addCategory2(Item item) throws eCommerceException;
    List<String> getCatItems() throws eCommerceException;
    List<String> getCatItems2() throws eCommerceException;



    //List<String> getCurrItems() throws CostManagerException;
}
